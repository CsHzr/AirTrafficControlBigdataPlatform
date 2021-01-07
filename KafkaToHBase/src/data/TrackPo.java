package data;

public class TrackPo
{
	private TrackDb track;
	private FplDb fpl;
	
	public TrackPo(TrackDb track)
	{
		this.track = track;
		
		assocFpl();
		refreshStat();	
	}

	public void updateData(TrackDb track)
	{	
		this.track = track;

		assocFpl();
		refreshStat();
	}
	
	public void assocFpl()
	{
		
	}

	public void refreshStat()
	{	
		
	}

	public TrackDb getTrack() {
		return track;
	}

	public FplDb getFpl() {
		return fpl;
	}
}