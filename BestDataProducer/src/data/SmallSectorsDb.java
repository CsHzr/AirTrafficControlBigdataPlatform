package data;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SmallSectorsDb {
	private String smallSectorName;	//小扇区名字
	private int cruisingAltitude; //巡航高度
	private CopyOnWriteArrayList<RoutePoDb> smallSectorList; 	//小扇区由一个一个的点组成
	

	public String getSmallSectorName() {
		return smallSectorName;
	}
	public void setSmallSectorName(String smallSectorName) {
		this.smallSectorName = smallSectorName;
	}
	public int getCruisingAltitude() {
		return cruisingAltitude;
	}
	public void setCruisingAltitude(int cruisingAltitude) {
		this.cruisingAltitude = cruisingAltitude;
	}
	public CopyOnWriteArrayList<RoutePoDb> getSmallSectorList() {
		return smallSectorList;
	}
	public void setSmallSectorList(CopyOnWriteArrayList<RoutePoDb> smallSectorList) {
		this.smallSectorList = smallSectorList;
	}
	@Override
	public String toString() {
		return "SmallSectorsDb [smallSectorName=" + smallSectorName + ", cruisingAltitude=" + cruisingAltitude
				+ ", smallSectorList=" + smallSectorList + "]";
	}
	
	
	
}
