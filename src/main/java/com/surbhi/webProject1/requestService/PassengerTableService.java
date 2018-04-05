package com.surbhi.webProject1.requestService;


import java.util.ArrayList;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requests.DBConnection;

public class PassengerTableService  {
	DBConnection db = new DBConnection();
	DB mongo=db.getDB();
 public List<Passenger> Passengers(String uid){
	 String uType=null;;
	 DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> userCollection = JacksonDBCollection.wrap(collection,User.class, String.class);	
		
		User user = userCollection.findOneById(uid);
		System.out.println("user is "+user);
		if(user!=null){
			
			uType = user.getuType();		
		}
	
		//---------- Creating Collection ------------
		DBCollection collec = mongo.getCollection("passenger");
		JacksonDBCollection<Passenger, String> passengerCollection = JacksonDBCollection.wrap(collec,Passenger.class, String.class);
		Passenger passenger = new Passenger();
		List<Passenger> passengerList = new ArrayList<Passenger>();
		if(uType.equalsIgnoreCase("Admin")){	
			DBCursor<Passenger> cur = passengerCollection.find();
			
			while (cur.hasNext()) {					
				passenger =  cur.next();
				passengerList.add(passenger);
			}
		}
		
		else {
			BasicDBObject query1 = new BasicDBObject();
			
			query1.put("loginuserId", uid);	
			DBCursor<Passenger> cursor = passengerCollection.find(query1);
			
			while(cursor.hasNext()){					
				passenger =  cursor.next();
				passengerList.add(passenger);
				
			}		

		}
		return passengerList;
 }

}
