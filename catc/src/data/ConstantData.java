package data;




//时间单位都是秒（s）
public class ConstantData
{
	public static String str_Version = "2.64.20201218";
		
	public static int n_BufferSize = 13107100;
	
	public static long TrackPoRemoveTime_Sec = 12;
	public static long FplRemoveTime_Sec = 4*3600;//4小时

	public static String HbaseConfig;
	public static String HDFSConfig;
	public static int FplBatchInterval;
	public static int TrackBatchInterval;
	public static int StrackBatchInterval;
	public static String HDFSPathFpl;
	public static String HDFSPathTrack;
	public static String HDFSPathStrack;
	public static String LocalPath;
	public static void init(IniData id) throws Exception
	{
		HbaseConfig = id.getSysPara("Config", "HBaseConfig");
		HDFSConfig = id.getSysPara("Config", "HDFSConfig");
		FplBatchInterval = Integer.parseInt(id.getSysPara("Config", "FplBatchInterval"));
		TrackBatchInterval = Integer.parseInt(id.getSysPara("Config", "TrackBatchInterval"));
		StrackBatchInterval = Integer.parseInt(id.getSysPara("Config", "StrackBatchInterval"));
		HDFSPathFpl = BestUtils.pathFormat(id.getSysPara("COnfig", "HDFSPathFpl"));
		HDFSPathTrack = BestUtils.pathFormat(id.getSysPara("COnfig", "HDFSPathTrack"));
		HDFSPathStrack = BestUtils.pathFormat(id.getSysPara("COnfig", "HDFSPathStrack"));
		LocalPath = BestUtils.pathFormat(id.getSysPara("COnfig", "LocalPath"));
	}
}

