package com.surbhi.webProject1.requestService;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requests.DBConnection;
public class UserValidService {
	String bgcolor=null;
	DBConnection db = new DBConnection();
	public String[] checkValid(String uname,String password){
		DB mongo;
		
		mongo=db.getDB();
		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<User> cursor = coll.find(query);
		String[] result = new String[2];
		if (cursor.hasNext()) {
			User registration = cursor.next();
			String pass = registration.getPassword();
			 
				if(pass.equals(password)){
					result[0] = registration.getName();
					result[1] = registration.getBgcolor();
					return result;
				}				
				else{
					result[0]=password;
					return result;
				}
			
		}
		result[0]=uname;
		return result;
		
	}
	public String getBgcolor() {
		return bgcolor;
	}
	
}