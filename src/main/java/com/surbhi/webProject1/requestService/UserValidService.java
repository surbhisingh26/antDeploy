package com.surbhi.webProject1.requestService;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requests.DBConnection;
public class UserValidService {
	String bgcolor=null;
	DBConnection db = new DBConnection();
	public String checkValid(String uname,String password,String reference,String referenceId){
		DB mongo;
		
		mongo=db.getDB();
		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		if(uname!=""){
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<User> cursor = coll.find(query);
		String result = null;
		if (cursor.hasNext()) {
			User registration = cursor.next();
			String pass = registration.getPassword();
			 
				if(pass.equals(password)){
					
					result = registration.getId();
					System.out.println("uid is ... "+result);

					return result;
				}				
				else{
					
					return password;
				}
			
		}}
		else{
			BasicDBObject query = new BasicDBObject();
			query.put("referenceId", referenceId);
			DBCursor<User> cursor = coll.find(query);
			if(cursor.hasNext()){
				User registration = cursor.next();
				String id = registration.getId();
				System.out.println("Id is "+id);
				return id;	
			}
			else{
				return "Register First";
			}
		}
		
		return uname;
		
	}
	
	
}