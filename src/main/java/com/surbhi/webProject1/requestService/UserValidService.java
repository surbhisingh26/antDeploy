package com.surbhi.webProject1.requestService;
import java.util.Iterator;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.surbhi.webProject1.requests.DBConnection;
public class UserValidService {
	String username;
	DBConnection db = new DBConnection();
	public String checkValid(String uname,String password){
		MongoDatabase mongo;
		mongo=db.getDB();
		
		//---------- Creating Collection ------------
		MongoCollection<Document> collection = mongo.getCollection("registration");
		FindIterable<Document> cursor = collection.find();
		Iterator<Document> i = cursor.iterator();
		while (i.hasNext()) {
			Document obj =  (Document) i.next();
			username = (String)obj.get("username");
			String pass = (String)obj.get("password");
			//id=(String)obj.get("_id");
			if(username.equalsIgnoreCase(uname)){
				if(pass.equals(password)){
					String name = (String)obj.get("name");
					
					return name;
				}
				else
					return password;
			}
		}
		return uname;
	}
	public String getUname(){
		return username;
	}
}