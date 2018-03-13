/*package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
		String fname = request.getParameter("name");
		int Tick =Integer.parseInt(request.getParameter("tickets"));
		String Email =request.getParameter("email");
		String Date =(String)request.getParameter("date");
		String Time =(String)request.getParameter("time");		
		System.out.println("My name "+fname);
		DB mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		//---------- Creating Collection ------------
		DBCollection collection = mongo.getCollection("passenger");
		HttpSession session=request.getSession(); 
		String user =(String)session.getAttribute("user");
		Document doc=new Document();
		doc.put("LoginUser",user);
		doc.put("name", fname);
		doc.put("tickets", Tick);
		doc.put("email", Email);
		doc.put("date",Date );
		doc.put("time",Time );
		doc.put("TotalPay","$"+23*Tick);
		collection.insert(doc);
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