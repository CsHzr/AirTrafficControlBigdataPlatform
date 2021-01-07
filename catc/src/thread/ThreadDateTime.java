package thread;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import data.BestUtils;
import data.ConstantData;
import data.FplList;
import hdfs.HDFSUtils;
import main.App;


public class ThreadDateTime implements Runnable
{
	public void run()
	{	
		Timer timer15Sec = new Timer();
		timer15Sec.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					//每15秒定时判断，删除超时计划
					if(App.getApp().getFplList().checkOvertime())
						;
					
				}catch(Exception e)
				{
					
				}		
			}
		}, 15*1000, 15*1000);
		
		Timer timer4Sec = new Timer();
		timer4Sec.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//每4秒定时判断，删除超时航迹
				if(App.getApp().getTrackPoList().checkOvertime()) {
					;
					//System.out.print("track removed\n");
				}
			}
		}, 4*1000, 4*1000);
		
		Timer fplBatch = new Timer();
		fplBatch.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					//为备份创建hdfs目录
					HDFSUtils.makeDir(ConstantData.HDFSPathFpl);
					//创建本地目录
					BestUtils.createDir(ConstantData.LocalPath);
					//写文件
					SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd_HHmm");
					Date date = new Date(System.currentTimeMillis());
					String fileName = "fpl_"+formatter.format(date);
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
					FplList fplListBatch = App.getApp().getFplListBatch();
			        oos.writeObject(fplListBatch);
			        oos.close();
			        App.getApp().getFplListBatch().clearBatch();
					//存入hdfs
			        HDFSUtils.putFile(ConstantData.LocalPath+fileName, ConstantData.HDFSPathFpl+fileName);
			        
				}catch(Exception e)
				{
					
				}		
			}
		}, ConstantData.FplBatchInterval*1000, ConstantData.FplBatchInterval*1000);
		
		Timer trackBatch = new Timer();
		trackBatch.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					//为备份创建hdfs目录
					HDFSUtils.makeDir(ConstantData.HDFSPathTrack);
					//创建本地目录
					BestUtils.createDir(ConstantData.LocalPath);
					//写文件
					SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd_HHmm");
					Date date = new Date(System.currentTimeMillis());
					String fileName = "track_"+formatter.format(date);
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			        oos.writeObject(App.getApp().getTrackPoListBatch());
			        oos.close();
					//存入hdfs
			        HDFSUtils.putFile(ConstantData.LocalPath+fileName, ConstantData.HDFSPathTrack+fileName);
			        App.getApp().getTrackPoListBatch().clearBatch();
				}catch(Exception e)
				{
					
				}		
			}
		}, ConstantData.TrackBatchInterval*1000, ConstantData.TrackBatchInterval*1000);
		
		Timer StrackBatch = new Timer();
		StrackBatch.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					//为备份创建hdfs目录
					HDFSUtils.makeDir(ConstantData.HDFSPathStrack);
					//创建本地目录
					BestUtils.createDir(ConstantData.LocalPath);
					//写文件
					SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd_HHmm");
					Date date = new Date(System.currentTimeMillis());
					String fileName = "strack_"+formatter.format(date);
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			        oos.writeObject(App.getApp().getSurfaceTrackPoListBatch());
			        oos.close();
					//存入hdfs
			        HDFSUtils.putFile(ConstantData.LocalPath+fileName, ConstantData.HDFSPathStrack+fileName);
			        App.getApp().getSurfaceTrackPoListBatch().clearBatch();
				}catch(Exception e)
				{
					
				}		
			}
		}, ConstantData.StrackBatchInterval*1000, ConstantData.StrackBatchInterval*1000);
	}
}