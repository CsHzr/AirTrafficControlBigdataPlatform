package servlet;

import java.awt.Choice;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AppLogger;
import thread.ThreadFplNet;
import thread.ThreadFrdpNet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.TextFormat.ParseException;

import data.BestUtils;
import data.ConstantData;
import data.FplDb;
import data.TrackDb;
import hbs.DataUtils;
import hbs.HBSUtils;
import hbs.Record;
import javafx.util.Pair;
import main.App;

/**
 * Servlet implementation class BatchServlet
 */
@WebServlet("/batch")
public class BatchServlet extends HttpServlet {
	
	enum legacyType {track,strack};
	enum newType {fpl};
	static String[] legacyTypeStrArr = {"track", "strack"};
	static String[] newTypeStrArr = {"fpl"};
	static String newUrlSample = "/batch?type=fpl&&start=191020&&end=191020";
	static String oldUrlSample = "/batch?type=track&&date=20190306";
	
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String requestTypeString = request.getParameter("type");
		if(requestTypeString == null) {
			pw.write("缺少参数 type</br>示例：</br>"+newUrlSample+"</br>"+oldUrlSample);
			return;
		}
		String requestStartDateString = null;
		String requestEndDateString = null;
		String requestDateString = null;
		try 
		{
			if(!Arrays.asList(newTypeStrArr).contains(requestTypeString) 
				&& !Arrays.asList(legacyTypeStrArr).contains(requestTypeString)) {
				pw.write("数据类型无效，以下是有效的类型：");
				for(String type : newTypeStrArr) {
					pw.write("</br>"+type);
				}
				for(String type : legacyTypeStrArr) {
					pw.write("</br>"+type);
				}
				return;
			}
			//new
			if(Arrays.asList(newTypeStrArr).contains(requestTypeString)) {
				requestStartDateString = request.getParameter("start");
				requestEndDateString = request.getParameter("end");
				if(requestTypeString == null || requestStartDateString ==null || requestEndDateString ==null)
				{
					pw.write("缺少参数，示例："+newUrlSample);
					return;
				}
				newType requestType = newType.valueOf(requestTypeString);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
				if(requestStartDateString != null && requestEndDateString != null) {
					Date requestStartDate = sdf.parse(requestStartDateString);
					Date requestEndDate = sdf.parse(requestEndDateString);
					String data = getData(requestType, sdf.format(requestStartDate), sdf.format(requestEndDate), pw);
					pw.write(data);
				}
			}
			//legacy
			if(Arrays.asList(legacyTypeStrArr).contains(requestTypeString)){
				requestDateString = request.getParameter("date");
				legacyType requestType = legacyType.valueOf(requestTypeString);
				
				String fileSeparator = System.getProperty("file.separator");
				String userHome = System.getProperty("user.home");
				File file = new File(userHome+fileSeparator+requestTypeString+"_"+requestDateString);
				if(!file.exists())
				{
					System.out.println("batch return");
					return;
				}
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				byte[] bufHeader = "FFFF00".getBytes();
				byte[] bufReaded = new byte[2048];
				int readedLength = -1;
				byte[] bufTail = null;
				byte[] bufSeg = null;
				
				pw.write("[");
				while((readedLength = bis.read(bufReaded)) != -1)
				{
					if(bufTail != null)
					{
						bufSeg = new byte[bufTail.length+readedLength];
						System.arraycopy(bufTail, 0, bufSeg, 0, bufTail.length);
						System.arraycopy(bufReaded, 0, bufSeg, bufTail.length, readedLength);
						bufTail = null;
					}
					else
						bufSeg = Arrays.copyOfRange(bufReaded, 0, readedLength);
					
					int startIndex = 0;
					int headerIndex = -1;
					int oldHeaderIndex = -1;
					inner:
					while(startIndex < bufSeg.length)
					{
						oldHeaderIndex = headerIndex;
						headerIndex = BestUtils.getByteIndexOf(bufSeg, bufHeader, startIndex);
						if(headerIndex == -1)
							break inner;
		
						if(oldHeaderIndex!=-1 && headerIndex>oldHeaderIndex)
						{
							String dateString = new String(bufSeg, oldHeaderIndex+6, 13);
							Calendar recordDate = Calendar.getInstance();
							recordDate.setTimeInMillis(Long.parseLong(dateString));
							decode(pw, requestType, recordDate, bufSeg, oldHeaderIndex+19, headerIndex-oldHeaderIndex-19);
						}
						
						startIndex = headerIndex+19;
					}	
					if(oldHeaderIndex!=-1 && headerIndex==-1 && oldHeaderIndex<readedLength)
					{
						bufTail = Arrays.copyOfRange(bufReaded, oldHeaderIndex, readedLength);
					}
				}
	
				pw.write("]");
				pw.flush();
				bis.close();
			}
			System.out.println("Batch response finished!");
				
		} catch (java.text.ParseException e) {
			if(Arrays.asList(newTypeStrArr).contains(requestTypeString)) {
				pw.write("日期格式不正确！示例"+newUrlSample);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			pw.flush();
			pw.close();
		}	
	}
	private String getData(newType requestType, String start, String end, PrintWriter pw) {
		ConcurrentHashMap<String, FplDb> res = new ConcurrentHashMap<String, FplDb>();
		switch(requestType) {
		case fpl:
			res = DataUtils.getDataUtils().getFpl(start, end);
			break;
		default:
			break;
		}
		
		Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
		return gson.toJson(res);
	}
	public void decode(PrintWriter pw, newType requestType, Calendar recordDate, 
			byte[] bufData, int dataIndex, int dateLength) throws Exception
	{
		switch(requestType)
		{
		case fpl:	
			FplDb fd = ThreadFplNet.decodeFpl(bufData, dataIndex, 
				dateLength, false);
			if(fd != null)
			{
				pw.write(gson.toJson(fd));
				pw.write(",");
				pw.flush();
			}
			break;
		default:
			break;
		}
			
	}
	public void decode(PrintWriter pw, legacyType requestType, Calendar recordDate, 
		byte[] bufData, int dataIndex, int dateLength) throws Exception
	{
		switch(requestType)
		{
		case track:
		case strack:
			ArrayList<TrackDb> tds = ThreadFrdpNet.decodeCat062(bufData, dataIndex, 
				dateLength, false);
			for(TrackDb td: tds)
			{
				double realUpdateSeconds = td.getRealUpdateSeconds();
				int hour = ((int)realUpdateSeconds)/3600;
				int min = ((int)realUpdateSeconds)/60%60;
				int sec = ((int)realUpdateSeconds)%60;
				Calendar updateCal = Calendar.getInstance();
				updateCal.set(recordDate.get(Calendar.YEAR), recordDate.get(Calendar.MONTH), 
						recordDate.get(Calendar.DAY_OF_MONTH), hour, min, sec);
				if(hour >= 16) //时间跨天处理，日期-1，转为为UTC日期
					updateCal.add(Calendar.DAY_OF_MONTH, -1);
				td.setUpdateTime(updateCal.getTimeInMillis());
				td.setUpdateTime(recordDate.getTimeInMillis());
				pw.write(gson.toJson(td));
				pw.write(",");
				pw.flush();
			}
			break;
		default:
			break;
		}
	}
}
