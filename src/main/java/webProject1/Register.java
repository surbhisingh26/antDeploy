package webProject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import webProject1.DBConnection;
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
		MongoDatabase mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		//---------- Creating Collection ------------
		MongoCollection collection = mongo.getCollection("registration");
		//System.out.println(collection.find());
	//	BasicDBObject query = new BasicDBObject();
	//	BasicDBObject field = new BasicDBObject();
		
		FindIterable<Document> cursor = collection.find();
		while (cursor.iterator().hasNext()) {
		    Document obj =  cursor.iterator().next();
		    if((obj.get("username"))==uname){
				PrintWriter writer = response.getWriter();
				String htmlResponse = "<html>";
				htmlResponse += "<h2>matched</h2>";
				htmlResponse += "</html>";
				writer.println(htmlResponse);
			}
		}
		//DBObject dbo = collection.find();
		/*if(dbo.get("username")==uname){
			PrintWriter writer = response.getWriter();
			String htmlResponse = "<html>";
			htmlResponse += "<h2>matched</h2>";
			
			htmlResponse += "</html>";
			writer.println(htmlResponse);
		}*/
		Document doc=new Document();
		doc.put("name", fname+" "+lname);
		doc.put("username", uname);
		doc.put("gender",gender);
		doc.put("country",country);
		doc.put("city",city);
		doc.put("mobile",mobile);
		doc.put("password",password);
		collection.insertOne(doc);
		
	}

}
