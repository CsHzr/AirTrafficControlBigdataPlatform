package sftp;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

public class testSFTP {
	public static void main(String[] args) throws Exception {  
		JSchExecutor sftp = new JSchExecutor("sftp2", "123", "192.168.159.100", 22);
		sftp.connect();
		ArrayList<String> files =sftp.lsFIle("/");
		for(String file:files)
		{
			System.out.println(file);
		}
		sftp.disconnect();
    }  
}
