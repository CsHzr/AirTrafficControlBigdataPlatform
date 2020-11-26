package thread;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import data.BestUtils;
import data.TrackDb;

public class ThreadBatch implements Runnable{

	public void run()
	{
		
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("/home/system/surv_8102")));
			
			byte[] bufHeader = "FFFF00".getBytes();
			byte[] bufReaded = new byte[2048];
			int readedLength = -1;
			byte[] bufTail = null;
			byte[] bufSeg = null;
			
			while((readedLength = bis.read(bufReaded)) != -1)
			{
				if(bufTail != null)
				{
					bufSeg = new byte[bufTail.length+readedLength];
					System.arraycopy(bufTail, 0, bufSeg, 0, bufTail.length);
					System.arraycopy(bufReaded, 0, bufSeg, bufTail.length, readedLength);
					bufTail = null;
				}
				else
					bufSeg = Arrays.copyOfRange(bufReaded, 0, readedLength);
				
				int startIndex = 0;
				int headerIndex = -1;
				int oldHeaderIndex = -1;
				inner:
				while(startIndex < bufSeg.length)
				{
					oldHeaderIndex = headerIndex;
					headerIndex = BestUtils.getByteIndexOf(bufSeg, bufHeader, startIndex);
					if(headerIndex == -1)
						break inner;
	
					if(oldHeaderIndex!=-1 && headerIndex>oldHeaderIndex)
					{
						String dateString = new String(bufSeg, oldHeaderIndex+6, 13);
						Calendar recordDate = Calendar.getInstance();
						recordDate.setTimeInMillis(Long.parseLong(dateString));
						decode(recordDate, bufSeg, oldHeaderIndex+19, headerIndex-oldHeaderIndex-19);
					}
					
					startIndex = headerIndex+19;
				}	
				if(oldHeaderIndex!=-1 && headerIndex==-1 && oldHeaderIndex<readedLength)
				{
					bufTail = Arrays.copyOfRange(bufReaded, oldHeaderIndex, readedLength);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void decode(Calendar recordDate, 
		byte[] bufData, int dataIndex, int dateLength)
	{
		Properties props=new Properties();
		//props.put("bootstrap.servers", "39.102.60.29:9092");
		props.put("bootstrap.servers", "172.17.221.132:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		Gson gson=new GsonBuilder().serializeSpecialFloatingPointValues().create();
		ArrayList<TrackDb> tds = ThreadFrdpNet.decodeCat062(bufData, dataIndex, 
				dateLength/*, false*/);
		for(TrackDb td: tds)
		{
			
			producer.send(new ProducerRecord<String, String>("strack", gson.toJson(td)));
			
		}
	}
	
}
