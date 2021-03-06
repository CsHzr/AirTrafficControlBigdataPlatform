package kfk;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import app.App;
import decoder.Record;
import hbs.HBaseConnector;


public class RECConsumer {
	public RECConsumer() {
		System.out.println("--- Initialize kafka consumer ---");
		//配置
		kafkaProps = new Properties();
		kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, App.getApp().getAppConfig().getKafkaConfig());
		//自动提交offset
		kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		kafkaProps.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		//反序列化器
		kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "kfk.RecordDeseralizer");
		//消费者组
		kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "rec_consumer");
	}
	//consumer调用poll的时候才会真正开始和kafka建立TCP连接
	public void init(String topic)
	{
		System.out.println("--- Consumer connect to topic "+topic+" ---");
		this.topic = topic;
		//检查topic是否存在
		client = AdminClient.create(kafkaProps);
		ListTopicsResult topics = client.listTopics(new ListTopicsOptions());
        Set<String> topicNames;
		try {
			topicNames = topics.names().get();
			if(!topicNames.contains(topic))
	        	close();
	        else {
	        	//创建消费者
	        	consumer = new KafkaConsumer<>(kafkaProps);
	        	//订阅
	        	consumer.subscribe(Arrays.asList(topic));
	        }
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void receiveMessage(HBaseConnector hbc) {
		System.out.println("--- Receiving message from topic "+topic+" ---");
		if(consumer!=null)
		{
        	//拉取多个消息，参数是等待时间
        	while(true) {
	        	ConsumerRecords<String, Record> res = consumer.poll(Duration.ofMillis(100));
	        	if(res!=null) {
		        	for(ConsumerRecord<String, Record> cr:res) {
		        		System.out.println("msgkey: "+cr.key());
		        		Record msg = cr.value();
		        		hbc.putData(App.getApp().getAppConfig().getTableName(), "family", msg);
//		        		System.out.println("key: "+cr.key());
//		        		System.out.println("value: "+cr.value().getKey());
//		        		ArrayList<Record.item> recdata =  cr.value().getData();
//		        		System.out.println("value: "+recdata.get(0).getName()+", "+recdata.get(0).getValue());
//		        		System.out.println("value: "+recdata.get(recdata.size()-1).getName()+", "+recdata.get(recdata.size()-1).getValue());
		        	}
	        	}
        	}
        }
	}
	public void close() {
		System.out.println("--- Close kafka consumer ---");
		//关闭生产者
		if(consumer!=null) {
			consumer.close();
			consumer = null;
		}
		//关闭adminclient
		if(client!=null) {
			client.close();
			client=null;
		}
	}
	private Properties kafkaProps;//consumer配置
	private AdminClient client=null;	//adminclient
	private KafkaConsumer<String, Record> consumer=null;	//consumer
	private String topic;	//topic名
}
