/*package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requestService.PassengerTableService;
import com.surbhi.webProject1.requests.DBConnection;
*//**
 * Servlet implementation class Register
 *//*
public class PassengerTable extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String uType,name,email;
	Date time;
	Date date;
	int total;
	int tickets;
	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public PassengerTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		PassengerTableService pts = new PassengerTableService();

		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("user");
		String name = (String)session.getAttribute("name");
		List<Passenger> passengerList = new ArrayList<Passenger>();
		
		if(userName==null){
			out.print("<p style='margin-top:50px;margin-left:40px'>Please login first!!!");  
			request.getRequestDispatcher("login").include(request, response); 
		}
		else{
			passengerList = pts.Passengers(userName);			
			
			
			TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/Templates", ".hbs");
			Handlebars handlebars = new Handlebars(loader);
			Template template = handlebars.compile("passengerTable");
			Map<String, Object> hmap = new HashMap<String, Object>();
			if(name==null)
				hmap.put("login",true);
			else{
				hmap.put("login",false);
				hmap.put("name", name);
			}
			hmap.put("passengerList", passengerList);
			out.print(template.apply(hmap));
		}

	}

}

*/