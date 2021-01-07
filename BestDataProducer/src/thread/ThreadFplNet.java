package thread;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;
import java.util.Iterator;

import main.App;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import service.AppLogger;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import data.ConstantData;
import data.FplDb;

public class ThreadFplNet implements Runnable
{
	private static final String invalidStr = "NA";
	
	public void run()
	{	
		MulticastSocket socketR = null;
		InetAddress ia = null;
		try{
			socketR = new MulticastSocket(8108);
			ia = InetAddress.getByName("233.0.0.7");
			socketR.joinGroup(ia);
			socketR.setReceiveBufferSize(ConstantData.n_BufferSize);
		}
		catch (Exception e) {
			AppLogger.error("FplNet线程初始化失败！", e);
			System.exit(1);
		}
		
		while(true)
		{
			try
			{
				byte[] bufferR = new byte[2048];
				DatagramPacket dpR = new DatagramPacket(bufferR, bufferR.length);
				
				
				socketR.receive(dpR);	
					
				dispatchFpl(bufferR,dpR.getLength(),false);
			}
			catch(Exception e) 
			{
				AppLogger.error("处理Fpl数据失败！", e);
			}	
		}						
	}
	
	

	public static void dispatchFpl(byte[] buf, int length, boolean isPlayback) throws Exception
	{
		FplDb fd = decodeFpl(buf, length, isPlayback);
		App.getApp().getFplList().updateData(fd);
	}
	
	public static FplDb decodeFpl(byte[] buf, int length, boolean isPlayback) throws Exception
	{
		FplDb fd = new FplDb();
		
		SAXReader reader = new SAXReader();
		ByteInputStream bis = new ByteInputStream(buf, 0, length);
        Document document = reader.read(bis);
        Element root = document.getRootElement();
        
        Element path = root.element("path");
        Iterator it = path.elementIterator();
        while (it.hasNext()) 
        {
	        Element element = (Element) it.next();
	        
	        String attrName = element.attributeValue("name");
	        String str = null;
	        	        
	        switch(attrName)
	        {
	        case "ADEP":
	        	fd.setDep(element.getText().trim());
	        	break;
	        case "ADES":
	        	fd.setDes(element.getText().trim());
	        	break;
	        case "AIRCRAFT_TYPE":
	        	fd.setActype(element.getText().trim());
	        	break;
	        case "ARRIVAL_RUNWAY":
	        	fd.setArrRunway(element.getText().trim());
	        	break;
	        case "ASSR_CODE":
	        	fd.setAssr(element.getText().trim());
	        	break;
	        case "ATA":
	        	str = element.elementText("field").trim();
	        	if(str.equals(invalidStr))
	        		fd.setAta(new Date(0));
	        	else
	        		fd.setAta(new Date(Long.parseLong(str)*1000));
	        	break;
	        case "ATD":
	        	str = element.elementText("field").trim();
	        	if(str.equals(invalidStr))
	        		fd.setAtd(new Date(0));
	        	else
	        		fd.setAtd(new Date(Long.parseLong(str)*1000));
	        	break;
	        case "CALLSIGN":
	        	fd.setCallsign(element.getText().trim());
	        	break;
	        case "CFL":
	        	str = element.getText().trim();
	        	if(str.equals("NA"))
	        		fd.setCfl(0);
	        	else
	        		fd.setCfl(Integer.parseInt(str));
	        	break;
	        case "CONTROLLING_SECTOR":
	        	fd.setJuSector(element.getText().trim());
	        	break;
	        case "CTOT":
	        	
	        	break;
	        case "DEPARTURE_RUNWAY":
	        	fd.setDepRunway(element.getText().trim());
	        	break;
	        case "EOBT":
	        	str = element.elementText("field").trim();
	        	if(str.equals(invalidStr))
	        		fd.setEobt(new Date(0));
	        	else
	        		fd.setEobt(new Date(Long.parseLong(str)*1000));
	        	break;
	        case "FLIGHT_RULES":
	        	fd.setFlightRule(element.getText().trim());
	        	break;
	        case "FLIGHT_TYPES":
	        	fd.setFlightType(element.getText().trim());
	        	break;
	        case "GATE_NAME":
	        	fd.setGate(element.getText().trim());
	        	break;
	        case "IFPLID":
	        	fd.setIfpid(element.getText().trim());
	        	break;
	        case "NUMBER":
	        	fd.setFpid(element.getText().trim());
	        	break;
	        case "PSSR_CODE":
	        	fd.setPssr(element.getText().trim());
	        	break;
	        case "REGISTRATION_NUMBER":
	        	fd.setReg(element.getText().trim());
	        	break;
	        case "ROUTE_FIELD":
	        	fd.setRoute(element.getText().trim());
	        	break;
	        case "SID":
	        	fd.setSid(element.getText().trim());
	        	break;
	        case "STAR":
	        	fd.setStar(element.getText().trim());
	        	break;
	        case "WTC":
	        	fd.setWake(element.getText().trim());
	        	break;
	        default:
	        	break;
	        }
        }
        fd.setRecTime(new Date());
        
        bis.close();
		
		return fd;
	}
}