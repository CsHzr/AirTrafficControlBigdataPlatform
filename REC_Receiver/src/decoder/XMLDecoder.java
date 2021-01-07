package decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import app.RecUtils;

import org.dom4j.Element;



public class XMLDecoder {
	
	public AppConfig getAppConfig(byte[] xmlBytes) {
		try{
			AppConfig appConf = new AppConfig();
			Document doc = parseBytes(xmlBytes);
			Element root = doc.getRootElement();
			Iterator it = root.elementIterator();
			while(it.hasNext()) {
				Element e = (Element)it.next();
				String text = e.getName();
				Attribute attr;
				switch(text) {
				case "DataSource":
					attr = e.attribute("value");
					appConf.setDataSource(attr.getText());
					break;
				case "REC_path":
					attr = e.attribute("value");
					appConf.setRECPath(RecUtils.addPathSeparator(attr.getText()));
					break;
				case "FTPHost":
					attr = e.attribute("value");
					appConf.setFTPHost(attr.getText());
					break;
				case "FTPPort":
					attr = e.attribute("value");
					appConf.setFTPPort(attr.getText());
					break;
				case "FTPUser":
					attr = e.attribute("value");
					appConf.setFTPUser(attr.getText());
					break;
				case "FTPPassword":
					attr = e.attribute("value");
					appConf.setFTPPassword(attr.getText());
					break;
				case "FTPMode":
					attr = e.attribute("value");
					appConf.setFTPMode(attr.getText());
					break;
				case "FTPREC_path":
					attr = e.attribute("value");
					appConf.setFTPRECPath(RecUtils.addPathSeparator(attr.getText()));
					break;
				case "FTPTransInterval":
					attr = e.attribute("value");
					appConf.setFTPTransInterval(attr.getText());
					break;
				case "HBaseConfig":
					attr = e.attribute("value");
					appConf.setHBaseConfig(attr.getText());
					break;
				case "TableName":
					attr = e.attribute("value");
					appConf.setTableName(attr.getText());
					break;
				case "KafkaConfig":
					attr = e.attribute("value");
					appConf.setKafkaConfig(attr.getText());
					break;
				case "TopicName":
					attr = e.attribute("value");
					appConf.setTopicName(attr.getText());
					break;
				case "RECConfig_path":
					attr = e.attribute("value");
					appConf.setRECConfPath(RecUtils.addPathSeparator(attr.getText()));
					break;
				case "DataConfig_path":
					attr = e.attribute("value");
					appConf.setDataConfPath(RecUtils.addPathSeparator(attr.getText()));
					break;
				case "out_path":
					attr = e.attribute("value");
					appConf.setOutPath(RecUtils.addPathSeparator(attr.getText()));
					break;
				}
			}
			return appConf;
		} catch(DocumentException e) {
			e.printStackTrace();
			System.err.println(e.toString());
			return null;
		}
	}

	private Document parseString(String xmlText) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlText);
		return document;
	}
	private Document parseBytes(byte[] xmlBytes) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(xmlBytes));
		return document;
	}

}
