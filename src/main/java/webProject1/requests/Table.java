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
	String user,uType,name,email,total,date,time;
	int tickets;
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
			MongoCollection<Document> collection1 = mongo.getCollection("registration");
			FindIterable<Document> cursor1 = collection1.find();
			Iterator<Document> j = cursor1.iterator();
			while(j.hasNext()){
				Document obj1 =  (Document) j.next();
				user = (String)obj1.get("username");
				
				if(user.equals(userName)){
					uType=(String)obj1.get("uType");
					break;
				}
			}
			request.getRequestDispatcher("tableHead.jsp").include(request, response);
			
			if(uType.equalsIgnoreCase("Admin")){
				while (i.hasNext()) {
					Document obj =  (Document) i.next();
					name = (String)obj.get("name");			
					tickets = (Integer)obj.get("tickets");
					email= (String)obj.get("email");
					total = (String)obj.get("TotalPay");
					date = (String)obj.get("date");
					time = (String)obj.get("time");
					context.setAttribute("Name",name );
					context.setAttribute("Tickets",tickets );
					context.setAttribute("Email",email );
					context.setAttribute("Total",total);
					context.setAttribute("Date",date);
					context.setAttribute("Time",time);
					request.getRequestDispatcher("tableRow.jsp").include(request, response);
				}
			}
			else {
				while (i.hasNext()) {
					Document obj =  (Document) i.next();
					user = (String)obj.get("LoginUser");
					if(user.equals(userName)){
						
						name = (String)obj.get("name");			
						tickets = (Integer)obj.get("tickets");
						email= (String)obj.get("email");
						total = (String)obj.get("TotalPay");
						date = (String)obj.get("date");
						time = (String)obj.get("time");
						context.setAttribute("Name",name );
						context.setAttribute("Tickets",tickets );
						context.setAttribute("Email",email );
						context.setAttribute("Total",total);
						context.setAttribute("Date",date);
						context.setAttribute("Time",time);
						request.getRequestDispatcher("tableRow.jsp").include(request, response);
					}
					
				}		

			}
		}

	}

}

