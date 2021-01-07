package config;





public class ConstantData
{
	public static String str_Version = "0.01.20200602";
	public static String HbaseConfig;
	public static String TableName;
	
	public static void init(IniData id) throws Exception
	{
		str_Version = id.getSysPara("Others", "Version");
		HbaseConfig = id.getSysPara("Config", "HBaseConfig");
		TableName = id.getSysPara("Config", "TableName");
	}
}

