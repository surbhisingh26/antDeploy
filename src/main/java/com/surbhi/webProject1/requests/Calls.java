package com.surbhi.webProject1.requests;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requestService.BuyTicketService;
import com.surbhi.webProject1.requestService.PassengerTableService;
import com.surbhi.webProject1.requestService.UserService;
import com.surbhi.webProject1.requestService.UserValidService;

/**
 * Servlet implementation class Home
 */

@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*5)
public class Calls extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String uid = null;
	String file;
	String msg;
	//private String excludedUrlsRegex;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calls() {
		super();
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
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap = checkSession(request, response);
			getHbs(request,response,"home",hmap);
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

		System.out.println("hbs1" +uid);
		TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/WEB-INF/Templates",".hbs");
		Handlebars handlebars = new Handlebars(loader);
		Template template = handlebars.compile(file);
		if(hmap==null){
			System.out.println("hbs" +uid);

			hmap  = new HashMap<String, Object>();}
		if(uid==null){
			System.out.println("hbs3" +uid);

			String bgcolor = "#000000";
			hmap.put("bgcolor", bgcolor);
			hmap.put("login",true);
		}

		else{
			System.out.println("hbs4" +uid);


			//			hmap.put("bgcolor", bgcolor);
			hmap.put("login",false);
			//			hmap.put("name", name);
			//			hmap.put("image", image);

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
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap=checkSession(request, response);
			
			if(uid!=null){  

				hmap.put("profile",true);
				getHbs(request,response,"message",hmap);

			}  
			else{  
				msg = "Please login first";

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
			String result = uv.checkValid(uname,password);
			Map<String, Object> hmap  = new HashMap<String, Object>();

			if(result.equals(uname)){
				msg = "No such username exists!!!"
						+ " Register or login with another username";

				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);

				getHbs(request,response,"login",null);

			}
			else if(result.equals(password)){
				msg = "Wrong password entered";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}
			else {

				Cookie loginCookie = new Cookie("uid",result);
				//uid=result;
				
				response.addCookie(loginCookie);
				loginCookie.setMaxAge(30*60); 			
				
				hmap=checkSession(request,response);
				System.out.println("signin" +uid);
				getHbs(request,response,"home",hmap);
				

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

			Cookie loginCookie=new Cookie("uid","");  
			loginCookie.setMaxAge(0);  
			response.addCookie(loginCookie); 	
			uid=null;
			//System.out.println(request.getContextPath());
			request.getRequestDispatcher("").forward(request, response);
			//response.sendRedirect("/");
			return;
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
			String rootPath = System.getProperty("catalina.home");
			String savePath = rootPath + File.separator + "images";
			File fileSaveDir=new File(savePath);
			//File file = new File(rootPath + File.separator + "images");
			//File fileSaveDir=new File(file);
			if(!fileSaveDir.exists()){
				fileSaveDir.mkdir();
			}
			String imagePath= fileSaveDir + File.separator + "default.jpg";
			UserService rs = new UserService();
			Boolean result = rs.registerUser(fname, lname, uname,country,city,mobile,password,gender,dob,bgcolor,imagePath);
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
			
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap=checkSession(request,response);
			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}
			else{
				
			
			String fname = request.getParameter("name");
			int Tick =Integer.parseInt(request.getParameter("tickets"));
			String Email =request.getParameter("email");
			String Date =(String)request.getParameter("date");
			String Time =(String)request.getParameter("time");
			String Place =(String)request.getParameter("place");
			String pid = (String)request.getParameter("pid");
			System.out.println("pid is   "+pid);
			BuyTicketService buy = new BuyTicketService();
			buy.bookPassenger(pid,uid,fname,Tick,Email,Date,Time,Place);
			request.getRequestDispatcher("passengers").forward(request,response);
			//response.sendRedirect("passengers");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void passengers(HttpServletRequest request, HttpServletResponse response){
		try {

			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = checkSession(request, response);
			PassengerTableService pts = new PassengerTableService();

			List<Passenger> passengerList = new ArrayList<Passenger>();

			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}
			else{
				passengerList = pts.Passengers(uid);	
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
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = checkSession(request, response);
			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				getHbs(request,response,"message",hmap);
				getHbs(request,response,"login",null);
			}else{
			
			
			getHbs(request,response,"settings",hmap);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public void color(HttpServletRequest request, HttpServletResponse response){
		try {

			UserService registerService = new UserService();
			
			Map<String, Object> hmap = new HashMap<String, Object>();
			checkSession(request, response);
			System.out.println("coloring "+uid);
			String bgcolor =(String)request.getParameter("bgcolor");
			System.out.println(bgcolor);
			User user = registerService.updateColor(bgcolor,uid);
			hmap.put("loggedInUser", user);
			getHbs(request,response,"settings",hmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response){
		try {
			
			BuyTicketService bts = new BuyTicketService();
			String pid = request.getParameter("pid");
			bts.deletePassenger(pid);
			request.getRequestDispatcher("passengers").forward(request, response);;
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void profilepic(HttpServletRequest request, HttpServletResponse response){
		try {
			checkSession(request, response);
			System.out.println("profile pic "+uid);
			String rootPath = System.getProperty("catalina.home");
			String savePath = rootPath + File.separator + "webapps/images";
			File fileSaveDir=new File(savePath);

			if(!fileSaveDir.exists()){
				fileSaveDir.mkdir();
			}

			Part file = request.getPart("file");
			String path = request.getParameter("filename");
			String fileName = path.replace("C:\\fakepath\\", "");
			file.write(fileSaveDir + File.separator + fileName);
			String filePath= File.separator +"images" + File.separator + fileName;
			UserService userService = new UserService();
			User user = userService.updatePic(filePath,uid);
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("loggedInUser",user);
			System.out.println("filepath  ...  "+filePath);

			getHbs(request,response,"settings",hmap);


		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public Map<String, Object> checkSession(HttpServletRequest request, HttpServletResponse response){

		Cookie[] cookies = request.getCookies();
		System.out.println("cookies "+cookies);
		if(cookies !=null){
			for(Cookie cookie : cookies){
				System.out.println("cookies "+cookie);

				if(cookie.getName().equals("uid")){
					uid = cookie.getValue();

				}}
		}
		System.out.println("cookie "+uid);
		UserService userservice = new UserService();
		User user = userservice.findOneById(uid);
		Map<String, Object> hmap  = new HashMap<String, Object>();
		hmap.put("loggedInUser", user);
		return hmap;
	}


}
