package config;

public class ConstantData
{
	public static String HDFSConfig;
	public static String DestDir;
	public static String SourceDir;

	public static void init(IniData id) throws Exception
	{
		HDFSConfig = id.getSysPara("Config", "HDFSConfig");
		
		DestDir = id.getSysPara("Config", "DestDir");
		if(!DestDir.substring(DestDir.length() - 1).equals("/")) {
			DestDir += "/";
		}
		SourceDir = id.getSysPara("Config", "SourceDir");
		if(!SourceDir.substring(SourceDir.length() - 1).equals("/")) {
			SourceDir += "/";
		}
	}
}

