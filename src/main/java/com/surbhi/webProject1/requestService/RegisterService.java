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
	public boolean register(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob){
	DB mongo;
	mongo=db1.getDB();
		
	DBCollection collec = mongo.getCollection("registration");
	JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
	Registration reg = new Registration();
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
		reg.setuType("User");
		reg.setName(fname,lname);
		reg.setUsername(uname);
		reg.setGender(gender);
		reg.setCountry(country);
		reg.setCity(city);
		reg.setMobile(mobile);
		reg.setPassword(password);
		reg.setDob(date);
		
	coll.insert(reg);
	return true;
	
	}
}
