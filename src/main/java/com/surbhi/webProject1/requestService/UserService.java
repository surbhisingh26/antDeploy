package com.surbhi.webProject1.requestService;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.DBCursor;

import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.model.Invite;
import com.surbhi.webProject1.model.Notify;
import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requests.DBConnection;

public class UserService  {

	DBConnection db1 = new DBConnection();
	public boolean registerUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String bgcolor,String imagepath,String email,String reference,String referenceId){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
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
		query.put("email", email);
		DBCursor<User> cursor = coll.find(query);

		if (cursor.hasNext()) {
			return false;
		}
		registration.setuType("User");
		registration.setName(fname,lname);
		registration.setUsername(uname);
		registration.setEmail(email);
		registration.setGender(gender);
		registration.setCountry(country);
		registration.setCity(city);
		registration.setMobile(mobile);
		registration.setPassword(password);
		registration.setDob(date);
		registration.setBgcolor(bgcolor);
		registration.setLastLoggedInAt(null);
		registration.setImagepath(imagepath);
		registration.setLoggedIn(false);
		registration.setReference(reference);
		registration.setReferenceId(referenceId);
		registration.setPoints(50);

		coll.insert(registration);
		
		DBCollection collection = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll1 = JacksonDBCollection.wrap(collection,Invite.class, String.class);
		DBCollection collection1 = mongo.getCollection("notification");
		JacksonDBCollection<Notify, String> coll2 = JacksonDBCollection.wrap(collection1,Notify.class, String.class);
		Notify notification = new Notify();
		Date Ndate = new Date();
		notification.setUserId(registration.getId());
		notification.setNotification("Welcome "+fname+" "+lname+"\n Congratulation!!! You have been rewarded by 50 points in your account");
		notification.setLink("points");
		notification.setDate(Ndate);
		
		BasicDBObject query1 = new BasicDBObject();
		query.put("recieverEmail", email);
		DBCursor<Invite> cursor1 = coll1.find(query1);
		while(cursor1.hasNext()){
			Invite invite = cursor1.next();
			String userId = invite.getSenderId();
			User user = coll.findOneById(userId);
			user.setPoints(user.getPoints()+50);
			coll.updateById(userId, user);
			Notify notify = new Notify();
			notify.setUserId(userId);
			notify.setNotification("Congratulation!!! You have earned 50 points reward on joining of "+registration.getName());
			notify.setLink("points");
				
			notify.setDate(Ndate);
			coll2.insert(notify);
			coll1.remove(query1);
			
		}
	
		
		
		return true;

	}
	public void updateUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String email){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
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
		DBCursor<User> cursor = coll.find(query);
		if (cursor.hasNext()) {

			registration.setuType("User");
			registration.setName(fname,lname);
			registration.setUsername(uname);
			registration.setEmail(email);
			registration.setGender(gender);
			registration.setCountry(country);
			registration.setCity(city);
			registration.setMobile(mobile);
			registration.setPassword(password);
			registration.setDob(date);

		}
		coll.updateById(registration.getUsername(), registration);
	}
	public User updateColor(String bgcolor,String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> collection = JacksonDBCollection.wrap(collec,User.class, String.class);
					
		System.out.println("color "+uid);
		User user = collection.findOneById(uid);
		user.setBgcolor(bgcolor);
		collection.updateById(uid, user);
		return user;
		}
	

	public User updatePic(String path,String uid){
		try{
			System.out.println("pic "+uid);
			DB mongo;
			mongo=db1.getDB();

			DBCollection collec = mongo.getCollection("registration");
			JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
			System.out.println("uid is ..."+uid);

			User user = coll.findOneById(uid);
			
			System.out.println("uid is ..."+uid);
			user.setImagepath(path);
			System.out.println("path is ..."+user.getImagepath());

			coll.updateById(uid, user);

			return user;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;


	}
	public User findOneById(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User user = coll.findOneById(uid);
		return user;

	}
	public Boolean login(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User user = coll.findOneById(uid);
		System.out.println(uid);
		System.out.println(user.getName());
		user.setLoggedIn(true);
		coll.updateById(uid, user);
		return user.getLoggedIn();
		
	}
	public void logout(String uid){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		Date date = new Date();		
		
		//System.out.println("Date is "+now);
		User user = coll.findOneById(uid);
		user.setLoggedIn(false);
		user.setLastLoggedInAt(date);
		coll.updateById(uid, user);
	}
	public List<Notify> notify(String uid){
		
		DB mongo;
		mongo=db1.getDB();
		List<Notify> Notifications = new ArrayList<Notify>();
		DBCollection collec = mongo.getCollection("notification");
		JacksonDBCollection<Notify, String> coll = JacksonDBCollection.wrap(collec,Notify.class, String.class);
		BasicDBObject query = new BasicDBObject();
		System.out.println("Uid id "+uid);
		query.put("userId", uid);
		DBCursor<Notify> cursor = coll.find(query);
		while(cursor.hasNext()){
			Notify notify = cursor.next();
			Notifications.add(notify);
			System.out.println("Notifications are "+notify.getNotification());
			System.out.println("Notifications are "+notify);
		}
		return Notifications;
		
	}
	public void invite(String senderId,String recieverEmail){
		
		DB mongo;
		mongo=db1.getDB();
		
		DBCollection collec = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll = JacksonDBCollection.wrap(collec,Invite.class, String.class);
		Invite invite = new Invite();
		invite.setSenderId(senderId);
		invite.setRecieverEmail(recieverEmail);
		coll.insert(invite);
		
	}

}
