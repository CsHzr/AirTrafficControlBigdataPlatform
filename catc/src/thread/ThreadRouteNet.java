package thread;

import main.App;

import data.RouteDb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import service.AppLogger;

public class ThreadRouteNet implements Runnable{
	
	public void run()
	{
		String filename = System.getProperty("user.home")+"/EFEED_Dataset/ROUTES.ASF";
		try {
			dispatchRoute(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dispatchRoute(String file_name) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
		
		String str=null;
		int kind=0;
		RouteDb prev=new RouteDb();
		while((str=in.readLine())!=null)
		{
			if(str.length()<3)
				continue;
			if(str.equals("/CODED_ROUTE/ ")) {   //字符串值比较要使用equals()方法
				kind=1;
				continue;
			}
			else if(str.equals("/SID/")) {
				kind=2;
				continue;
			}
			else if(str.equals("/STAR/")) {
				kind=3;
				continue;
			}
			else if(str.equals("/SPR/")) {
				kind=4;
				continue;
			}
			else if(str.equals("/STANDARD_ROUTES/")) {
				kind=5;
				continue;
			}
			else if(str.equals("/PREFERENTIAL_ROUTES/")) {
				kind=6;
				continue;
			}			
			
			switch (kind) {
				case 1:
					//ArrayList list=new ArrayList();
					char first=str.charAt(0);
					char second=str.charAt(1);
					int index=0;
					String name="";
					if(first=='-'&&second=='-')
					{
						//do nothing!
					}
					else {
						if(str.charAt(0)!=' ') {
							while(str.charAt(index)!=' ')
							{
								++index;
							}
							name=str.substring(0,index);
							//System.out.println(name);
							int sig_num=0;
							for(;index<str.length();index++)
							{
								if(str.charAt(index)=='|')
									++sig_num;
								if(sig_num==5)
									break;
							}
							++index;
							while(index<str.length()) {
								if(str.charAt(index)!=' ')
									break;
								++index;
							}
							String str1=str.substring(index);
							String[] set=str1.split(" ");
							
							RouteDb route_node=new RouteDb();
							route_node.setRoutename(name);
							for(int j=0;j<set.length;j++) {
								route_node.getPolist().add(set[j]);
							}
							App.getApp().getRouteList().updateData(route_node);
							prev=route_node;
							
							/*for(int j=0;j<set.length;j++)
							{
								System.out.println(prev.toArray()[j]);
							} //*/
						}
						else {
							int sig_num=0;
							for(;index<str.length();index++)
							{
								if(str.charAt(index)=='|')
									++sig_num;
								if(sig_num==5)
									break;
							}
							++index;
							while(index<str.length()) {
								if(str.charAt(index)!=' ')
									break;
								++index;
							}
							String str1=str.substring(index);
							String[] set=str1.split(" ");
							for(int j=0;j<set.length;j++) {
								prev.getPolist().add(set[j]);
							}
							App.getApp().getRouteList().updateData(prev);
						}
					}
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
			}
		}
	}
	
}
