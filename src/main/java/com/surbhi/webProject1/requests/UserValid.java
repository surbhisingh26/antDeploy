package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.surbhi.webProject1.requestService.UserValidService;
/**
 * Servlet implementation class GetData
 */
public class UserValid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserValid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		/*TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/Templates", ".hbs");
		Handlebars handlebars = new Handlebars(loader);
		Template template = handlebars.compile("login");
		Map<String, Object> hmap = new HashMap<String, Object>();
		out.print(template.apply(hmap));*/
		
		response.setContentType("text/html");
		String uname = request.getParameter("uname");
		String password = request.getParameter("pass");
		UserValidService uv = new UserValidService();
		String result = uv.checkValid(uname,password);
		
		if(result.equals(uname)){
			
			out.print("<p style='margin-top:70px;margin-left:20px'>No such username exists <a href='registration.hbs'>Register here</a> or login with another username</p>");
			
			request.getRequestDispatcher("Templates/login.hbs").include(request, response); 
		}
		else if(result.equals(password)){
			
			out.print("<p style='margin-top:70px;margin-left:20px'>Wrong password entered</p>");
			request.getRequestDispatcher("Templates/login.hbs").include(request, response); 
		}
		else {
			HttpSession session=request.getSession(); 
	        session.setAttribute("name",result);
	       // String username=uv.getUname();
	        session.setAttribute("user", uname);
			request.getRequestDispatcher("/home").forward(request, response);			
		}
		out.close();
	}

}
