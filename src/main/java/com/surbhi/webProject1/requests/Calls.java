package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.surbhi.webProject1.requestService.UserValidService;

/**
 * Servlet implementation class Home
 */
public class Calls extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String file = "home";
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
		file = (String) request.getParameter("file");

		if(file.equals("home")){
			getHbs(request,response,"home");
		}
		else if(file.equals("login")){
			login(request,response);
		}
		else if(file.equals("signin")){
			signIn(request,response);
		}
		else if(file.equals("register")){
			register(request,response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void getHbs(HttpServletRequest request, HttpServletResponse response,String file) throws ServletException, IOException {
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
		out.print(template.apply(hmap));
	}
	public void login(HttpServletRequest request, HttpServletResponse response){
		try {

			getHbs(request,response,"login");
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void profile(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out=response.getWriter();  

			HttpSession session=request.getSession(false);

			String name=(String)session.getAttribute("name"); 


			if(name!=null){  

				getHbs(request,response,"header");
				out.print("<p style='margin-top:50px;margin-left:20px'>Hello, "+name+" Welcome to Profile<p>"); 

				//out.print("<a href ='home.jsp'>Home</a>");        
			}  
			else{  
				out.print("<p style='margin-top:50px;margin-left:20px'>Please login first");  
				request.getRequestDispatcher("/login").include(request, response);  
			}  
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void signIn(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String uname = request.getParameter("uname");
			
			String password = request.getParameter("pass");
			UserValidService uv = new UserValidService();
			String result = uv.checkValid(uname,password);
			
			if(result.equals(uname)){
				
				out.print("<p style='margin-top:70px;margin-left:20px'>No such username exists <a href='Registration'>Register here</a> or login with another username</p>");
				
				doPost(request,response);
				//request.getRequestDispatcher("login").include(request, response); 
			}
			else if(result.equals(password)){
				
				out.print("<p style='margin-top:70px;margin-left:20px'>Wrong password entered</p>");
				request.getRequestDispatcher("login()").include(request, response); 
			}
			else {
				HttpSession session=request.getSession(); 
		        session.setAttribute("name",result);
		        session.setAttribute("user", uname);
		        file="home";
		        doPost(request,response);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void register(HttpServletRequest request, HttpServletResponse response){
		try {
			getHbs(request,response,"registration");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
