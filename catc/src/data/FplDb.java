package data;

import java.util.Date;


public class FplDb
{
	public enum RflUnit {M,S,A,F,VFR};
	public enum TasUnit {K,N,M};
	public enum FplType {DEP,ARR,OVERFLY}
			
	private String fpid = ""; //飞行计划ID
	private String ifpid = ""; //IFPL的ID 未使用
	private String status = ""; //计划状态
	private String callsign = ""; //航班号	
	private String reg = ""; //注册号
	private String address = ""; //24位地址码
	private String assr = ""; //当前二次代码
	private String pssr = ""; //上次使用的二次代码
	private String dep = ""; //起飞机场
	private String des = ""; //落地机场
	private String altn = ""; //备降机场
	private Date etd = new Date(0); //预计起飞时间
	private Date eta = new Date(0); //预计落地时间
	private Date eobt = new Date(0);
	private Date eet = new Date(0); //预计巡航时间
	private Date atd = new Date(0); //实际起飞时间
	private Date ata = new Date(0); //实际落地时间
	private Date aet = new Date(0); //实际巡航时间
	
	
	private String flightRule = ""; //飞行规则
	private String flightType = ""; //飞行类型
	private String actype = ""; //机型
	private String wake = ""; //尾流
	private String dof = ""; //飞行计划执行日期
	private String route = ""; //计划申请航路
	private int rfl; //申请飞行高度
	private int cfl; //指令飞行高度
	private String remark = ""; //备注
	private String inSector = ""; //所在扇区
	private String juSector = ""; //控制扇区
	private int tas; //巡航速度
	private String other18 = ""; //编组18
	private String other19 = ""; //编组19
	private String equip = ""; //机载设备

	private String sid = ""; //标准进场
	private String star = ""; //标准离场
	private String depRunway = ""; //使用跑道
	private String arrRunway = ""; //使用跑道
	private String taxRoute = ""; //滑行路由
	private String gate = ""; //停机位

	private String vip = ""; //VIP备注
	private Date ctot = new Date(0);	//计算起飞时间
	private Date recTime = new Date(0); //接收到计划的时间

	public String getFpid() {
		return fpid;
	}

	public void setFpid(String fpid) {
		this.fpid = fpid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAssr() {
		return assr;
	}

	public void setAssr(String assr) {
		this.assr = assr;
	}

	public String getPssr() {
		return pssr;
	}

	public void setPssr(String pssr) {
		this.pssr = pssr;
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

	public String getAltn() {
		return altn;
	}

	public void setAltn(String altn) {
		this.altn = altn;
	}

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public Date getEobt() {
		return eobt;
	}

	public void setEobt(Date eobt) {
		this.eobt = eobt;
	}

	public Date getEet() {
		return eet;
	}

	public void setEet(Date eet) {
		this.eet = eet;
	}

	public Date getAtd() {
		return atd;
	}

	public void setAtd(Date atd) {
		this.atd = atd;
	}

	public Date getAta() {
		return ata;
	}

	public void setAta(Date ata) {
		this.ata = ata;
	}

	public Date getAet() {
		return aet;
	}

	public void setAet(Date aet) {
		this.aet = aet;
	}

	public String getFlightRule() {
		return flightRule;
	}

	public void setFlightRule(String flightRule) {
		this.flightRule = flightRule;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getWake() {
		return wake;
	}

	public void setWake(String wake) {
		this.wake = wake;
	}

	public String getDof() {
		return dof;
	}

	public void setDof(String dof) {
		this.dof = dof;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getRfl() {
		return rfl;
	}

	public void setRfl(int rfl) {
		this.rfl = rfl;
	}

	public int getCfl() {
		return cfl;
	}

	public void setCfl(int cfl) {
		this.cfl = cfl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInSector() {
		return inSector;
	}

	public void setInSector(String inSector) {
		this.inSector = inSector;
	}

	public String getJuSector() {
		return juSector;
	}

	public void setJuSector(String juSector) {
		this.juSector = juSector;
	}

	public int getTas() {
		return tas;
	}

	public void setTas(int tas) {
		this.tas = tas;
	}

	public String getOther18() {
		return other18;
	}

	public void setOther18(String other18) {
		this.other18 = other18;
	}

	public String getOther19() {
		return other19;
	}

	public void setOther19(String other19) {
		this.other19 = other19;
	}

	public String getEquip() {
		return equip;
	}

	public void setEquip(String equip) {
		this.equip = equip;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getDepRunway() {
		return depRunway;
	}

	public void setDepRunway(String depRunway) {
		this.depRunway = depRunway;
	}

	public String getArrRunway() {
		return arrRunway;
	}

	public void setArrRunway(String arrRunway) {
		this.arrRunway = arrRunway;
	}

	public String getTaxRoute() {
		return taxRoute;
	}

	public void setTaxRoute(String taxRoute) {
		this.taxRoute = taxRoute;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}


	public Date getCtot() {
		return ctot;
	}

	public void setCtot(Date ctot) {
		this.ctot = ctot;
	}

	public String getIfpid() {
		return ifpid;
	}

	public void setIfpid(String ifpid) {
		this.ifpid = ifpid;
	}

	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}
}
