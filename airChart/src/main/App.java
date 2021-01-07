package main;


import java.util.TimeZone;

import config.ConstantData;
import config.IniData;
import service.AppLogger;

public class App
{ 
	private static final App onlyOne = new App();
	
	private IniData iniData;
	

	
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
			System.out.println(ConstantData.str_Version);
			System.out.println(ConstantData.HbaseConfig);
			
		} catch (Exception e) {
			AppLogger.error("构建工程失败", e);
			System.exit(1);
		}	
	}
	
			
	
	public void init()
	{
		try
		{
			//new Thread(new ThreadDateTime()).start();	
			//new Thread(new ThreadFplNet()).start();

			AppLogger.info("初始化工程完成！");
		}
		catch(Exception e) {
			AppLogger.error("初始化工程失败！", e);;
			System.exit(1);
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
}

