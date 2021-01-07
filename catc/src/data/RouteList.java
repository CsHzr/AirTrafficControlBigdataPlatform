package data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.Set;

public class RouteList {
	
	private ConcurrentHashMap<String,RouteDb> m_sRoutes;
	
	public RouteList()
	{
		m_sRoutes = new ConcurrentHashMap<String, RouteDb>(500);
	}
	
	public void init()
	{
		
		
	}
	
	public void updateData(RouteDb fd)
	{
		m_sRoutes.put(fd.getRoutename(), fd);
	}
	
	public int size() {return m_sRoutes.size();}
	
	public ConcurrentHashMap<String, RouteDb> getRoutes() {return m_sRoutes;}
	
	public boolean contains(RouteDb fd)
	{
		return m_sRoutes.containsKey(fd.getRoutename());
	}
	
	public RouteDb findRouteDataFromName(String route_name)
	{
		return m_sRoutes.get(route_name);
	}
}
