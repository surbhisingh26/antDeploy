package com.surbhi.webProject1.requestService;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requests.DBConnection;
public class UserValidService {
	
	DBConnection db = new DBConnection();
	public String checkValid(String uname,String password){
		DB mongo;
		mongo=db.getDB();
		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<Registration> cursor = coll.find(query);
		
		if (cursor.hasNext()) {
			Registration cur = cursor.next();
			String pass = cur.getPassword();
			
				if(pass.equals(password)){
					String name = cur.getName();
					
					return name;
				}
				
				else
					return password;
			
		}
		return uname;
		
	}
}