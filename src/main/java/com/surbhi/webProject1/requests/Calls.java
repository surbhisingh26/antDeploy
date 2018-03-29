package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
public class Calls extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	String uname;
	String file;
	String msg;
    private String excludedUrlsRegex;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calls() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(FilterConfig config) throws ServletException {
		System.out.println("initializing...");
		this.excludedUrlsRegex = config.getInitParameter("[.js|.css]");
        System.out.println("excludedUrlsRegex <><><> " + excludedUrlsRegex);


	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {

		String path=((HttpServletRequest) request).getRequestURI();
		System.out.println("Filtering path "+ path);
	/*	if (!path.contains(".")) {		
			chain.doFilter(request, response); 
			return;
		}	*/
		
		if(!path.matches(".*(css|jpg|png|gif|js)")){
		    chain.doFilter(request, response);
		    return;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		Calls c= new Calls();
		//out.println("1"+request.getPathInfo());
		String path=request.getPathInfo();
		/*if (path.contains(".")) {
		    chain.doFilter(request, response); // Just continue chain.
		}*/
		String ur = path.replace("/", "");
		//out.println("2 "+ur);

		if(ur.equals("")){
			getHbs(request,response,"home",null);
		}
		else{
			try {

				Method method = Calls.class.getMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
				//out.print(method);

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

			//Map<String, Object> hmap  = new HashMap<String, Object>();
			HttpSession session=request.getSession();  
			session.invalidate();  		          
			response.sendRedirect("/webProject1");
			/*getHbs(request,response,"message",hmap);
			getHbs(request,response,"home",null);*/

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
			String date =(String)request.getParameter("date");
			String Time =(String)request.getParameter("time");

			BuyTicketService buy = new BuyTicketService();
			buy.booking(user,fname,Tick,Email,date,Time);

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
}
