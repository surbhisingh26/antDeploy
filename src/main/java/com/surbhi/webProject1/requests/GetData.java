/*package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mongojack.JacksonDBCollection;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.requestService.BuyTicketService;
import com.surbhi.webProject1.requests.DBConnection;


*//**
 * Servlet implementation class GetData
 *//*
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public GetData() {
		super();
		// TODO Auto-generated constructor stub
	}

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*


	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String user =(String)session.getAttribute("user");
		
		String fname = request.getParameter("name");
		int Tick =Integer.parseInt(request.getParameter("tickets"));
		String Email =request.getParameter("email");
		String date =(String)request.getParameter("date");
		String Time =(String)request.getParameter("time");	
		BuyTicketService buy = new BuyTicketService();
		buy.booking(user,fname,Tick,Email,date,Time);
				
		PrintWriter writer = response.getWriter();
		String htmlResponse = "<html>";
		htmlResponse += "<h2>Welcome: " + fname + "</h2>";
		htmlResponse +="<h3>You have booked " + Tick + " tickets</h3>";
		htmlResponse +="<h3>We will send your tickets on your mail " + Email + "</h3>";
		htmlResponse +="<h3>You have to pay a total of $" + 23*Tick + "</h3>";
		htmlResponse += "</html>";
		writer.println(htmlResponse);
	}

}
*/