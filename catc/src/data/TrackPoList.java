package data;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import main.App;

public class TrackPoList
{
	private CopyOnWriteArrayList<TrackPo> trackPos;
	private CopyOnWriteArrayList<TrackPo> trackPosBatch;
	public TrackPoList(int senserChannel)
	{
		trackPos = new CopyOnWriteArrayList<TrackPo>();
		trackPosBatch = new CopyOnWriteArrayList<TrackPo>();
	}
	
	public void init()
	{
		
	}
	
	public CopyOnWriteArrayList<TrackPo> getTrackPos() {return trackPos;}
	public CopyOnWriteArrayList<TrackPo> getTrackPosBatch() {
		return trackPosBatch;
	}
	public void clearBatch() {
		trackPosBatch.clear();
	}
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
	//batch
	public void addData(TrackDb td)
	{
		trackPosBatch.add(new TrackPo(td));
	}
	public void refreshStat()
	{
		for(int i=0;i<trackPos.size();i++)
			trackPos.get(i).refreshStat();
	}
	
	
	public boolean checkOvertime()
	{
		boolean res=false;
		long nowTime = System.currentTimeMillis();
		Iterator<TrackPo> it = trackPos.iterator();
		while(it.hasNext())
		{
			TrackPo tp = it.next();
			int updateIntervalTime = (int)((nowTime - tp.getTrack().getUpdateTime())/1000);
			if(updateIntervalTime > ConstantData.TrackPoRemoveTime_Sec)
			{
				trackPos.remove(tp);
				res=true;
				continue;
			}	
		}
		return res;
	}
}