package hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import data.ConstantData;

public class HDFSUtils {

    

    private static FileSystem getfileSystem() throws IOException, InterruptedException, URISyntaxException{
        // 获取一个具体的文件系统对象
    	FileSystem fileSystem = FileSystem.get( 
        		new URI(ConstantData.HDFSConfig),    
                new  Configuration());
                
        return fileSystem;
    }
    

    public static void getFile(String remote, String local) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException{
    	FileSystem fileSystem = getfileSystem();

        FSDataInputStream in = fileSystem.open(new Path(remote));
        
        FileOutputStream out = new FileOutputStream(local);
        
        /**
         * 参数说明：
         *     in 
         *         代表输入流，读取HDFS文件系统的文件到本机内存中
         *     out
         *         代表输出流，将本机内存中的文件写入到本地磁盘中
         *     4096
         *         缓冲区大小
         *     true    
         *         自动关闭流，如果不使用自动关闭的话需要手动关闭输入输出流
         *         
         *         手动关闭输入输出流：
         *             IOUtils.closeStream(in);
         *            IOUtils.closeStream(out);
         */
        IOUtils.copyBytes(in, out, 4096, true);
        
    }
    
    /**
     * 上传文件到HDFS文件系统
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws URISyntaxException 
     * @throws InterruptedException 
     */
    public static void putFile(String source, String dest) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException{
    	FileSystem fileSystem = getfileSystem();
        // 构建一个输入流，将本机需要上传的文件写入到内存中
        FileInputStream in = new FileInputStream(source);
        
        // 构建一个输出流，将客户端内存的数据写入到HDFS文件系统指定的路径中
        FSDataOutputStream out = fileSystem.create(new Path(dest), true);
        
        /*
         *    参数说明：
         *     in 
         *         代表输入流，读取HDFS文件系统的文件到本机内存中
         *     out
         *         代表输出流，将本机内存中的文件写入到本地磁盘中
         *     4096
         *         缓冲区大小
         *     true    
         *         自动关闭流，如果不使用自动关闭的话需要手动关闭输入输出流
         *         
         *         手动关闭输入输出流：
         *             IOUtils.closeStream(in);
         *            IOUtils.closeStream(out);
         */
        IOUtils.copyBytes(in, out, 4096, true);
        
    }
    
    public static boolean makeDir(String dir) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException{
    	FileSystem fileSystem = getfileSystem();
        return fileSystem.mkdirs(new Path(dir));
    }
    
    //递归删除
    public static void delFile(String dir) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException{
    	FileSystem fileSystem = getfileSystem();
    	fileSystem.delete(new Path(dir), true);
    }
}
