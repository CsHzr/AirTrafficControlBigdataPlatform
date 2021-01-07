package service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.App;

import org.apache.log4j.Logger;

public class AppLogger {
	
	private static Logger logger = Logger.getLogger("App");
	
	public static void info(String message)
	{
		logger.info(message);		
	}
	
	public static void error(String message, Exception e)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		e.printStackTrace(new PrintStream(baos));  
		String str = baos.toString();  
		
		logger.error(message);
		logger.error(str);
	}
	
	public static void warn(String message)
	{
		logger.warn(message);
	}
	
	public static void debug(String message)
	{
		logger.debug(message);
	}
	
	public static void fatal(String message)
	{
		logger.fatal(message);
	}
}
