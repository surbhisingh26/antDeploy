/*package com.surbhi.webProject1.requests;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surbhi.webProject1.requestService.RegisterService;
*//**
 * Servlet implementation class Register
 *//*
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public Register() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("username");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String dob = request.getParameter("dob");
		String mobile =request.getParameter("mobile");
		String password = request.getParameter("pass");
		String gender = request.getParameter("gender");
		
		RegisterService rs = new RegisterService();
		Boolean result = rs.register(fname, lname, uname,country,city,mobile,password,gender,dob);
		PrintWriter writer = response.getWriter();
		if(result == false){
			String htmlResponse = "<html>";
			htmlResponse += "<h2>This username is are already registered <a href='Templates/login.hbs'>Login here</a> or<br><a href = 'Templates/registration.hbs'>register </a>with another username </h2>";
			htmlResponse += "</html>";
			writer.println(htmlResponse);
		}
		else{
			String htmlResponse = "<html>";
			htmlResponse += "<h2>You are successfully registered <a href='Templates.login.hbs'>Login here</a> </h2>";
			htmlResponse += "</html>";
			writer.println(htmlResponse);
		}
		PrintWriter writer = response.getWriter();
		String htmlResponse = "<html>";
		htmlResponse += "<h2>"+ username+"</h2>";
		htmlResponse += "</html>";
		writer.println(htmlResponse);

	}

}
*/