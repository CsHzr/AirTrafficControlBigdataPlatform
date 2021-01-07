package thread;

import main.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DecimalFormat;

import service.AppLogger;

import data.RoutePoDb;

public class ThreadRoutePoNet implements Runnable{
	
	public void run()
	{
		String filename = System.getProperty("user.home")+"/EFEED_Dataset/CHARACTERISTIC_POINTS.ASF";
		
		try {
			dispatchRoutePo(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			AppLogger.error("处理航路点文件失败！", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void dispatchRoutePo(String file_name) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
		DecimalFormat df = new DecimalFormat( "#.0000");  
		
		String str = null;
		while((str=in.readLine())!=null) {
			if(str.length()<3) {
				//System.out.println("catch!");
				continue;
			}
			char first=str.charAt(0);
			char second=str.charAt(1);
			int index=0;
			String name="";
			double longitude=0;
			int longitude_start;
			int longitude_end;
			double latitude=0;
			int latitude_start;
			int latitude_end;
			int degree;
			int minute;
			int sec;
			boolean isNorth=false;
			boolean isEast=false;
			if(first=='-'&&second=='-')
			{
				//do nothing!
			}
			else {
				while(str.charAt(index)!=' ')
				{
					++index;
				}
				name=str.substring(0,index);
				//System.out.println(name);
				while(str.charAt(index)!='|')
				{
					++index;
				}
				++index;
				for(;index<str.length();index++)
				{
					//if(str.charAt(index)!=' ')
					if(str.charAt(index)=='0'||str.charAt(index)=='1'||str.charAt(index)=='2'||str.charAt(index)=='3'||str.charAt(index)=='4'||str.charAt(index)=='5'||str.charAt(index)=='6'||str.charAt(index)=='7'||str.charAt(index)=='8'||str.charAt(index)=='9')
						break;
				}
				longitude_start=index;
				//System.out.println(str.substring(longitude_start,longitude_start+1));
				while(str.charAt(index)!='N'&&str.charAt(index)!='S')
				{
					++index;
				}
				longitude_end=index;
				if(str.charAt(index)=='N')
					isNorth=true;
				degree= Integer.parseInt(str.substring(longitude_start,longitude_end-4));
				minute= Integer.parseInt(str.substring(longitude_end-4,longitude_end-2));
				sec= Integer.parseInt(str.substring(longitude_end-2,longitude_end));
				//System.out.println(degree+" "+minute+" "+sec);
				if(isNorth)
					longitude = (double)degree+(double)minute/60+(double)sec/3600;
				else
					longitude = -(double)degree-(double)minute/60-(double)sec/3600;
				++index;
				latitude_start=index;
				while(str.charAt(index)!='E'&&str.charAt(index)!='W')
				{
					++index;
				}
				latitude_end=index;
				if(str.charAt(index)=='E')
					isEast=true;
				degree= Integer.parseInt(str.substring(latitude_start,latitude_end-4));
				minute= Integer.parseInt(str.substring(latitude_end-4,latitude_end-2));
				sec= Integer.parseInt(str.substring(latitude_end-2,latitude_end));
				//System.out.println(degree+" "+minute+" "+sec);
				if(isEast)
					latitude = (double)degree+(double)minute/60+(double)sec/3600;
				else
					latitude = -(double)degree-(double)minute/60-(double)sec/3600;
				
				longitude=Double.valueOf(df.format(longitude));
				latitude=Double.valueOf(df.format(latitude));
				
				RoutePoDb rpd=new RoutePoDb();
				rpd.setPoname(name);
				rpd.setPolongitude(latitude);
				rpd.setPolatitude(longitude);
				
				//System.out.println(rpd.getPolongitude());
				
				App.getApp().getRoutePointList().updateData(rpd);
			}
			
		}
		
		in.close();
	}
}
