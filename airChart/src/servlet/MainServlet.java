package servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import main.App;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(name="MainServlet",urlPatterns="/MainServlet",loadOnStartup=0)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		App.getApp().init();
	}
}
