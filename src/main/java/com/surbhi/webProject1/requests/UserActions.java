package com.surbhi.webProject1.requests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requestService.UserService;
import com.surbhi.webProject1.requestService.UserValidService;

/**
 * Servlet implementation class UserActions
 */
public class UserActions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utility utility = new Utility();
	String uid;
	String msg;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserActions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserActions useraction = new UserActions();


		String path = request.getPathInfo();
		System.out.println("path "+ path);


		if(path==null||path.equals("/")){
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			utility.getHbs(response,"home",hmap);
		}

		else{
			try {
				String ur = path.replace("/", "");
				Method method = UserActions.class.getDeclaredMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
				//				System.out.println("method is "+method);
				//				System.out.println("method name is "+method.getName());

				method.invoke(useraction,request,response);
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
	}
	public void login(HttpServletRequest request, HttpServletResponse response){
		try {

			utility.getHbs(response,"login",null);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void profile(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap=utility.checkSession(request);
			uid = (String) hmap.get("uid");
			System.out.println("uid in profile" +uid);
			if(uid!=null){  

				hmap.put("profile",true);
				utility.getHbs(response,"message",hmap);

			}  
			else{  
				msg = "Please login first";

				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);  
				utility.getHbs(response,"login",null);
			}  

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void signin(HttpServletRequest request, HttpServletResponse response){
		try {

			String uname = request.getParameter("uname");
			//System.out.println("username "+uname);
			if(uname==null)
				uname="";
			String password = request.getParameter("pass");
			if(password==null)
				password="";
			String reference = request.getParameter("reference");
			if(reference==null)
				reference = "No reference";
			String referenceId = request.getParameter("referenceId");
			if(referenceId==null)
				referenceId="null";
			System.out.println("reference is "+reference);
			System.out.println("ref Id is "+referenceId);
			UserValidService uv = new UserValidService();
			String result = uv.checkValid(uname,password,reference,referenceId);
			Map<String, Object> hmap  = new HashMap<String, Object>();
			//System.out.println(request.getParameter("firstname"));
			System.out.println(result);
			if(result.equals(uname)){
				msg = "No such username exists!!!"
						+ " Register or login with another username";

				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);

				utility.getHbs(response,"login",null);

			}
			else if(result.equals(password)){
				msg = "Wrong password entered";
				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);
				utility.getHbs(response,"login",null);
			}
			
			else if(result.equals("Register First")){
				hmap.put("register", true);
				//utility.getHbs(response,"registration",hmap);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(hmap));
				
				
			}
			else {
				
				Cookie loginCookie = new Cookie("uid",result);

				System.out.println("uid is "+result);
				response.addCookie(loginCookie);
				loginCookie.setMaxAge(30*60); 			
				UserService userservice = new UserService();
				Boolean loggedIn = userservice.login(result);
				hmap.put("LoggedIn", loggedIn);
				
				hmap.put("register", false);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(hmap));
			    if(referenceId.equals("null"))
			    response.sendRedirect("/webProject1");
			    
			}
			

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void register(HttpServletRequest request, HttpServletResponse response){
		try {
			System.out.println("I am here in register...");
			utility.getHbs(response,"registration",null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response){
		try {
			
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
		String reference = request.getParameter("reference");
		
			
			
			Cookie loginCookie=new Cookie("uid","");  
			loginCookie.setMaxAge(0);  
			response.addCookie(loginCookie); 
			
			UserService userservice = new UserService();
			userservice.logout(uid);

			//System.out.println(request.getContextPath());
			//request.getRequestDispatcher("").forward(request, response);
			if(reference==null)
			response.sendRedirect("/webProject1");
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
			String email = request.getParameter("email");
			String dob = request.getParameter("dob");
			String mobile =request.getParameter("mobile");
			String password = request.getParameter("pass");
			
			String reference = request.getParameter("reference");
			String referenceId = request.getParameter("referenceId");
			String gender = request.getParameter("gender");
			String bgcolor = "#000000";
			String rootPath = System.getProperty("catalina.home");
			String savePath = rootPath + File.separator + "webapps/images";
			File fileSaveDir=new File(savePath);
			//File file = new File(rootPath + File.separator + "images");
			//File fileSaveDir=new File(file);
			if(!fileSaveDir.exists()){
				fileSaveDir.mkdir();
			}
			//String imagePath= fileSaveDir + File.separator + "default.jpg";
			String filePath=null;
			if(reference.equals("fb")){
				filePath = request.getParameter("picUrl");
			}
			else
				filePath = File.separator +"images" + File.separator + "default.jpg";
			
			UserService rs = new UserService();
			Boolean result = rs.registerUser(fname, lname, uname,country,city,mobile,password,gender,dob,bgcolor,filePath,email,reference,referenceId);
			Map<String, Object> hmap  = new HashMap<String, Object>();
			if(result == false){

				msg = "This username is are already registered";
				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);
				utility.getHbs(response,"registration",null);
			}
			else{
				msg = "You are successfully registered!!! Login here";
				hmap.put("message", msg);
				
				if(!referenceId.equals("null")){
					response.sendRedirect("/webProject1");
				}
				else
				{
					utility.getHbs(response,"message",hmap);
					utility.getHbs(response,"login",null);
				}
					
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void settings(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			if(uid==null){
				msg = "Please login first!!!";
				hmap.put("message", msg);
				utility.getHbs(response,"message",hmap);
				utility.getHbs(response,"login",null);
			}else{


				utility.getHbs(response,"settings",hmap);
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
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			System.out.println("coloring "+uid);
			String bgcolor =(String)request.getParameter("bgcolor");
			System.out.println(bgcolor);
			User user = registerService.updateColor(bgcolor,uid);
			hmap.put("loggedInUser", user);
			utility.getHbs(response,"settings",hmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void profilepic(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			uid = (String) hmap.get("uid");
			System.out.println("profile pic "+uid);
			String rootPath = System.getProperty("catalina.home");
			String savePath = rootPath + File.separator + "webapps/images";
			File fileSaveDir=new File(savePath);


			Part file = request.getPart("file");
			String path = request.getParameter("filename");
			String fileName = path.replace("C:\\fakepath\\", "");
			file.write(fileSaveDir + File.separator + fileName);
			String filePath= File.separator +"images" + File.separator + fileName;
			UserService userService = new UserService();
			User user = userService.updatePic(filePath,uid);

			hmap.put("loggedInUser",user);
			System.out.println("filepath  ...  "+filePath);

			utility.getHbs(response,"settings",hmap);


		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	}
