package thread;

import java.awt.geom.Point2D;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Calendar;

import main.App;
import main.AppUtils;
import service.AppLogger;
import data.BestUtils;
import data.ConstantData;
import data.SysData;
import data.TrackDb;
import data.TrackPoList;

public class ThreadSurfaceFrdpNet implements Runnable
{
	private static final String NONE = "";
	public void run()
	{	
		
		MulticastSocket socketR = null;
		try
		{
			socketR = new MulticastSocket(2828);
//			ia = InetAddress.getByName("228.28.28.28");
//			socketR.joinGroup(ia);
			
//			InetSocketAddress sockaddr = new InetSocketAddress("228.28.28.28", 2828);
			socketR.joinGroup(InetAddress.getByName("228.28.28.28"));
			socketR.setReceiveBufferSize(ConstantData.n_BufferSize);
		}
		catch (Exception e) 
		{
			AppLogger.error("SurfaceFrdpNet线程初始化失败！", e);
			socketR.close();
//			System.exit(1);
			return;
		}
		
		while(true)
		{
			try
			{
				byte[] bufferR = new byte[1024];
				DatagramPacket dpR = new DatagramPacket(bufferR,bufferR.length);
				
				socketR.receive(dpR);
				//test
				//AppLogger.info(AppUtils.byteToArray(dpR.getData()));
				
				int cat = bufferR[0];
				
				switch(cat)
				{
				case 62:
					dispatchCat062(bufferR, 0, dpR.getLength(), false);
					break;
				case 65:
					
					break;
				default:
					break;
				}
			}
			catch(Exception e) 
			{
				AppLogger.error("处理SurfaceFrdp数据失败！", e);
				socketR.close();
				return;
			}	
		}						
	}
	
	public static void dispatchCat062(byte[] buf,int index,int len,boolean isPlayback)
	{
		TrackPoList tpl = App.getApp().getSurfaceTrackPoList();
		TrackPoList tplBatch = App.getApp().getSurfaceTrackPoListBatch();
		ArrayList<TrackDb> tds = ThreadFrdpNet.decodeCat062(buf, index, len, isPlayback);
		
		for(TrackDb td: tds) {
			tpl.updateData(td);	
			tplBatch.addData(td);
		}
	}
}