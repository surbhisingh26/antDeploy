package webProject1.requests;
import webProject1.requestService.RegisterService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("username");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String mobile =request.getParameter("mobile");
		String password = request.getParameter("pass");
		String gender = request.getParameter("gender");
		RegisterService rs = new RegisterService();
		Boolean result = rs.register(fname, lname, uname,country,city,mobile,password,gender);
		PrintWriter writer = response.getWriter();
		if(result == false){
			String htmlResponse = "<html>";
			htmlResponse += "<h2>This username is are already registered <a href='login.jsp'>Login here</a> or<br><a href = 'registration.jsp'>register </a>with another username </h2>";
			htmlResponse += "</html>";
			writer.println(htmlResponse);
		}
		else{
			String htmlResponse = "<html>";
			htmlResponse += "<h2>You are successfully registered <a href='login.jsp'>Login here</a> </h2>";
			htmlResponse += "</html>";
			writer.println(htmlResponse);
		}
		/*PrintWriter writer = response.getWriter();
		String htmlResponse = "<html>";
		htmlResponse += "<h2>"+ username+"</h2>";
		htmlResponse += "</html>";
		writer.println(htmlResponse);*/

	}

}
