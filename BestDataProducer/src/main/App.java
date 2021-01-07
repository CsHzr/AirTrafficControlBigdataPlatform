package main;

import java.io.IOException;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

import service.AppLogger;
import thread.ThreadDateTime;
import thread.ThreadFplNet;
import thread.ThreadFrdpNet;
import thread.ThreadRoutePoNet;
import thread.ThreadRouteNet;
import thread.ThreadSectorsNet;
import thread.ThreadBatch;
import data.ConstantData;
import data.FplDb;
import data.FplList;
import data.IniData;
import data.SysData;
import data.TrackPoList;
import data.RoutePoDb;
import data.RoutePoList;
import data.RouteDb;
import data.RouteList;
import data.RouteSectorsList;
import data.RouteSmallSectorsList;
import metrics.MyMetrics;

public class App
{ 
	private static final App onlyOne = new App();
	
	private IniData iniData;
	private TrackPoList tpl;
	private FplList fplList;
	private RoutePoList routePointList;
	private RouteList routeList;
	private RoutePoList routePartialPointList;
	private RouteSmallSectorsList routeSmallSectorsList;
	private RouteSectorsList routeSectorsList;
	

	
	public static App getApp()
	{
		return onlyOne;
	}
	
	private App() 
	{		
		try 
		{
			System.setProperty("user.timezone", "UTC");
			TimeZone.setDefault(TimeZone.getTimeZone("UTC")); 
			
			//数据库、配置文件、网络、全局静态变量、全局动态变量优先尽快初始化
			iniData = new IniData();
			iniData.init();
			ConstantData.init(iniData);
			SysData.init(iniData);
			
			tpl = new TrackPoList(99);
			fplList = new FplList();
			routePointList = new RoutePoList();
			routeList = new RouteList();
			routePartialPointList = new RoutePoList();
			routeSmallSectorsList = new RouteSmallSectorsList();
			routeSmallSectorsList.init();		//初始化巡航高度
			routeSectorsList = new RouteSectorsList();
			
		} catch (Exception e) {
			AppLogger.error("构建工程失败", e);
			System.exit(1);
		}	
	}
	
			
	
	public void init()
	{
		try
		{
			tpl.init();
			fplList.init();

			new Thread(new ThreadDateTime()).start();	
			//new Thread(new ThreadFrdpNet()).start();
			//new Thread(new ThreadFplNet()).start();
			new Thread(new ThreadRoutePoNet()).start();
			new Thread(new ThreadRouteNet()).start();
			//new Thread(new ThreadSectorsNet()).start();
			new Thread(new ThreadBatch()).start();

			AppLogger.info("初始化工程完成！");
		}
		catch(Exception e) {
			AppLogger.error("初始化工程失败！", e);;
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		
		App.getApp().init();
		
		try {
			MyMetrics.Initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void test()
	{
		try 
		{
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public IniData getIniData() {return iniData;}
	public TrackPoList getTrackPoList() {return tpl;}
	public FplList getFplList() {return fplList;}

	public RoutePoList getRoutePointList() {
		return routePointList;
	}

	public RouteList getRouteList() {
		return routeList;
	}
	
	public RoutePoList getRoutePartialPointList() {return routePartialPointList;}
	public RouteSmallSectorsList getRouteSmallSectorsList() {return routeSmallSectorsList;}
	public RouteSectorsList getRouteSectorsList() {return routeSectorsList;}
}

