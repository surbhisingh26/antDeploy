package com.surbhi.webProject1.requestService;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.mongojack.JacksonDBCollection;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.requests.DBConnection;

public class BookingService {
	public void bookPassenger(String pid, String userId,String fname,int Tick,String Email,String date,String Time,String Place){
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
		/*BasicDBObject query = new BasicDBObject();
		query.put("id", pid);*/
		
		Passenger passen = coll.findOneById(pid);
		/*DBCursor<Passenger> cursor = coll.find(query);*/
		System.out.println("passenger is is "+pid);
		System.out.println("passen is  "+passen);

		if(passen==null){
	
			passenger.setLoginuserId(userId);
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
			
			passen.setLoginuserId(userId);
			passen.setName(fname);
			passen.setTickets(Tick);
			passen.setEmail(Email);
			passen.setDate(date1);
			passen.setTime(time);
			passen.setTotalPay(23*Tick);
			passen.setPlace(Place);
			coll.updateById((pid),passen);
			
		}

	}
	public void deletePassenger(String id){
		DB mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		DBCollection collection = mongo.getCollection("passenger");
		JacksonDBCollection<Passenger, String> coll = JacksonDBCollection.wrap(collection,Passenger.class, String.class);
		
		coll.removeById(id);
		
	}
}
