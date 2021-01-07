package thread;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import main.App;
import main.AppUtils;
import service.AppLogger;
import data.BestUtils;
import data.ConstantData;
import data.SysData;
import data.TrackDb;
import data.TrackPoList;

public class ThreadFrdpNet implements Runnable
{
	private static final String NONE = "";
	public void run()
	{	
		
		MulticastSocket socketR = null;
		try{
			socketR = new MulticastSocket(8108);
			
//			InetSocketAddress sockaddr = new InetSocketAddress("233.0.0.7", 8108);
//			NetworkInterface ni = new NetworkInterface();
//			socketR.setNetworkInterface(SysData.getGlobeNif());
//			socketR.joinGroup(sockaddr, SysData.getGlobeNif());
			

			socketR.joinGroup(InetAddress.getByName("233.0.0.7"));
			
			socketR.setReceiveBufferSize(ConstantData.n_BufferSize);
		}
		catch (Exception e) 
		{
			AppLogger.error("FrdpNet线程初始化失败！", e);
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
				AppLogger.error("处理Frdp数据失败！", e);
				socketR.close();
				return;
			}	
		}						
	}
	
	public static void dispatchCat062(byte[] buf,int index,int len,boolean isPlayback)
	{
		TrackPoList tpl = App.getApp().getTrackPoList();
		TrackPoList tplBatch = App.getApp().getTrackPoListBatch();
		
		ArrayList<TrackDb> tds = decodeCat062(buf, index, len, isPlayback);
		
		for(TrackDb td: tds) {
			tpl.updateData(td);	
			tplBatch.addData(td);
		}
	}
	
	public static ArrayList<TrackDb> decodeCat062(byte[] buf,int index,int len, boolean isPlayback)
	{
		ArrayList<TrackDb> tds = new ArrayList<TrackDb>();
		byte[] byte2 = new byte[2];
		byte[] byte3 = new byte[3];
		byte[] byte4 = new byte[4];
		
		System.arraycopy(buf, index+1, byte2, 0, 2);
		int datalenth = BestUtils.byte2ToInt_High(byte2);
		int dataIndex = index+3;

		while(dataIndex < (index+datalenth-1))
		{
			int fx1=0,fx2=0,fx3=0,fx4=0,fx5=0;	
			
			int fspec1=0,fspec2=0,fspec3=0,fspec4=0,fspec5=0;
			
			fspec1 = buf[dataIndex]&0xff;
			fx1 = buf[dataIndex]&0x01;
			dataIndex++;
			
			if(fx1 == 1)
			{
				fspec2 = buf[dataIndex]&0xff;
				fx2 = buf[dataIndex]&0x01;
				dataIndex++;
				
				if(fx2 == 1)
				{
					fspec3 = buf[dataIndex]&0xff;
					fx3 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx3 == 1)
					{
						fspec4 = buf[dataIndex]&0xff;
						fx4 = buf[dataIndex]&0x01;
						dataIndex++;
						if(fx4 == 1)
						{
							fspec5 = buf[dataIndex]&0xff;
							fx5 = buf[dataIndex]&0x01;
							dataIndex++;
						}
					}	
				}	
			}
			

			int bit010 = (fspec1&0x80)>>7;
			//----Spare
			int bit015 = (fspec1&0x20)>>5;
			int bit070 = (fspec1&0x10)>>4;	
			int bit105 = (fspec1&0x08)>>3;
			int bit100 = (fspec1&0x04)>>2;
			int bit185 = (fspec1&0x02)>>1;
			
			int bit210 = (fspec2&0x80)>>7;
			int bit060 = (fspec2&0x40)>>6;
			int bit245 = (fspec2&0x20)>>5;
			int bit380 = (fspec2&0x10)>>4;	
			int bit040 = (fspec2&0x08)>>3;
			int bit080 = (fspec2&0x04)>>2;
			int bit290 = (fspec2&0x02)>>1;
			
			int bit200 = (fspec3&0x80)>>7;
			int bit295 = (fspec3&0x40)>>6;
			int bit136 = (fspec3&0x20)>>5;
			int bit130 = (fspec3&0x10)>>4;	
			int bit135 = (fspec3&0x08)>>3;
			int bit220 = (fspec3&0x04)>>2;
			int bit390 = (fspec3&0x02)>>1;
			
			int bit270 = (fspec4&0x80)>>7;
			int bit300 = (fspec4&0x40)>>6;
			int bit110 = (fspec4&0x20)>>5;
			int bit120 = (fspec4&0x10)>>4;	
			int bit510 = (fspec4&0x08)>>3;
			int bit500 = (fspec4&0x04)>>2;
			int bit340 = (fspec4&0x02)>>1;
			
			//----Spare
			//----Spare
			//----Spare
			//----Spare
			//----Spare
			int bitRE = (fspec5&0x04)>>2;
			int bitSP = (fspec5&0x02)>>1;
			
			dataIndex = dataIndex+bit010*2;	
			//----Spare
			dataIndex = dataIndex+bit015*1;	
			
			double realUpdateSeconds = 0; //s
			if(bit070 == 1)
			{
				byte4[0] = 0;
				System.arraycopy(buf, dataIndex, byte4, 1, 3);
				int timen = BestUtils.byte4ToInt_High(byte4);
				realUpdateSeconds = timen/128.0;
				
				int hour = ((int)realUpdateSeconds)/3600;
				int min = ((int)realUpdateSeconds)/60%60;
				int sec = ((int)realUpdateSeconds)%60;
				
//				System.out.println(hour+":"+min+":"+sec);
				
				
				dataIndex = dataIndex+3;	
			}
			
			
			Point2D latlonP = new Point2D.Double();
			if(bit105 == 1)
			{
				System.arraycopy(buf,dataIndex,byte4,0,4);
				double lat = BestUtils.byte4ToInt_High(byte4)*180.0/33554432.0;
				System.arraycopy(buf,dataIndex+4,byte4,0,4);
				double lon = BestUtils.byte4ToInt_High(byte4)*180.0/33554432.0;
				
				latlonP.setLocation(lon, lat);
				
				dataIndex = dataIndex + 8;
			}
			
			Point distanceP = new Point();
			if(bit100 == 1)
			{
				byte4[0] = 0;
				System.arraycopy(buf, dataIndex, byte4, 1, 3);
				int xn = BestUtils.byte4ToInt_High(byte4);
				if(xn >= (256*256*256/2))
					xn = xn - 256*256*256;
				
				byte4[0] = 0;
				System.arraycopy(buf, dataIndex+3, byte4, 1, 3);
				int yn = BestUtils.byte4ToInt_High(byte4);
				if(yn >= (256*256*256/2))
					yn = yn - 256*256*256;
				
				distanceP.x = (int)(xn*0.5);
				distanceP.y = (int)(yn*0.5);				
				
				dataIndex = dataIndex + 6;
			}
			
			
			double speed = 0.0;
			double speed_angle = 0;
			if(bit185 == 1)
			{	
				System.arraycopy(buf, dataIndex, byte2, 0, 2);
				int speedxn = BestUtils.byte2ToInt_High(byte2);
				System.arraycopy(buf, dataIndex+2, byte2, 0, 2);
				int speedyn = BestUtils.byte2ToInt_High(byte2);
				if(speedxn >= 32768)
					speedxn = speedxn - 65536;
				if(speedyn >= 32768)
					speedyn = speedyn - 65536;
				
				double speedx = speedxn*0.25*3600/1000;
				double speedy = speedyn*0.25*3600/1000;
				
				speed = Math.hypot(speedx, speedy);
				speed_angle = BestUtils.computeAngleForXY(speedx, speedy);
				
				dataIndex = dataIndex + 4;
				
//				System.out.println(speedxn+" "+speedyn);
//				System.out.println(speedx+" "+speedy);
			}
			
			if(bit210 == 1)
			{
				
				
				dataIndex = dataIndex + 2;
			}
			
			String ssr = NONE;
			if(bit060 == 1)
			{
				int I060_1 = buf[dataIndex];
				int I060_2 = buf[dataIndex+1];
				
				I060_1 = I060_1 & 0x0F;
				if(I060_2 < 0)
					I060_2 = 256 + I060_2;
				int ssrn = I060_1*256 + I060_2;
					
				int ssr1 = ssrn & 0x07;
				ssrn = ssrn >>> 3;
				int ssr2 = ssrn & 0x07;
				ssrn = ssrn >>> 3;
				int ssr3 = ssrn & 0x07;
				ssrn = ssrn >>> 3;
				int ssr4 = ssrn & 0x07;
				ssr = NONE+ssr4+ssr3+ssr2+ssr1;
				if(ssr.equals("0000"))
					ssr = NONE;	
					
				dataIndex = dataIndex + 2;
			}
			
			StringBuffer bf = new StringBuffer();
			if(bit245 == 1)
			{
				int code1 = (buf[dataIndex+1]&0xFC)>>2;
				int code2 = ((buf[dataIndex+1]&0x03)*16+ ((buf[dataIndex+2]&0xF0)>>4));
				int code3 = ((buf[dataIndex+2]&0x0F)*4+ ((buf[dataIndex+3]&0xC0)>>6));
				int code4 = ((buf[dataIndex+3]&0x3F));
				int code5 = ((buf[dataIndex+4]&0xFC)>>2);
				int code6 = ((buf[dataIndex+4]&0x03)*16+ ((buf[dataIndex+5]&0xF0)>>4));
				int code7 = ((buf[dataIndex+5]&0x0F)*4+ ((buf[dataIndex+6]&0xC0)>>6));
				int code8 = ((buf[dataIndex+6]&0x3F));
				bf.append(codeRule(code1));
				bf.append(codeRule(code2));
				bf.append(codeRule(code3));
				bf.append(codeRule(code4));
				bf.append(codeRule(code5));
				bf.append(codeRule(code6));
				bf.append(codeRule(code7));
				bf.append(codeRule(code8));
				
				dataIndex = dataIndex + 7;
			}
			String callsign = bf.toString().trim();
			
			String address = NONE;
			if(bit380 == 1)
			{
				int bitADR = 0;
				int bitID = 0;
				int bitMHG = 0;
				int bitIAS = 0;
				int bitTAS = 0;
				int bitSAL = 0;
				int bitFSS = 0;
				
				int bitTIS = 0;
				int bitTID = 0;
				int bitCOM = 0;
				int bitSAB = 0;
				int bitACS = 0;
				int bitBVR = 0;
				int bitGVR = 0;
				
				int bitRAN = 0;
				int bitTAR = 0;
				int bitTAN = 0;
				int bitGSP = 0;
				int bitVUN = 0;
				int bitMET = 0;
				int bitEMC = 0;
				
				int bitPOS = 0;
				int bitGAL = 0;
				int bitPUN = 0;
				int bitMB = 0;
				int bitIAR = 0;
				int bitMAC = 0;
				int bitBPS = 0;
				
		
				bitADR = (buf[dataIndex]&0x80)>>7;
				bitID = (buf[dataIndex]&0x40)>>6;
				bitMHG = (buf[dataIndex]&0x20)>>5;
				bitIAS = (buf[dataIndex]&0x10)>>4;	
				bitTAS = (buf[dataIndex]&0x08)>>3;
				bitSAL = (buf[dataIndex]&0x04)>>2;
				bitFSS = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit380_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit380_1 == 1)
				{
					bitTIS = (buf[dataIndex]&0x80)>>7;
					bitTID = (buf[dataIndex]&0x40)>>6;
					bitCOM = (buf[dataIndex]&0x20)>>5;
					bitSAB = (buf[dataIndex]&0x10)>>4;	
					bitACS = (buf[dataIndex]&0x08)>>3;
					bitBVR = (buf[dataIndex]&0x04)>>2;
					bitGVR = (buf[dataIndex]&0x02)>>1;
					
					int fx_bit380_2 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx_bit380_2 == 1)
					{
						bitRAN = (buf[dataIndex]&0x80)>>7;
						bitTAR = (buf[dataIndex]&0x40)>>6;
						bitTAN = (buf[dataIndex]&0x20)>>5;
						bitGSP = (buf[dataIndex]&0x10)>>4;	
						bitVUN = (buf[dataIndex]&0x08)>>3;
						bitMET = (buf[dataIndex]&0x04)>>2;
						bitEMC = (buf[dataIndex]&0x02)>>1;
						
						int fx_bit380_3 = buf[dataIndex]&0x01;
						dataIndex++;	
						if(fx_bit380_3 == 1)
						{
							bitPOS = (buf[dataIndex]&0x80)>>7;
							bitGAL = (buf[dataIndex]&0x40)>>6;
							bitPUN = (buf[dataIndex]&0x20)>>5;
							bitMB = (buf[dataIndex]&0x10)>>4;	
							bitIAR = (buf[dataIndex]&0x08)>>3;
							bitMAC = (buf[dataIndex]&0x04)>>2;
							bitBPS = (buf[dataIndex]&0x02)>>1;
							
							int fx_bit380_4 = buf[dataIndex]&0x01;
							dataIndex++;
						}
					}
				}
				
				if(bitADR == 1)
				{
					System.arraycopy(buf, dataIndex, byte3, 0, 3);
					address = BestUtils.bytesToHex(byte3);
					
					dataIndex = dataIndex+3;
				}
				
				
				StringBuffer idsb = new StringBuffer();
				if(bitID == 1)
				{
					int code1 = (buf[dataIndex]&0xFC)>>2;
					int code2 = ((buf[dataIndex]&0x03)*16+ ((buf[dataIndex+1]&0xF0)>>4));
					int code3 = ((buf[dataIndex+1]&0x0F)*4+ ((buf[dataIndex+2]&0xC0)>>6));
					int code4 = ((buf[dataIndex+2]&0x3F));
					int code5 = ((buf[dataIndex+3]&0xFC)>>2);
					int code6 = ((buf[dataIndex+3]&0x03)*16+ ((buf[dataIndex+4]&0xF0)>>4));
					int code7 = ((buf[dataIndex+4]&0x0F)*4+ ((buf[dataIndex+5]&0xC0)>>6));
					int code8 = ((buf[dataIndex+5]&0x3F));
					idsb.append(codeRule(code1));
					idsb.append(codeRule(code2));
					idsb.append(codeRule(code3));
					idsb.append(codeRule(code4));
					idsb.append(codeRule(code5));
					idsb.append(codeRule(code6));
					idsb.append(codeRule(code7));
					idsb.append(codeRule(code8));
					
					if(callsign.equals(NONE))
						callsign = idsb.toString();
					
					dataIndex = dataIndex+6;
				}
				
				if(bitMHG == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitIAS == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTAS == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitSAL == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitFSS == 1)
				{
					
					dataIndex = dataIndex+2;
				}

				if(bitTIS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTID == 1)
				{
					
					dataIndex = dataIndex+16;
				}
				if(bitCOM == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitSAB == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitACS == 1)
				{
					
					dataIndex = dataIndex+7;
				}
				if(bitBVR == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitGVR == 1)
				{
					
					dataIndex = dataIndex+2;
				}

				if(bitRAN == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTAR == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTAN == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitGSP == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitVUN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMET == 1)
				{
					
					dataIndex = dataIndex+8;
				}
				if(bitEMC == 1)
				{
					
					dataIndex = dataIndex+1;
				}

				if(bitPOS == 1)
				{
					
					dataIndex = dataIndex+6;
				}
				if(bitGAL == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitPUN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMB == 1)
				{
					
					dataIndex = dataIndex+9;
				}
				if(bitIAR == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitMAC == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitBPS == 1)
				{
					
					dataIndex = dataIndex+2;
				}
			}
			
			int tracknum = -1;
			if(bit040 == 1)
			{
				int I140_1 = buf[dataIndex];
				int I140_2 = buf[dataIndex+1];
				I140_1 = I140_1 & 0x0F;
				if(I140_2 < 0)
					I140_2 = 256 + I140_2;
				tracknum = I140_1*256 + I140_2;
				
				dataIndex = dataIndex + 2;	
			}
			
			if(bit080 == 1)
			{
				
				int fx_bit080_1 = buf[dataIndex]&0x01;
				dataIndex++;		
				if(fx_bit080_1 == 1)
				{
					int fx_bit080_2 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx_bit080_2 == 1)
					{
						int fx_bit080_3 = buf[dataIndex]&0x01;
						dataIndex++;	
						if(fx_bit080_3 == 1)
						{
							int fx_bit080_4 = buf[dataIndex]&0x01;
							dataIndex++;
							
						}	
					}
				}	
			}
			
			if(bit290 == 1)
			{
				int bitTRK = 0;
				int bitPSR = 0;
				int bitSSR = 0;
				int bitMDS = 0;
				int bitADS = 0;
				int bitES = 0;
				int bitVDL = 0;
				
				int bitUAT = 0;
				int bitLOP = 0;
				int bitMLT = 0;
				
				bitTRK = (buf[dataIndex]&0x80)>>7;
				bitPSR = (buf[dataIndex]&0x40)>>6;
				bitSSR = (buf[dataIndex]&0x20)>>5;
				bitMDS = (buf[dataIndex]&0x10)>>4;	
				bitADS = (buf[dataIndex]&0x08)>>3;
				bitES = (buf[dataIndex]&0x04)>>2;
				bitVDL = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit290_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit290_1 == 1)
				{
					bitUAT = (buf[dataIndex]&0x80)>>7;
					bitLOP = (buf[dataIndex]&0x40)>>6;
					bitMLT = (buf[dataIndex]&0x20)>>5;
		
					int fx_bit290_2 = buf[dataIndex]&0x01;
					dataIndex++;
				}
				
				if(bitTRK == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitPSR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitSSR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMDS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitADS == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitES == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitVDL == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitUAT == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitLOP == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMLT == 1)
				{
					
					dataIndex = dataIndex+1;
				}
			}
			
			if(bit200 == 1)
			{
				
				dataIndex = dataIndex+1;
			}
			
			if(bit295 == 1)
			{
				int bitMFL = 0;
				int bitMD1 = 0;
				int bitMD2 = 0;
				int bitMDA = 0;
				int bitMD4 = 0;
				int bitMD5 = 0;
				int bitMHG = 0;
				
				int bitIAS = 0;
				int bitTAS = 0;
				int bitSAL = 0;
				int bitFSS = 0;
				int bitTID = 0;
				int bitCOM = 0;
				int bitSAB = 0;
				
				int bitACS = 0;
				int bitBVR = 0;
				int bitGVR = 0;
				int bitRAN = 0;
				int bitTAR = 0;
				int bitTAN = 0;
				int bitGSP = 0;
				
				int bitVUN = 0;
				int bitMET = 0;
				int bitEMC = 0;
				int bitPOS = 0;
				int bitGAL = 0;
				int bitPUN = 0;
				int bitMB = 0;
				
				int bitIAR = 0;
				int bitMAC = 0;
				int bitBPS = 0;
			
				bitMFL = (buf[dataIndex]&0x80)>>7;
				bitMD1 = (buf[dataIndex]&0x40)>>6;
				bitMD2 = (buf[dataIndex]&0x20)>>5;
				bitMDA = (buf[dataIndex]&0x10)>>4;	
				bitMD4 = (buf[dataIndex]&0x08)>>3;
				bitMD5 = (buf[dataIndex]&0x04)>>2;
				bitMHG = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit295_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit295_1 == 1)
				{
					bitIAS = (buf[dataIndex]&0x80)>>7;
					bitTAS = (buf[dataIndex]&0x40)>>6;
					bitSAL = (buf[dataIndex]&0x20)>>5;
					bitFSS = (buf[dataIndex]&0x10)>>4;	
					bitTID = (buf[dataIndex]&0x08)>>3;
					bitCOM = (buf[dataIndex]&0x04)>>2;
					bitSAB = (buf[dataIndex]&0x02)>>1;
		
					int fx_bit295_2 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx_bit295_2 == 1)
					{
						bitACS = (buf[dataIndex]&0x80)>>7;
						bitBVR = (buf[dataIndex]&0x40)>>6;
						bitGVR = (buf[dataIndex]&0x20)>>5;
						bitRAN = (buf[dataIndex]&0x10)>>4;	
						bitTAR = (buf[dataIndex]&0x08)>>3;
						bitTAN = (buf[dataIndex]&0x04)>>2;
						bitGSP = (buf[dataIndex]&0x02)>>1;
			
						int fx_bit295_3 = buf[dataIndex]&0x01;
						dataIndex++;
						if(fx_bit295_3 == 1)
						{
							bitVUN = (buf[dataIndex]&0x80)>>7;
							bitMET = (buf[dataIndex]&0x40)>>6;
							bitEMC = (buf[dataIndex]&0x20)>>5;
							bitPOS = (buf[dataIndex]&0x10)>>4;	
							bitGAL = (buf[dataIndex]&0x08)>>3;
							bitPUN = (buf[dataIndex]&0x04)>>2;
							bitMB = (buf[dataIndex]&0x02)>>1;
				
							int fx_bit295_4 = buf[dataIndex]&0x01;
							dataIndex++;
							if(fx_bit295_4 == 1)
							{
								bitIAR = (buf[dataIndex]&0x80)>>7;
								bitMAC = (buf[dataIndex]&0x40)>>6;
								bitBPS = (buf[dataIndex]&0x20)>>5;
					
								int fx_bit295_5 = buf[dataIndex]&0x01;
								dataIndex++;
							}
						}
					}
				}
				
				
				if(bitMFL == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMD1 == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMD2 == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMDA == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMD4 == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMD5 == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMHG == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitIAS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTAS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitSAL == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitFSS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTID == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitCOM == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitSAB == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitIAS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitACS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitBVR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitGVR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitRAN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTAR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTAN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitGSP == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitVUN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMET == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitEMC == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitPOS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitGAL == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitPUN == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMB == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				
				if(bitIAR == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitMAC == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitBPS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
			}
			
			double height = 0;
			if(bit136 == 1)
			{
				System.arraycopy(buf, dataIndex, byte2, 0, 2);
				int heightn = BestUtils.byte2ToInt_High(byte2);
				height = heightn*100*0.3048/4;
				
				dataIndex = dataIndex+2;
			}
			
			if(bit130 == 1)
			{
				
				dataIndex = dataIndex+2;
			}
			
			double qnhheight = 0;
			if(bit135 == 1)
			{
				int bitQNH = (buf[dataIndex]&0x80)>>7;
				System.arraycopy(buf, dataIndex, byte2, 0, 2);
				byte2[0] = new Integer(byte2[0]&0x7f).byteValue();
				int qnhheightn = BestUtils.byte2ToInt_High(byte2);
				if(bitQNH == 1)
					qnhheight = qnhheightn*100*0.3048/4;
				else
					height = qnhheightn*100*0.3048/4;
				
				dataIndex = dataIndex+2;
			}
			
			double climbrate = 0;
			if(bit220 == 1)
			{
				System.arraycopy(buf, dataIndex, byte2, 0, 2);
				byte2[0] = new Integer(byte2[0]&0x7f).byteValue();
				int climbraten = BestUtils.byte2ToInt_High(byte2);
				if(climbraten >= 32768)
					climbraten = climbraten-65536;
				
				climbrate = climbraten*6.25*0.3048/60;
				
				dataIndex = dataIndex+2;
			}
			
			//FPL
			String wake = NONE;
			String actype = NONE;
			String dep = NONE;
			String des = NONE;
			String etd = NONE;
			String atd = NONE;
			String eta = NONE;
			String ata = NONE;
			String runway = NONE;
			String gate = NONE;
			if(bit390 == 1)
			{
				int bitTAG = 0;
				int bitCSN = 0;
				int bitIFI = 0;
				int bitFCT = 0;
				int bitTAC = 0;
				int bitWTC = 0;
				int bitDEP = 0;
				
				int bitDST = 0;
				int bitRDS = 0;
				int bitCFL = 0;
				int bitCTL = 0;
				int bitTOD = 0;
				int bitAST = 0;
				int bitSTS = 0;
				
				int bitSTD = 0;
				int bitSTA = 0;
				int bitPEM = 0;
				int bitPEC = 0;
				
				bitTAG = (buf[dataIndex]&0x80)>>7;
				bitCSN = (buf[dataIndex]&0x40)>>6;
				bitIFI = (buf[dataIndex]&0x20)>>5;
				bitFCT = (buf[dataIndex]&0x10)>>4;	
				bitTAC = (buf[dataIndex]&0x08)>>3;
				bitWTC = (buf[dataIndex]&0x04)>>2;
				bitDEP = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit390_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit390_1 == 1)
				{
					bitDST = (buf[dataIndex]&0x80)>>7;
					bitRDS = (buf[dataIndex]&0x40)>>6;
					bitCFL = (buf[dataIndex]&0x20)>>5;
					bitCTL = (buf[dataIndex]&0x10)>>4;	
					bitTOD = (buf[dataIndex]&0x08)>>3;
					bitAST = (buf[dataIndex]&0x04)>>2;
					bitSTS = (buf[dataIndex]&0x02)>>1;
		
					int fx_bit390_2 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx_bit390_2 == 1)
					{
						bitSTD = (buf[dataIndex]&0x80)>>7;
						bitSTA = (buf[dataIndex]&0x40)>>6;
						bitPEM = (buf[dataIndex]&0x20)>>5;
						bitPEC = (buf[dataIndex]&0x10)>>4;	
			
						int fx_bit390_3 = buf[dataIndex]&0x01;
						dataIndex++;
					}
				}
				
				if(bitTAG == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitCSN == 1)
				{
					String callsign_fpl = new String(buf, dataIndex, 7).trim();
					if(callsign.equals(NONE))
						callsign = callsign_fpl;
						
					dataIndex = dataIndex+7;
				}
				if(bitIFI == 1)
				{
					
					dataIndex = dataIndex+4;
				}
				if(bitFCT == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitTAC == 1)
				{
					actype = new String(buf, dataIndex, 4).trim();

					dataIndex = dataIndex+4;
				}
				if(bitWTC == 1)
				{
					wake = new String(buf, dataIndex, 1).trim();
					
					dataIndex = dataIndex+1;
				}
				if(bitDEP == 1)
				{
					dep = new String(buf, dataIndex, 4).trim();
					
					dataIndex = dataIndex+4;
				}
				
				if(bitDST == 1)
				{
					des = new String(buf, dataIndex, 4).trim();
					
					dataIndex = dataIndex+4;
				}
				if(bitRDS == 1)
				{
					runway = new String(buf, dataIndex, 3).trim();
					
					dataIndex = dataIndex+3;
				}
				if(bitCFL == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitCTL == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTOD == 1)
				{
					int rep = buf[dataIndex];
					dataIndex++;
					for(int i=0;i<rep;i++)
					{
						int todType = (buf[dataIndex]&0xf8) >> 3;
						int todHour = buf[dataIndex+1]&0x1f;
						int todMin = buf[dataIndex+2]&0x3f;
						String tod = String.format("%02d%02d", todHour, todMin);
						
						switch (todType) {
						case 2:
							etd = tod;
							break;
						case 7:
							atd = tod;
							break;
						case 8:
							eta = tod;
							break;
						case 10:
							ata = tod;
							break;
						default:
							break;
						}
						
						dataIndex = dataIndex+4;
					}	
				}
				if(bitAST == 1)
				{
					gate = new String(buf, dataIndex, 6).trim();
					
					dataIndex = dataIndex+6;
				}
				if(bitSTS == 1)
				{
					int emp = (buf[dataIndex]&0xc0)>>6;
					int avl = (buf[dataIndex]&0x30)>>4;
					
					dataIndex = dataIndex+1;
				}
				
				if(bitSTD == 1)
				{
					
					dataIndex = dataIndex+7;
				}
				if(bitSTA == 1)
				{
					
					dataIndex = dataIndex+7;
				}
				if(bitPEM == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitPEC == 1)
				{
					
					dataIndex = dataIndex+7;
				}
			}
			
			if(bit270 == 1)
			{
				
				int fx_bit270_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit270_1 == 1)
				{
					int fx_bit270_2 = buf[dataIndex]&0x01;
					dataIndex++;
					if(fx_bit270_2 == 1)
					{
						int fx_bit270_3 = buf[dataIndex]&0x01;
						dataIndex++;	
					}
				}
			}
			
			if(bit300 == 1)
			{
				
				dataIndex = dataIndex + 1;
			}
			
			if(bit110 == 1)
			{
				int bitSUM = (buf[dataIndex]&0x80)>>7;
				int bitPMN = (buf[dataIndex]&0x40)>>6;
				int bitPOS = (buf[dataIndex]&0x20)>>5;
				int bitGA = (buf[dataIndex]&0x10)>>4;	
				int bitEM1 = (buf[dataIndex]&0x08)>>3;
				int bitTOS = (buf[dataIndex]&0x04)>>2;
				int bitXP = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit110_1 = buf[dataIndex]&0x01;
				dataIndex++;
				
				if(bitSUM == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitPMN == 1)
				{
					
					dataIndex = dataIndex+4;
				}
				if(bitPOS == 1)
				{
					
					dataIndex = dataIndex+6;
				}
				if(bitGA == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitEM1 == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTOS == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitXP == 1)
				{
					
					dataIndex = dataIndex+1;
				}
			}
			
			if(bit120 == 1)
			{
				
				dataIndex = dataIndex+2;
			}
			
			if(bit510 == 1)
			{
				int fx_bit510_1 = buf[dataIndex+2]&0x01;
				dataIndex = dataIndex+3;
				if(fx_bit510_1 == 1)
				{
					int fx_bit510_2 = buf[dataIndex+2]&0x01;
					dataIndex = dataIndex+3;
				}
			}
			
			if(bit500 == 1)
			{
				int bitAPC = 0;
				int bitCOV = 0;
				int bitAPW = 0;
				int bitAGA = 0;
				int bitABA = 0;
				int bitATV = 0;
				int bitAA = 0;
				
				int bitARC = 0;

				bitAPC = (buf[dataIndex]&0x80)>>7;
				bitCOV = (buf[dataIndex]&0x40)>>6;
				bitAPW = (buf[dataIndex]&0x20)>>5;
				bitAGA = (buf[dataIndex]&0x10)>>4;	
				bitABA = (buf[dataIndex]&0x08)>>3;
				bitATV = (buf[dataIndex]&0x04)>>2;
				bitAA = (buf[dataIndex]&0x02)>>1;
				
				int fx_bit500_1 = buf[dataIndex]&0x01;
				dataIndex++;
				if(fx_bit500_1 == 1)
				{
					bitARC = (buf[dataIndex]&0x80)>>7;
					
					int fx_bit500_2 = buf[dataIndex]&0x01;
					dataIndex++;
				}
				
				if(bitAPC == 1)
				{
					
					dataIndex = dataIndex+4;
				}
				if(bitCOV == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitAPW == 1)
				{
					
					dataIndex = dataIndex+4;
				}
				if(bitAGA == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitABA == 1)
				{
					
					dataIndex = dataIndex+1;
				}
				if(bitATV == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitAA == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitARC == 1)
				{
					
					dataIndex = dataIndex+1;
				}
			}
			
			if(bit340 == 1)
			{
				int bitSID = (buf[dataIndex]&0x80)>>7;
				int bitPOS = (buf[dataIndex]&0x40)>>6;
				int bitHEI = (buf[dataIndex]&0x20)>>5;
				int bitMDC = (buf[dataIndex]&0x10)>>4;	
				int bitMDA = (buf[dataIndex]&0x08)>>3;
				int bitTYP = (buf[dataIndex]&0x04)>>2;
				
				int fx_bit340_1 = buf[dataIndex]&0x01;
				dataIndex++;
				
				if(bitSID == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitPOS == 1)
				{
					
					dataIndex = dataIndex+4;
				}
				if(bitHEI == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitMDC== 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitMDA == 1)
				{
					
					dataIndex = dataIndex+2;
				}
				if(bitTYP == 1)
				{
					
					dataIndex = dataIndex+1;
				}
			}
			
			if(bitRE == 1)
			{
				
			}
			
			if(bitSP == 1)
			{
				int splength = buf[dataIndex];
				dataIndex = dataIndex + splength;
			}
			
			//没有航迹号的跳过不要
			if(tracknum == -1)
				continue;
				
			
//			System.out.println(fspec1+" "+fspec2+" "+fspec3+" "+fspec4+" "+fspec5);
//			System.out.println(bit010+" "+" "+" "+bit015+" "+bit070+" "+bit105+" "+bit100+" "+bit185);
//			System.out.println(bit210+" "+bit060+" "+bit245+" "+bit380+" "+bit040+" "+bit080+" "+bit290);
//			System.out.println(bit200+" "+bit295+" "+bit136+" "+bit130+" "+bit135+" "+bit220+" "+bit390);
//			System.out.println(bit270+" "+bit300+" "+bit110+" "+bit120+" "+bit510+" "+bit500+" "+bit340);
//			System.out.println(" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+bitRE+" "+bitSP);
//			System.out.println("------------------------");
			
			
			TrackDb td = new TrackDb();
			td.setTrackid(NONE+tracknum);
			td.setCallsign(callsign);
			td.setSsr(ssr);
			td.setAddress(address);
			td.setLongitude(latlonP.getX());
			td.setLatitude(latlonP.getY());
			td.setX(distanceP.x);
			td.setY(distanceP.y);
			td.setHeight((int)height);
			td.setSpeed((int)speed);
			
			td.setSpeedHeading(speed_angle);	
			td.setQnhHeight((int)qnhheight);
			td.setClimbRate((int)climbrate);
			
//			test
			td.setUpdateTime(Calendar.getInstance().getTimeInMillis());
			td.setRealUpdateSeconds(realUpdateSeconds);
			
			td.setWake(wake);
			td.setAcType(actype);
			td.setDep(dep);
			td.setDes(des);
			td.setEtd(etd);
			td.setAtd(atd);
			td.setEta(eta);
			td.setAta(ata);
			td.setRunway(runway);
			td.setGate(gate);
			
			tds.add(td);	
		}

		return tds;	
	}
	
	public static char codeRule(int n)
	{
		if(n == 0)
			return ' ';
		else if(n <= 26)
			return (char)(n+64);
		else
			return (char)n;
	}
	
}