package webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import webProject1.requests.DBConnection;



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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("name");
		int Tick =Integer.parseInt(request.getParameter("tickets"));
		String Email =request.getParameter("email");
		String Date =(String)request.getParameter("date");
		System.out.println("My name "+fname);
		MongoDatabase mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		//---------- Creating Collection ------------
		MongoCollection collection = mongo.getCollection("passenger");
		Document doc=new Document();
		doc.put("name", fname);
		doc.put("tickets", Tick);
		doc.put("email", Email);
		doc.put("date",Date );
		doc.put("TotalPay","$"+23*Tick);
		collection.insertOne(doc);
		PrintWriter writer = response.getWriter();
		String htmlResponse = "<html>";
		htmlResponse += "<h2>Welcome: " + fname + "</h2>";
		htmlResponse +="<h3>You have booked " + Tick + " tickets</h3>";
		htmlResponse +="<h3>We will send your tickets on your mail " + Email + "</h3>";
		htmlResponse +="<h3>You have to pay a total of $" + 23*Tick + "</h3>";
		htmlResponse += "</html>";
		writer.println(htmlResponse);
	}

}
