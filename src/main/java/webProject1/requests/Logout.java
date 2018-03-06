package webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
	     PrintWriter out=response.getWriter(); 
	     HttpSession session=request.getSession();  
         session.invalidate();  
	          
	        out.print("<p style='margin-top:70px;margin-left:20px'>you are successfully logged out!"); 
	        //out.print("<p>Go to <a href = home.jsp>Home</a> page</p>");
//	        request.getRequestDispatcher("login.jsp").include(request, response);
	        response.sendRedirect("/webProject1");
	}

}
