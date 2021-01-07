package main;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class kafka_consumer {
	public kafka_consumer(String topic) {
		//配置
		kafkaProps = new Properties();
		kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.221.128:9092");
		//自动提交offset
		kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		kafkaProps.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		//反序列化器
		kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		//消费者组
		kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_test");

		this.topic = topic;
		//检查topic是否存在
		client = AdminClient.create(kafkaProps);
		ListTopicsResult topics = client.listTopics(new ListTopicsOptions());
        Set<String> topicNames;
		try {
			topicNames = topics.names().get();
			//没有这个topic就将consumer置为null
	        if(!topicNames.contains(topic))
	        	consumer = null;
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
	public void receiveMessage() {
		System.out.println("--- receive message ---");
		if(consumer!=null) {
        	//拉取多个消息，参数是等待时间
        	int count = 0;
        	while(true) {
	        	ConsumerRecords<String, String> res = consumer.poll(Duration.ofMillis(100));
	        	if(res!=null) {
		        	for(ConsumerRecord<String, String> cr:res) {
		        		System.out.println("key: "+cr.key());
		        		System.out.println("value: "+cr.value());
		        		++count;
		        	}
	        	}
	        	if(count>=5)
	        		break;
        	}
	   }
	}
	public void close() {
		System.out.println("--- close consumer ---");
		//关闭生产者
		if(consumer!=null) {
			consumer.close();
			consumer = null;
			System.out.println("Close consumer");
		}
		//关闭adminclient
		if(client!=null) {
			client.close();
			client=null;
			System.out.println("Close client");
		}
	}
	private Properties kafkaProps;//consumer配置
	private AdminClient client=null;	//adminclient
	private KafkaConsumer<String, String> consumer=null;	//consumer
	private String topic;	//topic名
	
	public static void main(String[] args) {
		kafka_producer pro = new kafka_producer();
		pro.connect("rec_test");
		pro.printTopicInfo();
		pro.sendMessage("test1", "Hello world");
		pro.sendMessage("test2", "Hello kafka");
		pro.sendMessage("test3", "Hello producer");
		pro.sendMessage("test4", "Hello rec");
		pro.sendMessage("test5", "Hello consumer");
		pro.close();
		
		kafka_consumer cons = new kafka_consumer("rec_test");
		cons.receiveMessage();
		cons.close();
	}
}



