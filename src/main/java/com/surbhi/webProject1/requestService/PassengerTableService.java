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
 public List<Passenger> Passengers(String userName){
	 String uType=null;;
	 DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll2 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User reg = new User();
		BasicDBObject query = new BasicDBObject();
		query.put("username", userName);
		DBCursor<User> cursor = coll2.find(query);
		
		if(cursor.hasNext()){
			reg = cursor.next();
			uType = reg.getuType();
		
		}
	
		//---------- Creating Collection ------------
		DBCollection collec = mongo.getCollection("passenger");
		JacksonDBCollection<Passenger, String> coll = JacksonDBCollection.wrap(collec,Passenger.class, String.class);
		Passenger passen = new Passenger();
		List<Passenger> passengerList = new ArrayList<Passenger>();
		if(uType.equalsIgnoreCase("Admin")){	
			DBCursor<Passenger> cur = coll.find();
			
			while (cur.hasNext()) {					
				passen =  cur.next();
				passengerList.add(passen);
			}
		}
		
		else {
			BasicDBObject query1 = new BasicDBObject();
			
			query1.put("loginuser", userName);			
			DBCursor<Passenger> cur = coll.find(query1);
			
			while(cur.hasNext()){					
				passen =  cur.next();
				passengerList.add(passen);
				
			}		

		}
		return passengerList;
 }

}
