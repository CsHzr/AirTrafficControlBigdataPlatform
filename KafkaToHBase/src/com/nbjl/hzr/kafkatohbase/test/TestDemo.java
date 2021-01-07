package com.nbjl.hzr.kafkatohbase.test;

import data.RouteDb;
import data.RoutePoDb;
import data.TrackDb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class TestDemo {

	private static Connection connection=null;
	private static Admin admin=null;
	
	static {
		Configuration configuration=HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "172.18.1.1,172.18.1.2,172.18.1.3");
		
		try {
			
			connection=ConnectionFactory.createConnection(configuration);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			admin=connection.getAdmin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void putData(String tableName,String rowkey,String cf,String cn,String value) throws IOException {
		
		Table table = connection.getTable(TableName.valueOf(tableName));
		
		Put put = new Put(Bytes.toBytes(rowkey));
		
		put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
		
		table.put(put);
		
		table.close();
		
	}
	
	public static void putData(String tableName,String rowkey,String cf,String cn,Long value) throws IOException {
		
		Table table = connection.getTable(TableName.valueOf(tableName));
		
		Put put = new Put(Bytes.toBytes(rowkey));
		
		put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
		
		table.put(put);
		
		table.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		Gson gson=new GsonBuilder().serializeSpecialFloatingPointValues().create();
		
		Properties props=new Properties();
		props.put("bootstrap.servers", "172.18.1.1:9092");
		props.setProperty("group.id", "test");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		Consumer<String, String> consumer=new KafkaConsumer<String, String>(props);
		
		
		
		consumer.subscribe(Arrays.asList("routepoint","route","strack"));
		
		//ConsumerRecords<String, String> recordTemp = consumer.poll(0);
		
		//consumer.seekToBeginning(Arrays.asList(new TopicPartition("log_test",0)));
		
		//consumer.seek(p0,0);
		
		while(true) {
			
			ConsumerRecords<String, String> records=consumer.poll(100);
			for(ConsumerRecord<String, String> record:records)
			{
				
//				ByteArrayOutputStream out=new ByteArrayOutputStream();
//				GZIPOutputStream gzip=null;
//				gzip=new GZIPOutputStream(out);
//				gzip.write(record.value().getBytes("UTF-8"));
//				if(gzip!=null)
//					gzip.close();
//				String result=new String(out.toByteArray());
				
				if(record.topic().equals("routepoint")) {
					
					RoutePoDb routePointDb=gson.fromJson(record.value(), RoutePoDb.class);
					long currtime1=System.currentTimeMillis();
					String rowKey1=Long.toString(currtime1);
					putData("routepoint", rowKey1, "info", "name", routePointDb.getPoname());
					putData("routepoint", rowKey1, "info", "longitude", Double.toString(routePointDb.getPolongitude()));
					putData("routepoint", rowKey1, "info", "latitude", Double.toString(routePointDb.getPolatitude()));
					
				}else if(record.topic().equals("route")) {
					
					RouteDb route=gson.fromJson(record.value(), RouteDb.class);
					long currtime2=System.currentTimeMillis();
					String rowKey2=Long.toString(currtime2);
					putData("route", rowKey2, "info", "name",route.getRoutename());
					putData("route", rowKey2, "info", "point_list", route.getPolist().toString());
					
				}
				else if(record.topic().equals("strack")){
					
					TrackDb track=gson.fromJson(record.value(), TrackDb.class);
					long currtime3=System.currentTimeMillis();
					String rowKey3=Long.toString(currtime3);
					putData("strack", rowKey3, "info", "trackid",track.getTrackid());
					//putData("strack", rowKey3, "info", "ssr",track.getSsr());
					//putData("strack", rowKey3, "info", "updateTime",Long.toString(track.getUpdateTime()));
					putData("strack", rowKey3, "info", "updateTime",track.getUpdateTime());
					
				}else {
					
				}
				
				System.out.println(record.value());
				
//				long currtime=System.currentTimeMillis();
//				String rowkey=Long.toString(currtime);
//				putData("Test", rowkey, "info", "tt", record.value());
				
			}
			
		}
		
	}
	
}
