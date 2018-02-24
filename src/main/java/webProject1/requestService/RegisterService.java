package webProject1.requestService;

import java.io.PrintWriter;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import webProject1.requests.Register;
import webProject1.requests.DBConnection;

public class RegisterService {
	//Register reg = new Register();
	DBConnection db1 = new DBConnection();
	public boolean register(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender){
	MongoDatabase mongo;
	mongo=db1.getDB();
	//---------- Creating Collection ------------
	MongoCollection collection = mongo.getCollection("registration");
	 FindIterable<Document> cursor = collection.find();
		Iterator i = cursor.iterator();
		while (i.hasNext()) {
		    Document obj =  (Document) i.next();
		    String username = (String)obj.get("username");
		    if(username.equalsIgnoreCase(uname)){
				return false;
			}
		}
		
	Document doc=new Document();
	doc.put("name", fname+" "+ lname);
	doc.put("username", uname);
	doc.put("gender",gender);
	doc.put("country",country);
	doc.put("city",city);
	doc.put("mobile",mobile);
	doc.put("password",password);
	collection.insertOne(doc);
	return true;
	/*System.out.println("First name is "+fname);
	System.out.println("Last name is "+lname);
	System.out.println("User name is "+uname);
	System.out.println("Country is "+country);
	System.out.println("city is "+city);
	System.out.println("Mobile is "+mobile);
	System.out.println("Password is "+password);
	System.out.println("Gender is "+gender);*/
	}
}
