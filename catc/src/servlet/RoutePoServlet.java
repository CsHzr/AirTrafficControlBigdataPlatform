package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.App;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class RoutePoServlet
 */
@WebServlet("/routepoint")
public class RoutePoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoutePoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String str = gson.toJson(App.getApp().getRoutePointList().getRoutePoints());
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();	
	}
}
