package thread;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Iterator;

import main.App;
import main.AppUtils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import service.AppLogger;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import data.ConstantData;
import data.FplDb;
import data.SysData;

public class ThreadFplNet implements Runnable
{
	private static final String invalidStr = "NA";
	
	public void run()
	{	
		MulticastSocket socketR = null;
		try{
			socketR = new MulticastSocket(8110);
			
//			InetSocketAddress sockaddr = new InetSocketAddress("233.0.0.7", 8108);
//			NetworkInterface ni = new NetworkInterface();
//			socketR.setNetworkInterface(SysData.getGlobeNif());
//			socketR.joinGroup(sockaddr, SysData.getGlobeNif());
			

			socketR.joinGroup(InetAddress.getByName("233.0.0.7"));
			
			socketR.setReceiveBufferSize(ConstantData.n_BufferSize);
		}
		catch (Exception e) {
			AppLogger.error("FplNet线程初始化失败！", e);
			socketR.close();
			return;
		}
		
		while(true)
		{
			try
			{
				byte[] bufferR = new byte[3072];
				DatagramPacket dpR = new DatagramPacket(bufferR, bufferR.length);
				
				socketR.receive(dpR);
				//test
				//AppLogger.info(AppUtils.byteToArray(dpR.getData()));
					
				dispatchFpl(bufferR,0,dpR.getLength(),false);
			}
			catch(Exception e) 
			{
				AppLogger.error("处理Fpl数据失败！", e);
				socketR.close();
				break;
			}	
		}						
	}
	
	

	public static void dispatchFpl(byte[] buf, int index, int length, boolean isPlayback) throws Exception
	{
		FplDb fd = decodeFpl(buf, index, length, isPlayback);
		if(fd != null) {
			App.getApp().getFplList().updateData(fd);	
			App.getApp().getFplListBatch().addData(fd);
		}
	}
	
	public static FplDb decodeFpl(byte[] buf, int index, int length, boolean isPlayback) throws Exception
	{
		FplDb fd = new FplDb();
		
		SAXReader reader = new SAXReader();
		ByteInputStream bis = new ByteInputStream(buf, index, length);
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
	        	if(str.equals(invalidStr))
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
	        case "ETD":
	        	str = element.elementText("field").trim();
	        	if(str.equals(invalidStr))
	        		fd.setEtd(new Date(0));
	        	else
	        		fd.setEtd(new Date(Long.parseLong(str)*1000));
	        	break;
	        case "ETA":
	        	str = element.elementText("field").trim();
	        	if(str.equals(invalidStr))
	        		fd.setEta(new Date(0));
	        	else
	        		fd.setEta(new Date(Long.parseLong(str)*1000));
	        	break;
	        case "FLIGHT_RULES":
	        	fd.setFlightRule(element.getText().trim());
	        	break;
	        case "FLIGHT_TYPES":
	        	fd.setFlightType(element.getText().trim());
	        	break;
	        case "FPL_STATUS":
	        	fd.setStatus(element.getText().trim());
	        	break; 	
	        case "GATE_NAME":
	        	fd.setGate(element.getText().trim());
	        	break;
	        case "IFPLID":
	        	fd.setIfpid(element.getText().trim());
	        	break;
	        case "NUMBER":
	        	str = element.getText().trim();
	        	if(str.equals(invalidStr))
	        		return null;
	        	else
	        		fd.setFpid(str);
	        	break;
	        case "PSSR_CODE":
	        	fd.setPssr(element.getText().trim());
	        	break;
	        case "REGISTRATION_NUMBER":
	        	fd.setReg(element.getText().trim());
	        	break;
	        case "RFL_VALUE":
	        	str = element.getText().trim();
	        	if(str.equals(invalidStr))
	        		fd.setRfl(0);
	        	else
	        		fd.setRfl(Integer.parseInt(str));
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