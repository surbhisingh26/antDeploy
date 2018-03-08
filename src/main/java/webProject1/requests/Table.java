package webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import webProject1.requests.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Table
 */
public class Table extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Table() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DBConnection db = new DBConnection();
		MongoDatabase mongo;
		mongo=db.getDB();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("user");
		
		request.getRequestDispatcher("header.jsp").include(request, response);
		ServletContext context=getServletContext();
		
		MongoCollection<Document> collection = mongo.getCollection("passenger");
		FindIterable<Document> cursor = collection.find();
		Iterator<Document> i = cursor.iterator();
		if(userName==null){
			out.print("<p style='margin-top:80px;margin-left:40px'>Please login first");  
			request.getRequestDispatcher("login.jsp").include(request, response); 
			
		}
		else{
			request.getRequestDispatcher("tableHead.jsp").include(request, response);
		while (i.hasNext()) {
			Document obj =  (Document) i.next();
			String user = (String)obj.get("LoginUser");
			if(userName==null){
				out.print("<p style='margin-top:80px;margin-left:40px'>Please login first");  
				request.getRequestDispatcher("login.jsp").include(request, response); 
				break;
			}
			
			if(user.equals(userName)){
				String name = (String)obj.get("name");			
				int tickets = (Integer)obj.get("tickets");
				String email= (String)obj.get("email");
				String total = (String)obj.get("TotalPay");
				String date = (String)obj.get("date");
				context.setAttribute("Name",name );
				context.setAttribute("Tickets",tickets );
				context.setAttribute("Email",email );
				context.setAttribute("Total",total);
				context.setAttribute("Date",date);
				request.getRequestDispatcher("tableRow.jsp").include(request, response);	
			}
		}
		}

	}

}

