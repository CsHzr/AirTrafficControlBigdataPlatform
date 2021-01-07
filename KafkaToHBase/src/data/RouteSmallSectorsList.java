package data;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class RouteSmallSectorsList {
	private CopyOnWriteArrayList<SmallSectorsDb> smallSectors;
	private HashMap<String, String> map = null;		//巡航高度
	
	
	public HashMap<String, String> getMap() {
		return map;
	}
	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}
	
	public RouteSmallSectorsList()
	{
		smallSectors = new CopyOnWriteArrayList<SmallSectorsDb>();
	}
	
	public void init()
	{
		map = new HashMap<String, String>();
        map.put("1", "1800"); 
        map.put("2", "2400"); 
        map.put("3", "2700"); 
        map.put("4", "3000"); 
        map.put("5", "5100"); 
        map.put("6", "6000"); 
        map.put("7", "6600"); 
        map.put("8", "7400"); 
        map.put("9", "8090"); 
        map.put("10", "9190"); 
        map.put("11", "9500"); 
        map.put("12", "11400"); 
        map.put("13", "12600"); 
        map.put("14", "13800"); 
        map.put("15", "30000"); 
	
	}
	
	public SmallSectorsDb getSmallSectorsbyName(String SmallSectorsName) 
	{
		for(SmallSectorsDb smallSector:smallSectors) {
			if(smallSector.getSmallSectorName().equals(SmallSectorsName)) {
				return smallSector;
			}
		}
		return null;
	}
	
	public CopyOnWriteArrayList<SmallSectorsDb> getSmallSectors() {return smallSectors;}
	
	
	public void addSmallSectors(SmallSectorsDb smallSector)
	{
		smallSectors.add(smallSector);
	}

	
	public void removeSmallSectors(SmallSectorsDb smallSector) 
	{
		smallSectors.remove(smallSector);
	}
	

	public void updateData(SmallSectorsDb smallSector)
	{	
		
	}
	
	public void refreshStat()
	{
		
	}
	
	
}
