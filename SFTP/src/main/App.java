package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import config.ConstantData;
import config.IniData;
import sftp.JSchExecutor;

public class App {
	private static final App onlyOne = new App();
	private IniData iniData;
	
	public static App getApp()
	{
		return onlyOne;
	}
	
	private App() {
		iniData = new IniData();
		try {
			iniData.init();
			ConstantData.init(iniData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	private static void putPathlog(ArrayList<String> dir, JSchExecutor sftp) {
		try {
	        FileOutputStream fos=new FileOutputStream(new File("pathlog"));
	        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	        BufferedWriter  bw=new BufferedWriter(osw);
	        
	        for(String str: dir) {
	        	bw.write(str);
	        	bw.newLine();
	        }
	        
	        bw.close();
	        osw.close();
	        fos.close();
	        
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sftp.disconnect();
			System.exit(1);
		}
	}
	private static ArrayList<String> getPathLog() {
		ArrayList<String> arrs=new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			
			File file = new File("pathlog");
	        file.createNewFile();
			fis = new FileInputStream(file);
			isr=new InputStreamReader(fis, "UTF-8");
	        br = new BufferedReader(isr);
	        
	        String line="";
	        while ((line=br.readLine())!=null) {
	        	arrs.add(line);
	        }
	        br.close();
	        isr.close();
	        fis.close(); 
	        
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return arrs;
	}
	private static void getRec() {
		//从哪个目录开始取数据
		String dir1 = null;
		String dir2 = null;
		ArrayList<String> path = getPathLog();
		if(path.size()>0 && path.get(0).matches("^DISK_[0-9]{8}_[0-9]{4}$")) {
			dir1 = path.get(0);
		}
		if(path.size()>0 && path.get(0).matches("^REC_[0-9]{8}_[0-9]{4}$")) {
			dir1 = path.get(0);
		}

		//列出本次要下载的目录
		TreeSet<String> dirForDownload = new TreeSet<String>();
		JSchExecutor sftp = new JSchExecutor(ConstantData.User, ConstantData.Password, ConstantData.Host, ConstantData.Port);
		try {
			sftp.connect();

			//一级目录(DISK_20201020_2015)
			ArrayList<String> files =sftp.lsFIle(ConstantData.RemoteDir);
			TreeSet<String> dirList1 = new TreeSet<String>(); //TresSet可以保证字符串的字典序
			String regexp1="^DISK_[0-9]{8}_[0-9]{4}$";
			for(String f: files) {
				if(f.matches(regexp1))
					dirList1.add(f);
			}
			if(dirList1.size() == 0) {
				System.out.println("No data!");
				sftp.disconnect();
				System.exit(1);
			}
			if(dir1 == null || !dirList1.contains(dir1)) {
				dir1 = dirList1.first();
			}
			//二级目录(REC_20201020_2015)
			files =sftp.lsFIle(ConstantData.RemoteDir+dir1);
			TreeSet<String> dirList2 = new TreeSet<String>(); //TresSet可以保证字符串的字典序
			String regexp2="^REC_[0-9]{8}_[0-9]{4}$";
			for(String f: files) {
				if(f.matches(regexp2))
					dirList2.add(f);
			}
			if(dirList2.size() == 0 ||
				(dirList2.contains(dir2) && dir2.equals(dirList2.last()))) {//从二级目录的下一个目录开始取数据
				System.out.println("No data!");
				sftp.disconnect();
				System.exit(1);
			}
			if(dir2 == null || !dirList2.contains(dir2)) {
				dir2 = "REC_00000000_0000";
			}
			//列出本次要下载的所有目录,从dir2的下一个目录开始下载
			int count =0;
			String p1 = dir1;
			String p2 = dir2;
			while(true) {
				while(dirList2.higher(p2) != null && count != ConstantData.Batch) {
					p2 = dirList2.higher(p2);
					dirForDownload.add(p1+"/"+p2);
					++count;
				}
				if(count == ConstantData.Batch) {
					break;
				}
				if(dirList1.higher(p1) == null) {
					break;
				}	
				//更新p1
				p1 = dirList1.higher(p1);
				//更新dirList2
				files =sftp.lsFIle(ConstantData.RemoteDir+p1);
				dirList2.clear(); 
				for(String f: files) {
					if(f.matches(regexp2))
						dirList2.add(f);
				}
				//更新p2
				p2 = "REC_00000000_0000";
				
				if(dirList2.size() == 0) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sftp.disconnect();
		}
		
		//开始下载
		if(dirForDownload.size() == 0) {
			System.out.println("No data!");
			sftp.disconnect();
			System.exit(1);
		}
		String nextPath = dirForDownload.first();
		String dir[] = null;
		ArrayList<String> pathlog = new ArrayList<String>();
		while(true) {
			dir = nextPath.split("/",2);
			new File(ConstantData.LocalDir + dir[1]).mkdir();
			try {
				sftp.downloadFolder(ConstantData.RemoteDir+ nextPath, ConstantData.LocalDir+ dir[1]);
			}catch (SftpException e) {
				sftp.disconnect();
				putPathlog(pathlog, sftp);
				System.exit(1);
			}
			pathlog.clear();
			pathlog.add(dir[0]);
			pathlog.add(dir[1]);
			if(dirForDownload.higher(nextPath) == null) {
				break;
			}
			nextPath = dirForDownload.higher(nextPath);
		}
		putPathlog(pathlog, sftp);
	}
	private static void getTrace() {
		//日志数据只有一级目录
		String dir1 = null;

		ArrayList<String> path = getPathLog();
		if(path.size()>0 && path.get(0).matches("^[0-9]{4}_[0-9]{2}_[0-9]{2}_[ap]{1}m$")) {
			dir1 = path.get(0);
		}

		//列出本次要下载的目录
		TreeSet<String> dirForDownload = new TreeSet<String>();
		JSchExecutor sftp = new JSchExecutor(ConstantData.User, ConstantData.Password, ConstantData.Host, ConstantData.Port);
		try {
			sftp.connect();

			//一级目录(2020_23_12_am)
			ArrayList<String> files =sftp.lsFIle(ConstantData.RemoteDir);
			TreeSet<String> dirList1 = new TreeSet<String>(); //TresSet可以保证字符串的字典序
			String regexp1="^[0-9]{4}_[0-9]{2}_[0-9]{2}_[ap]{1}m$";
			for(String f: files) {
				if(f.matches(regexp1))
					dirList1.add(f);
			}
			if(dirList1.size() == 0) {
				System.out.println("No data!");
				sftp.disconnect();
				System.exit(1);
			}
			if(dir1 == null || !dirList1.contains(dir1)) {
				dir1 = "0000_00_00_am";
			}
			
			//列出本次要下载的所有目录,从dir2的下一个目录开始下载
			int count =0;
			String p1 = dir1;
			while(true) {
				while(dirList1.higher(p1) != null && count != ConstantData.Batch) {
					p1 = dirList1.higher(p1);
					dirForDownload.add(p1);
					++count;
				}
				if(count == ConstantData.Batch) {
					break;
				}
				if(dirList1.higher(p1) == null) {
					break;
				}	
				//更新p1
				p1 = dirList1.higher(p1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sftp.disconnect();
		}
		
		//开始下载
		if(dirForDownload.size() == 0) {
			System.out.println("No data!");
			sftp.disconnect();
			System.exit(1);
		}
		String nextPath = dirForDownload.first();
		ArrayList<String> pathlog = new ArrayList<String>();
		while(true) {
			new File(ConstantData.LocalDir + nextPath).mkdir();
			try {
				sftp.downloadFolder(ConstantData.RemoteDir+ nextPath, ConstantData.LocalDir+ nextPath);
			}catch (SftpException e) {
				sftp.disconnect();
				putPathlog(pathlog, sftp);
				System.exit(1);
			}
			pathlog.clear();
			pathlog.add(nextPath);
			if(dirForDownload.higher(nextPath) == null) {
				break;
			}
			nextPath = dirForDownload.higher(nextPath);
		}
		putPathlog(pathlog, sftp);
	}
	public static void main(String[] args) {
		if(ConstantData.Batch<=0) {
			System.out.println("Invalid Batch!");
			System.exit(1);
		}
		
		if(ConstantData.Type.equals("rec")) {
			getRec();
		}
		else if(ConstantData.Type.equals("trace")) {
			getTrace();
		}
		
		
	}
}
