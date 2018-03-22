package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.requestService.BuyTicketService;
import com.surbhi.webProject1.requestService.PassengerTableService;
import com.surbhi.webProject1.requestService.RegisterService;
import com.surbhi.webProject1.requestService.UserValidService;

/**
 * Servlet implementation class Home
 */
public class Calls extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String file;
	String msg;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calls() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		file = (String) request.getParameter("file");
		if(file==null){
			getHbs(request,response,"home",null,null);
		}
		else if(file.equals("login")){
			loginPage(request,response);
		}
		else if(file.equals("signin")){
			signIn(request,response);
		}
		else if(file.equals("register")){
			registerPage(request,response);
		}
		else if(file.equals("logout")){
			logout(request,response);
		}
		else if(file.equals("profile")){
			profile(request,response);
		}
		else if(file.equals("registration")){
			registration(request,response);
		}
		else if(file.equals("buyTickets")){
			buyTickets(request,response);
		}
		else if(file.equals("passengers")){
			passengers(request,response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void getHbs(HttpServletRequest request, HttpServletResponse response,String file,String message,List<Passenger> passengerList) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");

		TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/Templates",".hbs");
		Handlebars handlebars = new Handlebars(loader);
		Template template = handlebars.compile(file);
		Map<String, Object> hmap = new HashMap<String, Object>();
		if(name==null)
			hmap.put("login",true);
		else{
			hmap.put("login",false);
			hmap.put("name", name);
		}
		if(message!=null){
			hmap.put("message",message);
		}
		if(passengerList!=null)
		hmap.put("passengerList", passengerList);
		out.print(template.apply(hmap));
	}
	public void loginPage(HttpServletRequest request, HttpServletResponse response){
		try {

			getHbs(request,response,"login",null,null);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void profile(HttpServletRequest request, HttpServletResponse response){
		try {

			HttpSession session=request.getSession(false);
			String name=(String)session.getAttribute("name"); 
			msg = "Hello, "+name+" Welcome to Profile";
			if(name!=null){  
				getHbs(request,response,"message",msg,null);

			}  
			else{  
				msg = "Please login first";
				getHbs(request,response,"message",msg,null);  
				getHbs(request,response,"login",null,null);
			}  

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void signIn(HttpServletRequest request, HttpServletResponse response){
		try {
			String uname = request.getParameter("uname");

			String password = request.getParameter("pass");
			UserValidService uv = new UserValidService();
			String result = uv.checkValid(uname,password);

			if(result.equals(uname)){
				msg = "No such username exists!!!"
						+ " Register or login with another username";
				getHbs(request,response,"message",msg,null);
				getHbs(request,response,"login",null,null);
				//request.getRequestDispatcher("login").include(request, response); 
			}
			else if(result.equals(password)){
				msg = "Wrong password entered";
				getHbs(request,response,"message",msg,null);
				getHbs(request,response,"login",null,null);
			}
			else {
				HttpSession session=request.getSession(); 
				session.setAttribute("name",result);
				session.setAttribute("user", uname);

				getHbs(request,response,"home",null,null);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void registerPage(HttpServletRequest request, HttpServletResponse response){
		try {
			getHbs(request,response,"registration",null,null);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response){
		try {
			msg ="you are successfully logged out!";

			HttpSession session=request.getSession();  
			session.invalidate();  		          

			getHbs(request,response,"message",msg,null);
			getHbs(request,response,"home",null,null);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void registration(HttpServletRequest request, HttpServletResponse response){
		try {
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
			
			if(result == false){
				msg = "This username is are already registered";
				getHbs(request,response,"message",msg,null);
				getHbs(request,response,"registration",null,null);
			}
			else{
				msg = "You are successfully registered!!! Login here";
				getHbs(request,response,"message",msg,null);
				getHbs(request,response,"login",null,null);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buyTickets(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session=request.getSession();
			String user =(String)session.getAttribute("user");
			
			String fname = request.getParameter("name");
			int Tick =Integer.parseInt(request.getParameter("tickets"));
			String Email =request.getParameter("email");
			String date =(String)request.getParameter("date");
			String Time =(String)request.getParameter("time");	
			BuyTicketService buy = new BuyTicketService();
			buy.booking(user,fname,Tick,Email,date,Time);
			msg = "Your tickets are Booked and will be sent to your mail "+Email;	
			getHbs(request,response,"message",msg,null);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void passengers(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			
			PassengerTableService pts = new PassengerTableService();

			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("user");
			List<Passenger> passengerList = new ArrayList<Passenger>();
			
			if(userName==null){
				msg = "Please login first!!!";
				getHbs(request,response,"message",msg,null);
				getHbs(request,response,"login",null,null);
			 
			}
			else{
				passengerList = pts.Passengers(userName);			
				
				getHbs(request,response,"passengerTable",null,passengerList);
			}
			hmap.put("passengerList", passengerList);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
