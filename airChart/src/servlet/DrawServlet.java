package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import echarts.EctDemo1Obj;
import echarts.EctDemo2Obj;


@WebServlet("/draw")
public class DrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
	public DrawServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> map =req.getParameterMap();
		String value = map.get("type")[0];
		String str = "";
		if(value.equals("demo1")) {
			EctDemo1Obj demo = new EctDemo1Obj();
			demo.getFromHBase();
			//demo.noHBase();
			str = gson.toJson(demo);
			//System.out.println(str);
		}
		if(value.equals("demo2")) {
			EctDemo2Obj demo = new EctDemo2Obj();
			demo.getFromHBase();
			//demo.noHBase();
			str = gson.toJson(demo);
			//System.out.println(str);
		}
		resp.setContentType("text/html;charset=utf-8");	//text/json
		PrintWriter pw = resp.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();	
	}
	static public class DrawObject{
		public ArrayList<Object> x = new ArrayList<Object>();
		public ArrayList<Object> y = new ArrayList<Object>();
	}
}
