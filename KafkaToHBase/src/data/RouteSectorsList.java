package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class RouteSectorsList {
	private CopyOnWriteArrayList<SectorDb> sectors;

	public RouteSectorsList()
	{
		sectors = new CopyOnWriteArrayList<SectorDb>();
	}
	
	public void init()
	{
		
	}
	
	public CopyOnWriteArrayList<SectorDb> getRouteSectors() {return sectors;}
	
	
	public void addRouteSectors(SectorDb sector)
	{
		sectors.add(sector);
	}

	
	public void removeRouteSectors(SectorDb sector) 
	{
		sectors.remove(sector);
	}
	

	public void updateData(SectorDb sector)
	{	
	}
	
	public void refreshStat()
	{
		
	}
}
