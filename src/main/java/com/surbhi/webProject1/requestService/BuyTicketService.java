package com.surbhi.webProject1.requestService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requests.DBConnection;

public class BuyTicketService {
	public void bookPassenger(String pid, String user,String fname,int Tick,String Email,String date,String Time,String Place){
		DB mongo;
		DBConnection db1 = new DBConnection();
		Passenger passenger = new Passenger();
		mongo=db1.getDB();
		Date date1 = null;
		Date time = null;
		try {	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date1 = format.parse(date);
			//date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(date);
			SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
			time = format1.parse(Time);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//---------- Creating Collection -----------
		DBCollection collection = mongo.getCollection("passenger");

		JacksonDBCollection<Passenger, String> coll = JacksonDBCollection.wrap(collection,Passenger.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("id", pid);
		BasicDBObject query1 = new BasicDBObject();
		
		DBCursor<Passenger> cursor = coll.find(query);
		System.out.println("passenger is is "+pid);
		if(!cursor.hasNext()){
			System.out.println("false");
			pid = UUID.randomUUID().toString();
			passenger.setId(pid);
			passenger.setLoginuser(user);
			passenger.setName(fname);
			passenger.setTickets(Tick);
			passenger.setEmail(Email);
			passenger.setDate(date1);
			passenger.setTime(time);
			passenger.setTotalPay(23*Tick);
			passenger.setPlace(Place);
			coll.insert(passenger);
		}
		else{
			System.out.println("true");
			passenger=cursor.next();
			System.out.println(passenger);
			
			passenger.setLoginuser(user);
			passenger.setName(fname);
			passenger.setTickets(Tick);
			passenger.setEmail(Email);
			passenger.setDate(date1);
			passenger.setTime(time);
			passenger.setTotalPay(23*Tick);
			passenger.setPlace(Place);
			coll.updateById(pid,passenger);
			System.out.println("true");
			System.out.println(Tick);
			System.out.println(passenger.getTickets());
		}

	}
	public List<Passenger> editPassenger(String id){
		DB mongo;
		DBConnection db1 = new DBConnection();
		Passenger passenger = new Passenger();
		mongo=db1.getDB();

		//if()
		/*try {	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    date1 = format.parse(date);
		//date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(date);
	    SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
	    time = format1.parse(Time);

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		//---------- Creating Collection -----------
		DBCollection collection = mongo.getCollection("passenger");

		JacksonDBCollection<Passenger, String> coll = JacksonDBCollection.wrap(collection,Passenger.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		DBCursor<Passenger> cursor = coll.find(query);
		List<Passenger> passengerDetails = new ArrayList<Passenger>();
		if(cursor.hasNext()){
			passenger = cursor.next();
			passengerDetails.add(passenger);
		}
		return passengerDetails;
	}
}
