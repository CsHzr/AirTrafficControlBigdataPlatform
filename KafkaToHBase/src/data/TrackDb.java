package data;



public class TrackDb implements Cloneable {
	
	public enum TrackUrgentStatus {NONE,HIJ,RDO,EMG,MED,FUL,};

	private String trackid = ""; //航迹号
	private String callsign = ""; //呼号
	private String ssr = ""; //二次代码
	private String address = ""; //24位地址码
	private double latitude; //纬度
	private double longitude; //经度
	
	private int qnhHeight; //QNH修正气压高度 米
	private int height; //气压高度
	private int speed; //地速 公里/小时
	private double speedHeading; //速度方向角，与正北夹角，顺时针旋转 度
	private int climbRate; //气压高度爬升率 米/秒
	
	private TrackUrgentStatus urgencyStatus = TrackUrgentStatus.NONE; //紧急告警状态
	

	private boolean simFlag; //外推标志
	private boolean spiFlag; //SPI标志
	private boolean testFlag; //测试目标标志

	private long updateTime; //更新时间 ms
	
	private String wake = "";   //尾流，计划相关后才有此项
	private String acType = ""; //机型，计划相关后才有此项
	private String dep = "";    //起飞机场，计划相关后才有此项
	private String des = "";    //落地机场，计划相关后才有此项
	
	
	public String getTrackid() {
		return trackid;
	}
	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}
	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	public String getSsr() {
		return ssr;
	}
	public void setSsr(String ssr) {
		this.ssr = ssr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getQnhHeight() {
		return qnhHeight;
	}
	public void setQnhHeight(int qnhHeight) {
		this.qnhHeight = qnhHeight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public double getSpeedHeading() {
		return speedHeading;
	}
	public void setSpeedHeading(double speedHeading) {
		this.speedHeading = speedHeading;
	}
	public int getClimbRate() {
		return climbRate;
	}
	public void setClimbRate(int climbRate) {
		this.climbRate = climbRate;
	}
	public TrackUrgentStatus getUrgencyStatus() {
		return urgencyStatus;
	}
	public void setUrgencyStatus(TrackUrgentStatus urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
	}
	public boolean isSimFlag() {
		return simFlag;
	}
	public void setSimFlag(boolean simFlag) {
		this.simFlag = simFlag;
	}
	public boolean isSpiFlag() {
		return spiFlag;
	}
	public void setSpiFlag(boolean spiFlag) {
		this.spiFlag = spiFlag;
	}
	public boolean isTestFlag() {
		return testFlag;
	}
	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getWake() {
		return wake;
	}
	public void setWake(String wake) {
		this.wake = wake;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	@Override
	public String toString() {
		return "TrackDb [trackid=" + trackid + ", callsign=" + callsign
				+ ", ssr=" + ssr + ", address=" + address + ", latitude="
				+ latitude + ", longitude=" + longitude + ", qnhHeight="
				+ qnhHeight + ", height=" + height + ", speed=" + speed
				+ ", speedHeading=" + speedHeading + ", climbRate=" + climbRate
				+ ", urgencyStatus=" + urgencyStatus + ", simFlag=" + simFlag
				+ ", spiFlag=" + spiFlag + ", testFlag=" + testFlag
				+ ", updateTime=" + updateTime + ", wake=" + wake + ", acType="
				+ acType + ", dep=" + dep + ", des=" + des + "]";
	}
	
	
}
