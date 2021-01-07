package main;

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
import thread.ThreadSurfaceFrdpNet;
import data.BestUtils;
import data.ConstantData;
import data.FplDb;
import data.FplList;
import data.IniData;
import data.SysData;
import data.TrackPoList;
import hdfs.HDFSUtils;
import data.RoutePoDb;
import data.RoutePoList;
import data.RouteDb;
import data.RouteList;
import data.RouteSectorsList;
import data.RouteSmallSectorsList;
public class App
{ 
	private static final App onlyOne = new App();
	
	private IniData iniData;
	private TrackPoList tpl;
	private TrackPoList stpl;
	private FplList fplList;
	//efeed备份
	private TrackPoList tplBatch;
	private TrackPoList stplBatch;
	private FplList fplListBatch;
	
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
			stpl = new TrackPoList(199);
			fplList = new FplList();
			
			tplBatch = new TrackPoList(99);
			stplBatch = new TrackPoList(199);
			fplListBatch = new FplList();
			
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
			stpl.init();
			fplList.init();

			tplBatch.init();
			stplBatch.init();
			fplListBatch.init();
			
			new Thread(new ThreadDateTime()).start();	
			new Thread(new ThreadFrdpNet()).start();
			new Thread(new ThreadSurfaceFrdpNet()).start();
			new Thread(new ThreadFplNet()).start();
			new Thread(new ThreadRoutePoNet()).start();
			new Thread(new ThreadRouteNet()).start();
			new Thread(new ThreadSectorsNet()).start();

			AppLogger.info("初始化工程完成！");
		}
		catch(Exception e) {
			AppLogger.error("初始化工程失败！", e);;
			System.exit(1);
		}
	}
	
	public IniData getIniData() {return iniData;}
	//综合航迹
	public TrackPoList getTrackPoList() {return tpl;}
	//场面航迹
	public TrackPoList getSurfaceTrackPoList() {return stpl;}
	//飞行计划
	public FplList getFplList() {return fplList;}
	//航路点
	public RoutePoList getRoutePointList() {
		return routePointList;
	}
	//航线
	public RouteList getRouteList() {
		return routeList;
	}
	//扇区
	public RoutePoList getRoutePartialPointList() {return routePartialPointList;}
	public RouteSmallSectorsList getRouteSmallSectorsList() {return routeSmallSectorsList;}
	public RouteSectorsList getRouteSectorsList() {return routeSectorsList;}
	
	//batch
	public TrackPoList getTrackPoListBatch() {
		return tplBatch;
	}
	public TrackPoList getSurfaceTrackPoListBatch() {
		return stplBatch;
	}
	public FplList getFplListBatch() {
		return fplListBatch;
	}
}

