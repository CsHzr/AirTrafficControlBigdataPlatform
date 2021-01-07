package config;

public class ConstantData
{
	public static String Host;
	public static int Port;
	public static String User;
	public static String Password;
	public static int Timeout;
	public static String Type;
	public static String RemoteDir;
	public static String LocalDir;
	public static int Batch;

	public static void init(IniData id) throws Exception
	{
		Host = id.getSysPara("SFTP", "Host");
		Port = Integer.parseInt(id.getSysPara("SFTP", "Port"));
		User = id.getSysPara("SFTP", "User");
		Password = id.getSysPara("SFTP", "Password");
		Timeout = Integer.parseInt(id.getSysPara("SFTP", "Timeout"));
		Type = id.getSysPara("SFTP", "Type");
		
		RemoteDir = id.getSysPara("REC", "RemoteDir");
		if(!RemoteDir.substring(RemoteDir.length() - 1).equals("/")) {
			RemoteDir += "/";
		}
		LocalDir = id.getSysPara("REC", "LocalDir");
		if(!LocalDir.substring(LocalDir.length() - 1).equals("/")) {
			LocalDir += "/";
		}
		Batch = Integer.parseInt(id.getSysPara("REC", "Batch"));
	}
}

