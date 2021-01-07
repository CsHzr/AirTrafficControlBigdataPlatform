package data;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class RouteList {
	
	private Hashtable<String,RouteDb> m_sRoutes;
	
	public RouteList()
	{
		m_sRoutes = new Hashtable<String, RouteDb>(500);
	}
	
	public void init()
	{
		
		
	}
	
	public void updateData(RouteDb fd)
	{
		m_sRoutes.put(fd.getRoutename(), fd);
	}
	
	public int size() {return m_sRoutes.size();}
	
	public Hashtable<String, RouteDb> getRoutes() {return m_sRoutes;}
	
	public boolean contains(RouteDb fd)
	{
		return m_sRoutes.containsKey(fd.getRoutename());
	}
	
	public RouteDb findRouteDataFromName(String route_name)
	{
		return m_sRoutes.get(route_name);
	}
}
