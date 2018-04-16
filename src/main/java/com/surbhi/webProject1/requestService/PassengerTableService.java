package com.surbhi.webProject1.requestService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> Passengers(String uid, int page, int limit, String sortBy, String ascending){
		String uType=null;;
		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> userCollection = JacksonDBCollection.wrap(collection,User.class, String.class);	
		long count=1;
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
		Map<String, Object> map = new HashMap<String, Object>();
		BasicDBObject query = new BasicDBObject();
		if(ascending.equalsIgnoreCase("true")){
			query.put(sortBy, -1);
		}
		else
			query.put(sortBy, 1);
		if(uType.equalsIgnoreCase("Admin")){	
			count = passengerCollection.count();
			DBCursor<Passenger> cur = passengerCollection.find().skip((page-1)*(limit)).limit(limit).sort(query);

			while (cur.hasNext()) {	
				passenger =  cur.next();				
				passengerList.add(passenger);

			}
		}

		else {
			BasicDBObject query1 = new BasicDBObject();

			query1.put("loginuserId", uid);	
			count = passengerCollection.count(query1);
			DBCursor<Passenger> cursor = passengerCollection.find(query1).skip((page-1)*(limit)).limit(limit).sort(query);

			while(cursor.hasNext()){	

				passenger =  cursor.next();
				if(passenger.getViewhistory()==true)
					passengerList.add(passenger);

			}		

		}
		map.put("passengerList", passengerList);
		map.put("count", count);
		return map;
	}
	public void sort(){
		DBConnection db = new DBConnection();
		DB mongo=db.getDB();
		DBCollection collec = mongo.getCollection("passenger");
		JacksonDBCollection<Passenger, String> passengerCollection = JacksonDBCollection.wrap(collec,Passenger.class, String.class);
		BasicDBObject query = new BasicDBObject();

		query.put("name", -1);	
		DBCursor<Passenger> cursor = passengerCollection.find().sort(query);
	}

}
