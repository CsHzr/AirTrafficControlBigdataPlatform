package thread;

import java.util.Timer;
import java.util.TimerTask;

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
					App.getApp().getFplList().checkOvertime();
					
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
				App.getApp().getTrackPoList().checkOvertime();
			}
		}, 4*1000, 4*1000);
	
	}
}