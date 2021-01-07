package thread;

import main.App;

import data.RouteDb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import service.AppLogger;

import metrics.MyMetrics;

public class ThreadRouteNet implements Runnable{
	
	public void run()
	{
		//String filename = "D:\\ROUTES.ASF";
		String filename = "/root/EFEED_Dataset/ROUTES.ASF";
		try {
			dispatchRoute(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dispatchRoute(String file_name) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
		
		//鍒涘缓灏嗘暟鎹啓鍏afka闆嗙兢鐨刾roducer瀵硅薄
		Properties props=new Properties();
		//props.put("bootstrap.servers", "39.102.60.29:9092");
		props.put("bootstrap.servers", "172.17.221.132:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
				
		//鍒涘缓Gson瀵硅薄
		Gson gson=new GsonBuilder().serializeSpecialFloatingPointValues().create();
		
		String str=null;
		int kind=0;
		RouteDb prev=new RouteDb();
		while((str=in.readLine())!=null)
		{
			if(str.length()<3)
				continue;
			if(str.equals("/CODED_ROUTE/ ")) {   //瀛楃涓插�兼瘮杈冭浣跨敤equals()鏂规硶
				kind=1;
				continue;
			}
			else if(str.equals("/SID/")) {
				kind=2;
				continue;
			}
			else if(str.equals("/STAR/")) {
				kind=3;
				continue;
			}
			else if(str.equals("/SPR/")) {
				kind=4;
				continue;
			}
			else if(str.equals("/STANDARD_ROUTES/")) {
				kind=5;
				continue;
			}
			else if(str.equals("/PREFERENTIAL_ROUTES/")) {
				kind=6;
				continue;
			}			
			
			switch (kind) {
				case 1:
					//ArrayList list=new ArrayList();
					char first=str.charAt(0);
					char second=str.charAt(1);
					int index=0;
					String name="";
					if(first=='-'&&second=='-')
					{
						//do nothing!
					}
					else {
						if(str.charAt(0)!=' ') {
							while(str.charAt(index)!=' ')
							{
								++index;
							}
							name=str.substring(0,index);
							//System.out.println(name);
							int sig_num=0;
							for(;index<str.length();index++)
							{
								if(str.charAt(index)=='|')
									++sig_num;
								if(sig_num==5)
									break;
							}
							++index;
							while(index<str.length()) {
								if(str.charAt(index)!=' ')
									break;
								++index;
							}
							String str1=str.substring(index);
							String[] set=str1.split(" ");
							
							RouteDb route_node=new RouteDb();
							route_node.setRoutename(name);
							for(int j=0;j<set.length;j++) {
								route_node.getPolist().add(set[j]);
							}
							App.getApp().getRouteList().updateData(route_node);
							prev=route_node;
							
							String rn_str=gson.toJson(route_node);
							System.out.println("route:"+rn_str);
							producer.send(new ProducerRecord<String, String>("route", rn_str));
							
//							MyMetrics.CountOneRoutePoint();
//							
//							try {
//								Thread.sleep(100);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
							
							/*for(int j=0;j<set.length;j++)
							{
								System.out.println(prev.toArray()[j]);
							} //*/
						}
						else {
							int sig_num=0;
							for(;index<str.length();index++)
							{
								if(str.charAt(index)=='|')
									++sig_num;
								if(sig_num==5)
									break;
							}
							++index;
							while(index<str.length()) {
								if(str.charAt(index)!=' ')
									break;
								++index;
							}
							String str1=str.substring(index);
							String[] set=str1.split(" ");
							for(int j=0;j<set.length;j++) {
								prev.getPolist().add(set[j]);
							}
							App.getApp().getRouteList().updateData(prev);
						}
					}
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
			}
		}
	}
	
}
