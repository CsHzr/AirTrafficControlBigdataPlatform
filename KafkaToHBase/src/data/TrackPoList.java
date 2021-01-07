package data;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrackPoList
{
	private CopyOnWriteArrayList<TrackPo> trackPos;

	public TrackPoList(int senserChannel)
	{
		trackPos = new CopyOnWriteArrayList<TrackPo>();
	}
	
	public void init()
	{
		
	}
	
	public CopyOnWriteArrayList<TrackPo> getTrackPos() {return trackPos;}
	
	
	public void addTrackPo(TrackPo tp)
	{
		trackPos.add(tp);
	}

	
	public void removeTrackPo(TrackPo tp) 
	{
		trackPos.remove(tp);
	}
	

	public void updateData(TrackDb td)
	{
		for(TrackPo tp : trackPos)
		{
			if(tp.getTrack().getTrackid().equals(td.getTrackid()))
			{
				tp.updateData(td);
				return;
			}
		}
		addTrackPo(new TrackPo(td));		
	}
	
	public void refreshStat()
	{
		for(int i=0;i<trackPos.size();i++)
			trackPos.get(i).refreshStat();
	}
	
	
	public void checkOvertime()
	{
		long nowTime = System.currentTimeMillis();
		Iterator<TrackPo> it = trackPos.iterator();
		while(it.hasNext())
		{
			TrackPo tp = it.next();
			int updateIntervalTime = (int)((nowTime - tp.getTrack().getUpdateTime())/1000);
			if(updateIntervalTime > ConstantData.TrackPoRemoveTime_Sec)
			{
				trackPos.remove(tp);
				continue;
			}	
		}
	}
}