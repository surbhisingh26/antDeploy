package com.surbhi.webProject1.requestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Passenger;
import com.surbhi.webProject1.requests.DBConnection;

public class BuyTicketService {
public void booking(String user,String fname,int Tick,String Email,String date,String Time){
	DB mongo;
	DBConnection db1 = new DBConnection();
	Passenger pas = new Passenger();
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

	pas.setLoginuser(user);
	pas.setName(fname);
	pas.setTickets(Tick);
	pas.setEmail(Email);
	pas.setDate(date1);
	pas.setTime(time);
	pas.setTotalPay(23*Tick);
	coll.insert(pas);
}
}
