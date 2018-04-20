package com.surbhi.webProject1.requestService;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.mongojack.JacksonDBCollection;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.model.Notify;
import com.surbhi.webProject1.model.Passenger;
import com.surbhi.webProject1.model.User;
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
			passenger.setViewhistory(true);
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
			passen.setViewhistory(true);
			coll.updateById((pid),passen);
			
		}
		DBCollection collec = mongo.getCollection("registration");

		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collec,User.class, String.class);
		
		DBCollection collection1 = mongo.getCollection("notification");
		JacksonDBCollection<Notify, String> coll2 = JacksonDBCollection.wrap(collection1,Notify.class, String.class);
		
		User user = coll1.findOneById(userId);
		user.setPoints(user.getPoints()+50*Tick);
		coll1.updateById(userId, user);
		Notify notify = new Notify();
		notify.setUserId(userId);
		notify.setNotification("You have earned "+50*Tick +"points for booking");
		notify.setLink("points");
		Date Ndate = new Date();	
		notify.setDate(Ndate);
		coll2.insert(notify);

	}
	public void deletePassenger(String pid){
		DB mongo;
		DBConnection db1 = new DBConnection();
		mongo=db1.getDB();
		DBCollection collection = mongo.getCollection("passenger");
		JacksonDBCollection<Passenger, String> coll = JacksonDBCollection.wrap(collection,Passenger.class, String.class);
		Passenger passenger = coll.findOneById(pid);
		passenger.setViewhistory(false);
		coll.updateById(pid,passenger);
		
	}
}
