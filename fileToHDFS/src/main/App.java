package main;

import java.io.File;


import main.App;
import config.ConstantData;
import config.IniData;
import hdfs.HDFSUtils;

public class App {
	private static final App onlyOne = new App();
	private IniData iniData;
	
	public static App getApp()
	{
		return onlyOne;
	}
	
	private App() {
		iniData = new IniData();
		try {
			iniData.init();
			ConstantData.init(iniData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	//存一个删一个，发生异常就直接退出；没存入的文件可以通过反复执行脚本存进去
	public static void main(String[] args) {
		//获取所有文件名
		File SourceDir = new File(ConstantData.SourceDir); 
	    File[] files = SourceDir.listFiles();
	    try {
		    HDFSUtils.makeDir(ConstantData.DestDir);
		    for(File f: files) {
		    	String fn = f.getName();
		    	HDFSUtils.putFile(ConstantData.SourceDir+fn, ConstantData.DestDir+fn);
		    	f.delete();
	    }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	System.exit(1);
	    }

	}
}
