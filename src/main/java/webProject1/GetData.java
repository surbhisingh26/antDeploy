package webProject1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class GetData
 */
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("name");
		System.out.println("My name "+fname);
		MongoDatabase mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		//---------- Creating Collection ------------
		MongoCollection collection = mongo.getCollection("passenger");
		Document doc=new Document();
		doc.put("name", fname);
		/*PrintWriter writer = response.getWriter();
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>Your name is: " + fname + "</h2>";
	        htmlRespone += "</html>";
	        writer.println(htmlRespone);*/
	}

}
