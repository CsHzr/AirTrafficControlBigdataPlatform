package hbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import data.FplDb;
import javafx.util.Pair;
import service.AppLogger;

public final class DataUtils {
	private DataUtils () {}
	private static DataUtils singleton = new DataUtils();
	public static DataUtils getDataUtils() {
		return singleton;
	}
	
	public ConcurrentHashMap<String, FplDb> getFpl(String start, String end) {
		AppLogger.info("--- getFpl ---");
		ConcurrentHashMap<String, FplDb> result = new ConcurrentHashMap<String, FplDb>();
		
		HBSUtils hbc = new HBSUtils();
		hbc.connect();
		//字段
		ArrayList<Pair<String, String>> qualifiers = new ArrayList<Pair<String, String>>();
		for(String fplfield: fplFields) {
			qualifiers.add(new Pair<String, String>("family",fplfield));
		}
		ArrayList<String> familys = new ArrayList<String>();
		familys.add("family");
		//行键过滤器
		RowFilter startFt = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryPrefixComparator(Bytes.toBytes(start)));
		RowFilter endFt = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryPrefixComparator(Bytes.toBytes(end)));
//      RowFilter typeFt = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator("^[0-9]{6}\\.[0-9]{4}\\.fpl\\.snp\\.[0-9]+"));
        RowFilter typeFt = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("fpl.snp"));
        ArrayList<Filter> filters = new ArrayList<Filter>();
        filters.add(startFt);
        filters.add(endFt);
        filters.add(typeFt);
        //两种选择，filter或者startrow/stoprow，filter更好一点
        ResultScanner hbcRes = hbc.scanData("rec", familys, qualifiers, filters, null, null);
//      ResultScanner hbcRes = hbc.scanData("rec", familys, qualifiers, null, start+".0000.fpl.snp.0", end+".2345.fpl.snp.9999");
        for(Result singleLine:hbcRes) {
//        	AppLogger.info(Bytes.toString(singleLine.getRow()));	//test
			FplDb fpl = RecToFplDb(singleLine);
			String key = Bytes.toString(singleLine.getRow());
			result.put(key, fpl);
		}
		hbc.close();
		
		return result;
	}
	private FplDb RecToFplDb(Result fplRec) {
//		AppLogger.info("--- RecToFplDb ---");
		String invalidStr = "NA";
		FplDb fd = new FplDb();
		fd.setRecTime(new Date());	//收到rec的时间
		for(Cell cell:fplRec.rawCells()) {
			String value = Bytes.toString(CellUtil.cloneValue(cell)).trim();
//			AppLogger.info(Bytes.toString(CellUtil.cloneQualifier(cell)));	//test
			switch(Bytes.toString(CellUtil.cloneQualifier(cell)))
	        {
	        case "ADEP":
	        	fd.setDep(value);
	        	break;
	        case "ADES":
	        	fd.setDes(value);
	        	break;
	        case "AIRCRAFT_TYPE":
	        	fd.setActype(value);
	        	break;
	        case "ARRIVAL_RUNWAY":
	        	fd.setArrRunway(value);
	        	break;
	        case "ASSR_CODE":
	        	fd.setAssr(value);
	        	break;
	        case "ATA.DATE_IN_SECONDS":
	        	fd.setAta(new Date(Long.parseLong(value)*1000));
	        	break;
	        case "ATD.DATE_IN_SECONDS":
	        	fd.setAtd(new Date(Long.parseLong(value)*1000));
	        	break;
	        case "CALLSIGN":
	        	fd.setCallsign(value);
	        	break;
	        case "CFL":
	        	fd.setCfl(Integer.parseInt(value));
	        	break;
	        case "CONTROLLING_SECTOR":
	        	fd.setJuSector(value);
	        	break;
	        case "CTOT":
	        	break;
	        case "DEPARTURE_RUNWAY":
	        	fd.setDepRunway(value);
	        	break;
	        case "EOBT.DATE_IN_SECONDS":
	        	fd.setEobt(new Date(Long.parseLong(value)*1000));
	        	break;
	        case "ETD.DATE_IN_SECONDS":
	        	fd.setEtd(new Date(Long.parseLong(value)*1000));
	        	break;
	        case "ETA.DATE_IN_SECONDS":
	        	fd.setEta(new Date(Long.parseLong(value)*1000));
	        	break;
	        case "FLIGHT_RULES":
	        	fd.setFlightRule(value);
	        	break;
	        case "FLIGHT_TYPES":
	        	fd.setFlightType(value);
	        	break;
	        case "FPL_STATUS":
	        	fd.setStatus(value);
	        	break; 	
	        case "GATE_NAME":
	        	fd.setGate(value);
	        	break;
	        case "IFPLID":
	        	fd.setIfpid(value);
	        	break;
	        case "NUMBER":
	        	fd.setFpid(value);
	        	break;
	        case "PSSR_CODE":
	        	fd.setPssr(value);
	        	break;
	        case "REGISTRATION_NUMBER":
	        	fd.setReg(value);
	        	break;
	        case "RFL_VALUE":
	        	fd.setRfl(Integer.parseInt(value));
	        	break;
	        case "ROUTE_FIELD":
	        	fd.setRoute(value);
	        	break;
	        case "SID":
	        	fd.setSid(value);
	        	break;
	        case "STAR":
	        	fd.setStar(value);
	        	break;
	        case "WTC":
	        	fd.setWake(value);
	        	break;
	        default:
	        	break;
	        }
		}
		return fd;
	}
	/*
	 * 从hbase中读取的飞行计划字段
	 * 注意，rec数据中的飞行计划不能用NUMBER作为key，因为每个时间点都会记录该NUMBER对应的飞行计划
	 */
	private String[] fplFields = new String[]{
			//格式：
			//HBase中存储的字段名			//fpldb类的字段名	//含义
			"NUMBER",				//private String fpid = ""; //飞行计划ID
			"IFPLID",				//private String ifpid = ""; //IFPL的ID 未使用
			"FPL_STATUS",			//private String status = ""; //计划状态
			"CALLSIGN",				//private String callsign = ""; //航班号
			"REGISTRATION_NUMBER",	//private String reg = ""; //注册号
			//hbase中没有存储该字段 		//private String address = ""; //24位地址码
			"ASSR_CODE",			//private String assr = ""; //当前二次代码
			"PSSR_CODE",			//private String pssr = ""; //上次使用的二次代码
			"ADEP",					//private String dep = ""; //起飞机场
			"ADES",					//private String des = ""; //落地机场
			//hbase中没有存储该字段		//private String altn = ""; //备降机场
			"ETD.DATE_IN_SECONDS",	//private Date etd = new Date(0); //预计起飞时间
			"ETA.DATE_IN_SECONDS",	//private Date eta = new Date(0); //预计落地时间
			"EOBT.DATE_IN_SECONDS",	//private Date eobt = new Date(0);
			//hbase中没有存储该字段		//private Date eet = new Date(0); //预计巡航时间
			"ATD.DATE_IN_SECONDS",	//private Date atd = new Date(0); //实际起飞时间
			"ATA.DATE_IN_SECONDS",	//private Date ata = new Date(0); //实际落地时间
			//hbase中没有存储该字段		//private Date aet = new Date(0); //实际巡航时间
			"FLIGHT_RULES",			//private String flightRule = ""; //飞行规则
			"FLIGHT_TYPES",			//private String flightType = ""; //飞行类型
			"AIRCRAFT_TYPE",		//private String actype = ""; //机型
			"WTC",					//private String wake = ""; //尾流
			//hbase中没有存储该字段		//private String dof = ""; //飞行计划执行日期
			"ROUTE_FIELD",			//private String route = ""; //计划申请航路
			"RFL_VALUE",			//private int rfl; //申请飞行高度
			"CFL",					//private int cfl; //指令飞行高度
			//hbase中没有存储该字段		//private String remark = ""; //备注
			//hbase中没有存储该字段		//private String inSector = ""; //所在扇区
			"CONTROLLING_SECTOR",	//private String juSector = ""; //控制扇区
			//hbase中没有存储该字段		//private int tas; //巡航速度
			//hbase中没有存储该字段		//private String other18 = ""; //编组18
			//hbase中没有存储该字段		//private String other19 = ""; //编组19
			//hbase中没有存储该字段		//private String equip = ""; //机载设备
			"SID",					//private String sid = ""; //标准进场
			"STAR",					//private String star = ""; //标准离场
			"DEPARTURE_RUNWAY",		//private String depRunway = ""; //使用跑道
			"ARRIVAL_RUNWAY",		//private String arrRunway = ""; //使用跑道
			//hbase中没有存储该字段		//private String taxRoute = ""; //滑行路由
			"GATE_NAME",			//private String gate = ""; //停机位

			//hbase中没有存储该字段		//private String vip = ""; //VIP备注
			"CTOT",					//private Date ctot = new Date(0);	//计算起飞时间
			//hbase中没有存储该字段，需要自定义	//private Date recTime = new Date(0); //接收到计划的时间
			};
}
