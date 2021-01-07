package sftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import config.ConstantData;

public class JSchExecutor {
		private String localSep = File.separator;
		private String remoteSep = "/";
	 	private String user; // 用户名
	    private String passwd; // 登录密码
	    private String host; // 主机IP
	    private int port; //默认端口
	    
	    private JSch jsch;
	    private Session session;
	    private ChannelSftp sftp;
	    
	    public JSchExecutor(String user, String passwd, String host , int port ) {
	        this.user = user;
	        this.passwd = passwd;
	        this.host = host;
	        this.port = port;
	    }
	    /**
	              * 连接到指定的IP
	     *
	     * @throws JSchException
	     */
	    public void connect() throws JSchException {
	        jsch=new JSch();
            //给出连接需要的用户名，ip地址以及端口号
            session=jsch.getSession(user, host, port);
            //跳过host-key检查
            session.setConfig("StrictHostKeyChecking", "no");
           //设置是否超时
            session.setTimeout(ConstantData.Timeout);
           //设置密码
            session.setPassword(passwd);
            //创建连接
            session.connect();
            Channel channel = session.openChannel("sftp");
	        channel.connect();
	        sftp = (ChannelSftp)channel;
	    }
	    /**
	     	* 关闭连接
	     */
	    public void disconnect(){
	        if (sftp != null && sftp.isConnected()) {
	            sftp.disconnect();
	            sftp = null;
	        }
	        if(session != null && session.isConnected()){
	            session.disconnect();
	            session = null;
	        }
	    }
//	    /**
//	     * 上传文件
//	     */
//	    public void uploadFile(String local,String remote) throws Exception {
//	        File file = new File(local);
//	        if (file.isDirectory()) {
//	            throw new RuntimeException(local + "  is not a file");
//	        }
//
//	        InputStream inputStream = null;
//	        try {
//	            String rpath = remote.substring(0,remote.lastIndexOf("/")+1);
//	            if (!isDirExist(rpath)){
//	                createDir(rpath);
//	            }
//	            inputStream = new FileInputStream(file);
//	            sftp.setInputStream(inputStream);
//	            sftp.put(inputStream, remote);
//	        } catch (Exception e) {
//	            throw e;
//	        }finally{
//	            if(inputStream != null){
//	                inputStream.close();
//	            }
//	        }
//	    }
	    /**
	     * 下载文件
	     */
	    public void downloadFile(String remote,String local) throws Exception{
	        OutputStream outputStream = null;
	        try {
	            outputStream = new FileOutputStream(new File(local));
	            sftp.get(remote, outputStream);
	            outputStream.flush();
	        } catch (Exception e) {
	            throw e;
	        }finally{
	            if(outputStream != null){
	                outputStream.close();
	            }
	        }
	    }
	    public void downloadFolder(String sourcePath, String destinationPath) throws SftpException {
//	    	new File(destinationPath + localSep + sourcePath).mkdir();
	    	recursiveDownloadFolder(sourcePath, destinationPath);
	    }
	    public void recursiveDownloadFolder(String sourcePath, String destinationPath) throws SftpException {
	        Vector<ChannelSftp.LsEntry> fileAndFolderList = sftp.ls(sourcePath); // Let list of folder content
	        
	        //Iterate through list of folder content
	        for (ChannelSftp.LsEntry item : fileAndFolderList) {
	            
	            if (!item.getAttrs().isDir()) { // Check if it is a file (not a directory).
	                if (!(new File(destinationPath + localSep + item.getFilename())).exists()) {

//	                    new File(destinationPath + sep + item.getFilename());
	                    sftp.get(sourcePath + remoteSep + item.getFilename(),
	                    		destinationPath + localSep + item.getFilename()); // Download file from source (source filename, destination filename).
	                }
	            } else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
	                new File(destinationPath + localSep + item.getFilename()).mkdirs(); // Empty folder copy.
	                recursiveDownloadFolder(sourcePath + remoteSep + item.getFilename(),
	                		destinationPath + localSep + item.getFilename()); // Enter found folder on server to read its contents and create locally.
	            }
	        }
	    }
	    /**
	     * 移动到相应的目录下
	     * @param pathName 要移动的目录
	     * @return
	     */
	    public boolean changeDir(String pathName) throws Exception{
	        if(pathName == null || pathName.trim().equals("")){
	            return false;
	        }
	        try {
	            sftp.cd(pathName.replaceAll("\\\\", "/"));
	            return true;
	        } catch (SftpException e) {
	        	throw e;
	        }
	    }

	    /**
	     * 创建一个文件目录，mkdir每次只能创建一个文件目录
	     * 或者可以使用命令mkdir -p 来创建多个文件目录
	     */
	    public void createDir(String createpath) {
	        try {
	            if (isDirExist(createpath)) {
	                sftp.cd(createpath);
	                return;
	            }
	            String pathArry[] = createpath.split("/");
	            StringBuffer filePath = new StringBuffer("/");
	            for (String path : pathArry) {
	                if (path.equals("")) {
	                    continue;
	                }
	                filePath.append(path + "/");
	                if (isDirExist(filePath.toString())) {
	                    sftp.cd(filePath.toString());
	                } else {
	                    // 建立目录
	                    sftp.mkdir(filePath.toString());
	                    // 进入并设置为当前目录
	                    sftp.cd(filePath.toString());
	                }
	            }
	            sftp.cd(createpath);
	        } catch (SftpException e) {
	            throw new RuntimeException("创建路径错误：" + createpath);
	        }
	    }
	    public boolean isDirExist(String directory)
	    {
	        boolean isDirExistFlag = false;
	        try
	        {
	            SftpATTRS sftpATTRS = sftp.lstat(directory);
	            isDirExistFlag = true;
	            return sftpATTRS.isDir();
	        }
	        catch (Exception e)
	        {
	            if (e.getMessage().toLowerCase().equals("no such file"))
	            {
	                isDirExistFlag = false;
	            }
	        }
	        return isDirExistFlag;
	    }
	    //not include '.', '..'
	    public ArrayList<String> lsFIle(String path) throws Exception
	    {
	    	Vector vector=sftp.ls(path);
	    	ArrayList<String> rst = new ArrayList<String>();

	        for(Object obj:vector){
	            if(obj instanceof ChannelSftp.LsEntry){
	                String fileName=((ChannelSftp.LsEntry) obj).getFilename();
	                if (".".equals(fileName) || "..".equals(fileName)) {
	                    continue;
	                }
	                rst.add(fileName);
//	                channel.rm(fileName);
//	                logger.info("success delete the file :"+SftpConfigProperties.sftpFilePath+File.separator+fileName);
	            }
	        }
	        return rst;
	    }
}
