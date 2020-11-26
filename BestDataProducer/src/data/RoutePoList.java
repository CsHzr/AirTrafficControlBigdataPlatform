package data;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import sun.misc.FDBigInteger;

public class RoutePoList {
	
	private CopyOnWriteArrayList<RoutePoDb> routePointList;
	
	private Hashtable<String,RoutePoDb> m_sRoutePos;
	
	public RoutePoList()
	{
		routePointList = new CopyOnWriteArrayList<RoutePoDb>();
		m_sRoutePos=new Hashtable<String,RoutePoDb>(1000);
	}
	
	public void init()
	{
		
		
	}
	
	public CopyOnWriteArrayList<RoutePoDb> getRoutePointList() {return routePointList;}
	
	public void addRoutePoint(RoutePoDb routePoint)
	{
		routePointList.add(routePoint);
	}
	
	public void removeRoutePoint(RoutePoDb routePoint) 
	{
		routePointList.remove(routePoint);
	}
	
	public RoutePoDb getRoutePoint(String routePointName) 
	{
		for(RoutePoDb routePoint:routePointList) {
			if(routePoint.getPoname().equals(routePointName)) {
				return routePoint;
			}
		}
		return null;
	}
	
	public void refreshStat()
	{
		
	}
	//----------------------------------------
	
	public void updateData(RoutePoDb fd)
	{
		m_sRoutePos.put(fd.getPoname(), fd);
	}
	
	public int size() {return m_sRoutePos.size();}
	
	public Hashtable<String, RoutePoDb> getRoutePoints() {return m_sRoutePos;}
	
	public boolean contains(RoutePoDb fd)
	{
		return m_sRoutePos.containsKey(fd.getPoname());
	}
	
	public RoutePoDb findRoutePointDataFromName(String route_point_name)
	{
		return m_sRoutePos.get(route_point_name);
	}
	
	
}
