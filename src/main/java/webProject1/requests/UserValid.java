package webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
 import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webProject1.requestService.UserValidService;
/**
 * Servlet implementation class UserValid
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
		response.setContentType("text/html");
		String uname = request.getParameter("uname");
		String password = request.getParameter("pass");
		UserValidService uv = new UserValidService();
		String result = uv.checkValid(uname,password);

		PrintWriter out = response.getWriter();
		
		if(result.equals(uname)){
			
			out.print("<p style='margin-top:70px;margin-left:20px'>No such username exists <a href='registration.jsp'>Register here</a> or login with another username</p>");
			
			
			request.getRequestDispatcher("login.jsp").include(request, response); 
		}
		else if(result.equals(password)){
			
			out.print("<p style='margin-top:70px;margin-left:20px'>Wrong password entered</p>");
			request.getRequestDispatcher("login.jsp").include(request, response); 
		}
		else {
			HttpSession session=request.getSession(); 
			
	        session.setAttribute("name",result);  
			//request.setAttribute("name", result);
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
			//request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
		out.close();
	}

}
