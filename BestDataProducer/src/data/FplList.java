package data;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class FplList
{
	private Hashtable<String, FplDb> m_sFpls;
	
	public FplList()
	{
		m_sFpls = new Hashtable<String, FplDb>(500);
	}
	
	public void init()
	{
		
		
	}
	
	public void updateData(FplDb fd)
	{
		m_sFpls.put(fd.getFpid(), fd);
	}
	
	public int size() {return m_sFpls.size();}
	
	public Hashtable<String, FplDb> getFpls() {return m_sFpls;}
	
	public boolean contains(FplDb fd)
	{
		return m_sFpls.containsKey(fd.getFpid());
	}
	
	public FplDb findFplDataFromFpid(String fpid)
	{
		return m_sFpls.get(fpid);
	}
	
	public FplDb findFplDataFromCallsign(String callsign)
	{
		Iterator<FplDb> it = m_sFpls.values().iterator();
		while(it.hasNext())
		{
			FplDb currentFd = it.next();
			if(currentFd.getCallsign().equals(callsign))
				return currentFd;
		}
		return null;
	}
	
	public void checkOvertime()
	{
		long nowTime = System.currentTimeMillis();
		Iterator<String> it = m_sFpls.keySet().iterator();
		while(it.hasNext())
		{
			String fpid = it.next();
			FplDb fd = m_sFpls.get(fpid);
			int updateIntervalTime = (int)((nowTime - fd.getRecTime().getTime())/1000);
			if(updateIntervalTime > ConstantData.TrackPoRemoveTime_Sec)
			{
				it.remove();
				continue;
			}	
		}
	}
}