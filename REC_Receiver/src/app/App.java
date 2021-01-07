package app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import decoder.AppConfig;
import decoder.XMLDecoder;
import hbs.HBaseConnector;
import kfk.RECConsumer;
import kfk.RECProducer;


public class App {
	private static final App DecoderApp = new App();
	
	public static App getApp() {
		return DecoderApp;
	}
	private App() {
		//TODO 测试时修改
		String AppConfPath = "./app_config.xml";
		
		System.out.println("Read app_config.xml from "+AppConfPath+".");
		byte[] xmlBytes = RecUtils.readFileToBytes(AppConfPath); 
//		System.out.println("Reading app_config.xml from /home/swj/air_traffic/app_config.xml.");
//		byte[] xmlBytes = readFileToBytes("/home/swj/air_traffic/app_config.xml"); 

		if(xmlBytes != null) {
			XMLDecoder dec = new XMLDecoder();
			appConf = dec.getAppConfig(xmlBytes);
		}
		if(xmlBytes == null || appConf == null) {
			System.err.println("Warning: An error has occurred when reading app_config.xml!");
			System.exit(1);
		}
	}
	
	
	private Charset encoding = StandardCharsets.UTF_8;
	//app
	private AppConfig appConf;

	public void init() {
		new Thread(new ThreadStorage()).start(); //启动消费者
	}
	public AppConfig getAppConfig() {
		return appConf;
	}
	public Charset getEncoding() {
		return encoding;
	}
	public void print() {
		System.out.println("------------------------AppConfig------------------------");
		App.getApp().getAppConfig().print();
	}
	public static void main(String[] args) {
		App.getApp().init();

	}
}
