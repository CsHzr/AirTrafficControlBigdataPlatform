package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.App;

/**
 * Servlet implementation class TrackServlet
 */
@WebServlet("/sectors")
public class SectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pointList = gson.toJson(App.getApp().getRoutePartialPointList().getRoutePointList());
		String smallSectorList = gson.toJson(App.getApp().getRouteSmallSectorsList().getSmallSectors());
		String sectorList = gson.toJson(App.getApp().getRouteSectorsList().getRouteSectors());
//		String str = gson.toJson(App.getApp().getRouteSectorsList().getRouteSectors());
//		String str = pointList + "\r\n\r\n---------------------------------------------------------------------------------------------\r\n\r\n" + smallSectorList + 
//				"\r\n\r\n---------------------------------------------------------------------------------------------\r\n\r\n" + sectorList;
//		
		String str = sectorList;
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();	
	}

}
