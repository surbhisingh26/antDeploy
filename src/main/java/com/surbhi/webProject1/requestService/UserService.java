package com.surbhi.webProject1.requestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.model.Friend;
import com.surbhi.webProject1.model.Invite;
import com.surbhi.webProject1.model.Passenger;
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

		org.mongojack.WriteResult<User, String> reg = coll.insert(registration);
		registration = reg.getSavedObject();
		DBCollection collection = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll1 = JacksonDBCollection.wrap(collection,Invite.class, String.class);

		NotificationService notificationservice = new NotificationService();
		String link = "points";
		Date Ndate = new Date();
		BasicDBObject query1 = new BasicDBObject();
		query1.put("recieverEmail", email);
		DBCursor<Invite> cursor1 = coll1.find(query1);

		while(cursor1.hasNext()){
			Invite invite = cursor1.next();
			String userId = invite.getSenderId();

			User user = coll.findOneById(userId);
			EmailService emailservice = new EmailService();
			String mailStatus = emailservice.checkStatus(email, user.getUsername(),"inviteToJoin");
			if(mailStatus.equalsIgnoreCase("Sent")){
				user.setPoints(user.getPoints()+50);
				String userEmail = user.getEmail();
				coll.updateById(userId, user);
				String username = user.getName();
				BasicDBObject query2 = new BasicDBObject();

				query2.put("recieverEmail", userEmail);
				DBCursor<Invite> cursor2 = coll1.find(query2);
				while(cursor2.hasNext()){
					Invite invite1 = cursor2.next();
					String secondaryUserId = invite1.getSenderId();
					User user1 = coll.findOneById(secondaryUserId);
					user1.setPoints(user1.getPoints()+10);
					coll.updateById(secondaryUserId, user1);
					coll1.remove(query2);
					notificationservice.send(secondaryUserId,"Congratulation!!! You have earned 10 points reward on joining of "+registration.getName()+" invited by your friend "+username,link,Ndate);
				}

				System.out.println("REGISTRATION ID....................."+ registration.getId());

				notificationservice.send(userId,"Congratulation!!! You have earned 50 points reward on joining of "+registration.getName(),link,Ndate);
			}
		}

		notificationservice.send(registration.getId(),"Welcome "+fname+" "+lname+"\n Congratulation!!! You have been rewarded by 50 points in your account",link,Ndate);



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

	public String invite(String senderId,String recieverEmail){

		DB mongo;
		mongo=db1.getDB();

		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User user = coll1.findOneById(senderId);
		BasicDBObject query = new BasicDBObject();
		query.put("email", recieverEmail);
		DBCursor<User> cursor = coll1.find(query);
		if(cursor.hasNext())
			return null;


		DBCollection collec = mongo.getCollection("invitation");
		JacksonDBCollection<Invite, String> coll = JacksonDBCollection.wrap(collec,Invite.class, String.class);
		query.put("senderId", senderId);
		DBCursor<Invite> cursor1 = coll.find(query);
		if(cursor1.hasNext()){
			return "Already invited";
		}
		Invite invite = new Invite();
		invite.setSenderId(senderId);
		invite.setRecieverEmail(recieverEmail);
		coll.insert(invite);



		return user.getUsername();

	}
	public Map<String,Object> profile(String uid) {

		DB mongo;
		mongo=db1.getDB();
		int[] Oaccept = new int[12];
		int[] Ounresponded = new int[12];
		int[] Oreject = new int[12];
		int[] Iaccept = new int[12];
		int[] Iunresponded = new int[12];
		int[] Ireject = new int[12];
		Map<String,Object> hmap = new HashMap<String,Object>();
		List<Integer> graph = new ArrayList<Integer>();
		DBCollection collection = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collection,Friend.class, String.class);
		BasicDBObject sentRequest = new BasicDBObject();
		sentRequest.put("uid", uid);
		DBCursor<Friend> cursor = coll.find(sentRequest);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			String status = friend.getStatus();
			int requestMonth = friend.getRequestDate().getMonth();
			if(status.equalsIgnoreCase("Request sent")){
				Ounresponded[requestMonth] += 1;
			}
			else{
			int responseMonth = friend.getResponseDate().getMonth();
			System.out.println("Month..........." + requestMonth);
			if(requestMonth!=responseMonth){
				Ounresponded[requestMonth] += 1;
			}
			if(status.equalsIgnoreCase("Friends")){
				
				Oaccept[responseMonth] += 1;
			
			}
			else if(status.equalsIgnoreCase("Your request rejected")){
				Oreject[responseMonth] += 1;
			}
			}
		}

		BasicDBObject gotRequest = new BasicDBObject();
		gotRequest.put("fid", uid);
		
		DBCursor<Friend> cursor1 = coll.find(gotRequest);
		while(cursor1.hasNext()){
			Friend friend = cursor1.next();
			String status = friend.getStatus();
			int requestMonth = friend.getRequestDate().getMonth();
			if(status.equalsIgnoreCase("Request pending")){
				Ounresponded[requestMonth] += 1;
				//graph.add();
			}
			else{
			int responseMonth = friend.getResponseDate().getMonth();
			System.out.println("Month..........." + requestMonth);
			if(requestMonth!=responseMonth){
				Iunresponded[requestMonth] += 1;
			}
			if(status.equalsIgnoreCase("Friends")){
				
				Iaccept[responseMonth] += 1;
			
			}
			else if(status.equalsIgnoreCase("Your request rejected")){
				Ireject[responseMonth] += 1;
			}
			}
		}
		hmap.put("Oaccept", Oaccept);
		hmap.put("Ounresponded", Ounresponded);
		hmap.put("Oreject", Oreject);
		hmap.put("Iaccept", Iaccept);
		hmap.put("Iunresponded", Iunresponded);
		hmap.put("Ireject", Ireject);
		hmap.put("graph", graph);
		return hmap;
	}



}
