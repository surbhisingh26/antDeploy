package com.surbhi.webProject1.requestService;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.mongojack.DBCursor;

import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requests.DBConnection;

public class RegisterService {
	
	
	DBConnection db1 = new DBConnection();
	public boolean registerUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String bgcolor){
	DB mongo;
	mongo=db1.getDB();
	
	DBCollection collec = mongo.getCollection("registration");
	JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
	Registration registration = new Registration();
	Date date = null;
	try {	
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    date = format.parse(dob);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//---------- Creating Collection ------------
	BasicDBObject query = new BasicDBObject();
	query.put("username", uname);
	DBCursor<Registration> cursor = coll.find(query);
	
	if (cursor.hasNext()) {
			return false;
	    }
		registration.setuType("User");
		registration.setName(fname,lname);
		registration.setUsername(uname);
		registration.setGender(gender);
		registration.setCountry(country);
		registration.setCity(city);
		registration.setMobile(mobile);
		registration.setPassword(password);
		registration.setDob(date);
		registration.setBgcolor(bgcolor);
		
	coll.insert(registration);
	return true;
	
	}
	public void updateUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob){
		DB mongo;
		mongo=db1.getDB();
			
		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
		Registration registration = new Registration();
		Date date = null;
		try {	
		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		    date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<Registration> cursor = coll.find(query);
		if (cursor.hasNext()) {
			
			registration.setuType("User");
			registration.setName(fname,lname);
			registration.setUsername(uname);
			registration.setGender(gender);
			registration.setCountry(country);
			registration.setCity(city);
			registration.setMobile(mobile);
			registration.setPassword(password);
			registration.setDob(date);
		
		}
		coll.updateById(registration.getUsername(), registration);
	}
		public void updateColor(String bgcolor,String uname){
			DB mongo;
			mongo=db1.getDB();
				
			DBCollection collec = mongo.getCollection("registration");
			JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
			//Registration registration = new Registration();					
			BasicDBObject query = new BasicDBObject();
			query.put("username", uname);
			
			DBCursor<Registration> cursor = coll.find(query);
			if (cursor.hasNext()) {
				
				
		
			coll.update(new BasicDBObject("username", uname),
	                  new BasicDBObject("$set", new BasicDBObject("bgcolor", bgcolor)));
			}
		}
}
