package thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.RoutePoDb;
import data.SectorDb;
import data.SmallSectorsDb;
import main.App;

public class ThreadSectorsNet implements Runnable {

	@Override
	public void run() {

		System.out.println("the thread of getSectors has execute .... ");

		
		String fileName = System.getProperty("user.home")+"/EFEED_Dataset/FDP_VOLUMES_DEFINITION.ASF";
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            Vector<String> v = new Vector<String>();
         
            String splitString = "--";
            String str = "";
            while ((line = reader.readLine()) != null ) {
              if (!line.contains("       |")) {
                if (str != "") {
                	v.addElement(str);
                }
                str = line;
              } else {
                str += line;
              }
            }
            if (str != null) v.addElement(str);
            Iterator<String> iterator = v.iterator();
            while(iterator.hasNext()) {
            	dispatch(iterator.next());
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
//                	System.out.println(App.getApp().getRoutePointList().getRoutePointList().toString());
//                	System.out.println(App.getApp().getRouteSectorsList().getRouteSectors().toString());
//                	System.out.println(App.getApp().getRouteSmallSectorsList().getSmallSectors().toString());
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
	}
	
	public static void dispatch(String tempString) {
		//System.out.println(tempString);
		if(tempString.length() >=2 ) {
			String temp = tempString.substring(0, 2);
			if(temp.equals("--") || tempString.contains("/")){
				return;
			}else {
//				System.out.println(tempString);
			}
		}else {
			return;
		}
		String regEx_Point = "......N.......E";
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx_Point);
	    Matcher matcher = pattern.matcher(tempString);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    boolean isPoint = matcher.find();
	    boolean isSectors = tempString.contains("+");
//		
		if(isPoint) 	//读入的文件 是点 增加点的list
		{
			//处理点
			RoutePoDb routePoint = decodePoint(tempString);
			if(routePoint == null) {
				return ;
			}else {
				App.getApp().getRoutePartialPointList().addRoutePoint(routePoint);
			}
		}
		else 
			
			if (isSectors){		//读入的文件是大扇区
//				System.out.println(tempString);
				SectorDb sectorDb = decodeSectors(tempString);
				if(sectorDb == null) {
					return ;
				}else {
					App.getApp().getRouteSectorsList().addRouteSectors(sectorDb);
				}
		
		}
		else {	//如果读入的文件是 小扇区， 增加小扇区的list
			if(!tempString.contains("--")) {
				SmallSectorsDb smallSector = decodeSmallSectors(tempString);
				if(smallSector == null) {
					return ;
				}else {
					App.getApp().getRouteSmallSectorsList().addSmallSectors(smallSector);
				}
			}
		}
	}

	private static SmallSectorsDb decodeSmallSectors(String tempString) {
//		tempString = "MONG14 | 14 | BAK090 BB403 INS017 INS016 INS015 INS014 INS013 INS012 INS011 \r\n" + 
//				"       |    | INS010 INS009 INS008 INS007 INS006 INS005 INS004 INS003 INS002 \r\n" + 
//				"       |    | INS001 BB001 BAK100 BAK090\r\n" + 
//				"MONG15 | 15 | BAK090 BB403 INS017 INS016 INS015 INS014 INS013 INS012 INS011 \r\n" + 
//				"       |    | INS010 INS009 INS008 INS007 INS006 INS005 INS004 INS003 INS002 \r\n" + 
//				"       |    | INS001 BB001 BAK100 BAK090\r\n" + 
//				"";
		
		String[] str = tempString.split("\\s+|\\|");
		Vector<String> newStrings = new Vector<String>();
		for(int i = 0,k = 0; i < str.length; i++,k++) {
			if(!str[i].equals("")) {
				newStrings.addElement(str[i]);
			}
		}
		SmallSectorsDb smallSectorsDb = new SmallSectorsDb();
		Iterator<String> iterator = newStrings.iterator();
		if(iterator.hasNext()) {

			String nameString = iterator.next();
			smallSectorsDb.setSmallSectorName(nameString);
			
		}
		if(iterator.hasNext()) {
			String temp = iterator.next();
			if(!temp.equals("") && isNumeric(temp)) {
				int CruisingAltitude = 0;
				CruisingAltitude = Integer.parseInt(App.getApp().getRouteSmallSectorsList().getMap().get(temp));
//				CruisingAltitude = Integer.parseInt(temp);
				smallSectorsDb.setCruisingAltitude(CruisingAltitude);
			}else {
				return null;
			}
		}
		CopyOnWriteArrayList<RoutePoDb> routePointDbs = new CopyOnWriteArrayList<RoutePoDb>();
        while(iterator.hasNext()) {
        	RoutePoDb routePointDb = App.getApp().getRoutePartialPointList().getRoutePoint(iterator.next());
        	if(routePointDb != null) {
        		routePointDbs.add(routePointDb);
        	}
        }
        smallSectorsDb.setSmallSectorList(routePointDbs);
        if(smallSectorsDb.getSmallSectorName() == null) {
        	return null;
        }
		return smallSectorsDb;
	}

	private static SectorDb decodeSectors(String tempString) {
//		tempString = "SEC1   | HI |   SEA102 + SEA103 + SEA104 + SEA105 + SEA106 + SEA107 + SEA108 + SEA109  \r\n" + 
//				"       |    | + APA108 + APA109 \r\n" + 
//				"       |    | + LPB208 + LPB209\r\n" + 
//				"       |    | + APC108 + APC109 ";
		tempString = replaceBlank(tempString);
		tempString = tempString.replace("||","|");
		String[] str = tempString.split("\\+|\\|");
		
		SectorDb sectorDb = new SectorDb();
		if(!str[0].equals("")) {
			sectorDb.setSectorName(str[0]);
			if(!str[1].equals("HI")) {
				sectorDb.setTempString("");
				CopyOnWriteArrayList<SmallSectorsDb> smallSectors = new CopyOnWriteArrayList<SmallSectorsDb>();
				for(int i = 1; i < str.length; i++) {
					if(!str[i].equals("")) {
//						SmallSectorsDb smallSectorsDb = new SmallSectorsDb();
//						smallSectorsDb.setSmallSectorName(str[i]);
						SmallSectorsDb smallSectorsDb = App.getApp().getRouteSmallSectorsList().getSmallSectorsbyName(str[i]);
						if(smallSectorsDb != null) {
							smallSectors.add(smallSectorsDb);
						}
					}
				}
				sectorDb.setSectorList(smallSectors);
			}else {
				sectorDb.setTempString(str[1]);
				CopyOnWriteArrayList<SmallSectorsDb> smallSectors = new CopyOnWriteArrayList<SmallSectorsDb>();
				for(int i = 2; i < str.length; i++) {
					if(!str[i].equals("")) {
						SmallSectorsDb smallSectorsDb = App.getApp().getRouteSmallSectorsList().getSmallSectorsbyName(str[i]);
//								new SmallSectorsDb();
//						smallSectorsDb.setSmallSectorName(str[i]);
						
						if(smallSectorsDb != null) {
							smallSectors.add(smallSectorsDb);
						}
					}
				}
				sectorDb.setSectorList(smallSectors);
			}
			
			return sectorDb;
		}else {
			return null;
		}
		
		
	}

	private static RoutePoDb decodePoint(String tempString) {
		
//		String temp = "BB001	| 425500N0962014E |" + "\n";
////				"BB002	| 414800N0950800E |" + "\n" + 
////				"BB003   | 412331N0943103E |";
		
//		String temp = "LZAR1  | APP09 | APP10 | 371142N1040442E | HI \r\n" + 
//				"LZAR2  | APP12 | APP13 | 361648N1040730E | HI";
		tempString = tempString.trim();
		RoutePoDb routePointDb = new RoutePoDb();
		tempString = replaceBlank(tempString);
		String[] str = tempString.split("\\|");

		for(int i = 0; i < str.length; i++) {
			str[i] = str[i].trim();
		}
		
		routePointDb.setPoname(str[0]);
		
		String regEx_Point_NE = "......N.......E";	//由于做字符匹配，匹配出NE的点
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx_Point_NE);
	    Matcher matcher = pattern.matcher(str[1]);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    boolean isPoint = matcher.find();
	    DecimalFormat df4  = new DecimalFormat(".0000");
	    if(isPoint) {
	    	//routePointDb.setLatitude(Integer.parseInt(str[1].substring(0, 6)));
	    	double degree  = 0, minute = 0, sec = 0;
	    	degree = Integer.parseInt(str[1].substring(0,2));
	    	minute = Integer.parseInt(str[1].substring(2,4));
	    	sec = Integer.parseInt(str[1].substring(4,6));
	    	
	    	
	    	routePointDb.setPolatitude(Double.parseDouble(df4.format((double)degree + (double) minute / 60 + (double)sec / 3600)));
	    	
	    	degree = Integer.parseInt(str[1].substring(7,10));
	    	minute = Integer.parseInt(str[1].substring(10,12));
	    	sec = Integer.parseInt(str[1].substring(12,14));
	    	routePointDb.setPolongitude(Double.parseDouble(df4.format((double)degree + (double) minute / 60 + (double)sec / 3600)));
//			routePointDb.setLongitude(Integer.parseInt(str[1].substring(7,13)));
	    }
	    
	    if(str.length == 5 ) {
	    	matcher = pattern.matcher(str[3]);
		    // 查找字符串中是否有匹配正则表达式的字符/字符串
		    isPoint = matcher.find();
	    	if(isPoint) {
		    	double degree  = 0, minute = 0, sec = 0;
		    	degree = Integer.parseInt(str[3].substring(0,2));
		    	minute = Integer.parseInt(str[3].substring(2,4));
		    	sec = Integer.parseInt(str[3].substring(4,6));
		    	routePointDb.setPolatitude(Double.parseDouble(df4.format((double)degree + (double) minute / 60 + (double)sec / 3600)));
		    	
		    	
		    	degree = Integer.parseInt(str[3].substring(7,10));
		    	minute = Integer.parseInt(str[3].substring(10,12));
		    	sec = Integer.parseInt(str[3].substring(12,14));
		    	routePointDb.setPolongitude(Double.parseDouble(df4.format((double)degree + (double) minute / 60 + (double)sec / 3600)));
	    	}
	    }

		return routePointDb;
	}
	
	//去除字符串中的空格、回车、换行符、制表符
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	public static boolean isNumeric(String str){  
	    Pattern pattern = Pattern.compile("[0-9]*");  
	    return pattern.matcher(str).matches();     
	}  
	

}
