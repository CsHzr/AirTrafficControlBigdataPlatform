package data;

//import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
//import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.util.Pair;;

public class FplList
{
	private ConcurrentHashMap<String, FplDb> m_sFpls;
	private CopyOnWriteArrayList<Pair<String, FplDb>> fplBatch;
	
	public FplList()
	{
		m_sFpls = new ConcurrentHashMap<String, FplDb>(500);
		fplBatch = new CopyOnWriteArrayList<Pair<String, FplDb>>();
	}
	
	public void init()
	{}
	
	public void updateData(FplDb fd)
	{
		m_sFpls.put(fd.getFpid(), fd);
	}
	//Batch
	public void addData(FplDb fd)
	{
		fplBatch.add(new Pair<String, FplDb>(fd.getFpid(), fd));
	}
	public void clearBatch() {
		fplBatch.clear();
	}
	public int size() {return m_sFpls.size();}
	
	public ConcurrentHashMap<String, FplDb> getFpls() {return m_sFpls;}
	public CopyOnWriteArrayList<Pair<String, FplDb>> getFplBatch() {
		return fplBatch;
	}
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
	
	public boolean checkOvertime()
	{
		boolean res=false;
		long nowTime = System.currentTimeMillis();
		Iterator<Entry<String, FplDb>> it = m_sFpls.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, FplDb> entry = it.next();
			String fpid = entry.getKey();
			FplDb fd = entry.getValue();
			int updateIntervalTime = (int)((nowTime - fd.getRecTime().getTime())/1000);
			if(updateIntervalTime > ConstantData.TrackPoRemoveTime_Sec)
			{
				it.remove();
				res=true;
				continue;
			}	
		}
		return res;
	}
}