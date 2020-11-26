package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class SectorDb {
	private String sectorName;			//扇区名
	private String highPrecision;			//有一个temp字段
	private CopyOnWriteArrayList<SmallSectorsDb> sectorList;	//由一个一个的小区域组成
	
	
	
	
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getTempString() {
		return highPrecision;
	}
	public void setTempString(String tempString) {
		this.highPrecision = tempString;
	}
	public CopyOnWriteArrayList<SmallSectorsDb> getSectorList() {
		return sectorList;
	}
	public void setSectorList(CopyOnWriteArrayList<SmallSectorsDb> sectorList) {
		this.sectorList = sectorList;
	}
	@Override
	public String toString() {
		return "SectorDb [sectorName=" + sectorName + ", tempString=" + highPrecision + ", sectorList=" + sectorList + "]";
	}
	
	
}