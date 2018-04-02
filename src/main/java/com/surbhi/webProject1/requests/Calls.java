package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
public class Calls extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String uname;
	String file;
	String msg;
    //private String excludedUrlsRegex;

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
	//	PrintWriter out = response.getWriter();
		Calls c= new Calls();
		String path = request.getPathInfo();
		System.out.println("path "+ path);
		
		if(path==null||path.equals("/")){
			getHbs(request,response,"home",null);
		}
		
		
		else{
			try {
				String ur = path.replace("/", "");
				Method method = Calls.class.getDeclaredMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
//				System.out.println("method is "+method);
//				System.out.println("method name is "+method.getName());
				
				method.invoke(c,request,response);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void getHbs(HttpServletRequest request, HttpServletResponse response,String file,Map<String, Object> hmap) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");

		TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/WEB-INF/Templates",".hbs");
		Handlebars handlebars = new Handlebars(loader);
		Template template = handlebars.compile(file);
		if(hmap==null)
			hmap  = new HashMap<String, Object>();
		if(name==null){
			String bgcolor = "#000000";
			hmap.put("bgcolor", bgcolor);
			hmap.put("login",true);
		}

		else{
			String bgcolor =(String)session.getAttribute("bgcolor");
			hmap.put("bgcolor", bgcolor);
			hmap.put("login",false);
			hmap.put("name", name);
		}

		out.print(template.apply(hmap));
	}
	public void login(HttpServletRequest request, HttpServletResponse response){
		try {

			getHbs(request,response,"login",null);
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
				Map<String, Object> hmap  = new HashMap<String, Object>();
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);

			}  
			else{  
				msg = "Please login first";
				Map<String, Object> hmap  = new HashMap<String, Object>();
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);  
				getHbs(request,response,"login",null);
			}  

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void signin(HttpServletRequest request, HttpServletResponse response){
		try {

			String uname = request.getParameter("uname");

			String password = request.getParameter("pass");
			UserValidService uv = new UserValidService();
			String[] result = uv.checkValid(uname,password);
			Map<String, Object> hmap  = new HashMap<String, Object>();

			if(result[0].equals(uname)){
				msg = "No such username exists!!!"
						+ " Register or login with another username";

				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);

				getHbs(request,response,"login",null);

			}
			else if(result[0].equals(password)){
				msg = "Wrong password entered";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}
			else {

				HttpSession session=request.getSession(); 
				session.setAttribute("name",result[0]);
				session.setAttribute("user", uname);
				String bgcolor =result[1];
				session.setAttribute("bgcolor",bgcolor);

				getHbs(request,response,"home",null);

			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void register(HttpServletRequest request, HttpServletResponse response){
		try {
			getHbs(request,response,"registration",null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response){
		try {

			HttpSession session=request.getSession();  
			session.invalidate();  	
			
			response.sendRedirect("/");
			
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
			String bgcolor = "#000000";
			RegisterService rs = new RegisterService();
			Boolean result = rs.registerUser(fname, lname, uname,country,city,mobile,password,gender,dob,bgcolor);
			Map<String, Object> hmap  = new HashMap<String, Object>();
			if(result == false){

				msg = "This username is are already registered";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"registration",null);
			}
			else{
				msg = "You are successfully registered!!! Login here";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
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

			Map<String, Object> hmap  = new HashMap<String, Object>();

			String fname = request.getParameter("name");
			int Tick =Integer.parseInt(request.getParameter("tickets"));
			String Email =request.getParameter("email");
			String Date =(String)request.getParameter("date");
			String Time =(String)request.getParameter("time");
			String Place =(String)request.getParameter("place");
			String pid = (String)request.getParameter("pid");
			BuyTicketService buy = new BuyTicketService();
			System.out.println(fname);
			System.out.println(Email);
			System.out.println(Date);
			System.out.println(Tick);
			System.out.println(pid);
			buy.bookPassenger(pid,user,fname,Tick,Email,Date,Time,Place);

			msg = "Your tickets are Booked and will be sent to your mail "+Email;	
			hmap.put("message", msg);
			getHbs(request,response,"message",hmap);

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
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}
			else{
				passengerList = pts.Passengers(userName);	
				System.out.println("\n Passenger List is \n" + passengerList);
				hmap.put("passengerList", passengerList);
				getHbs(request,response,"passengerTable",hmap);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void settings(HttpServletRequest request, HttpServletResponse response){
		try {
			getHbs(request,response,"settings",null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void color(HttpServletRequest request, HttpServletResponse response){
		try {

			RegisterService registerService = new RegisterService();
			HttpSession session = request.getSession();
			uname = (String) session.getAttribute("user");
			//PrintWriter out = response.getWriter();
			Map<String, Object> hmap = new HashMap<String, Object>();
			String bgcolor =(String)request.getParameter("bgcolor");

			session.setAttribute("bgcolor",bgcolor);

			getHbs(request,response,"settings",hmap);
			registerService.updateColor(bgcolor,uname);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void editpassenger(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			BuyTicketService buyTicketService = new BuyTicketService();
			String id = request.getParameter("id");
			List<Passenger> passengerDetails = new ArrayList<Passenger>();
			passengerDetails = buyTicketService.editPassenger(id);
			System.out.println("Passenger Details is "+passengerDetails);
		hmap.put("passengerDetails", passengerDetails);
			getHbs(request,response,"passengerTable",hmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
