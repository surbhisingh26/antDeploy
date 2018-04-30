package com.surbhi.webProject1.requestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.highchart.StackedChart;
import com.surbhi.webProject1.model.Friend;
import com.surbhi.webProject1.model.Invite;
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
		List<Integer> Oaccept = new ArrayList<Integer>();
		List<Integer> Ounresponded =new  ArrayList<Integer>();
		List<Integer> Oreject =new  ArrayList<Integer>();
		List<Integer> Iaccept =new  ArrayList<Integer>();
		List<Integer> Iunresponded =new  ArrayList<Integer>();
		List<Integer> Ireject =new  ArrayList<Integer>();
		/*int[] Oaccept = new int[12];
		int[] Ounresponded = new int[12];
		int[] Oreject = new int[12];
		int[] Iaccept = new int[12];
		int[] Iunresponded = new int[12];
		int[] Ireject = new int[12];*/
//		Ireject.size() > i
		//Ireject.a
		
		
		Map<String,Object> hmap = new HashMap<String,Object>();
		
		DBCollection collection = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collection,Friend.class, String.class);
		BasicDBObject request = new BasicDBObject();
		request.put("uid", uid);
		
	//	DBCursor<Friend> cursor1 = coll.find(sentRequest);
		for(int i = 0;i<12;i++){
			DBCursor<Friend> cursor1 = coll.find(request);
		//int oucount = 0;
		int oucount = 0;
		int oacount = 0;
		int orcount = 0;
		int iucount = 0;
		int iacount = 0;
		int ircount = 0;
		
			while(cursor1.hasNext()){
				Friend friend = cursor1.next();
				Calendar cal = Calendar.getInstance();
				cal.setTime(friend.getRequestDate());
				int requestMonth = cal.get(Calendar.MONTH);
				String status = friend.getStatus();
				if(requestMonth==i){
					if(status.equalsIgnoreCase("Request sent")){
						oucount += 1;
						//graph.add(1);
					}
					else if(status.equalsIgnoreCase("Request pending")){
						iucount += 1;
						//graph.add(1);
					}
					else if(friend.getResponseDate()!=null){
						cal.setTime(friend.getResponseDate());
						int responseMonth = cal.get(Calendar.MONTH);
						System.out.println("Month..........." + requestMonth);
						
						if(status.equalsIgnoreCase("My request Accepted")){
							if(requestMonth!=responseMonth){
								oucount += 1;
								
							}
							oacount += 1;

						}
						else if(status.equalsIgnoreCase("My request rejected")){
							orcount += 1;
						}
						
						if(status.equalsIgnoreCase("I accepted Request")){
							if(requestMonth!=responseMonth){
								iucount += 1;
							}
							iacount += 1;

						}
						else if(status.equalsIgnoreCase("I rejected request")){
							ircount += 1;
						}
					}
				}
				
			}
			Oaccept.add(oacount);
			Ounresponded.add(oucount);
			Oreject.add(orcount);
			Iaccept.add(iacount);
			Iunresponded.add(iucount);
			Ireject.add(ircount);
			
		}
		/*DBCursor<Friend> cursor = coll.find(sentRequest);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			String status = friend.getStatus();
			Calendar cal = Calendar.getInstance();
			cal.setTime(friend.getRequestDate());
			int requestMonth = cal.get(Calendar.MONTH);
			if(status.equalsIgnoreCase("Request sent")){
				Ounresponded[requestMonth] += 1;
			}
			else{
				cal.setTime(friend.getResponseDate());
				int responseMonth = cal.get(Calendar.MONTH);
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
		}*/

		//BasicDBObject gotRequest = new BasicDBObject();
		//gotRequest.put("fid", uid);
		//DBCursor<Friend> cursor1 = coll.find(gotRequest);
		//for(int i = 0;i<12;i++){
			//DBCursor<Friend> cursor1 = coll.find(gotRequest);
		//int oucount = 0;
		
		
			
			
			
		
		/*while(cursor1.hasNext()){
			Friend friend = cursor1.next();
			Calendar cal = Calendar.getInstance();
			cal.setTime(friend.getRequestDate());
			int requestMonth = cal.get(Calendar.MONTH);
			System.out.println("Request month........... " + requestMonth);
			String status = friend.getStatus();
			
			//			int count = 0;
			//			
			//			if(requestMonth==i){
			//				count++;
			//			}
			//			
			if(status.equalsIgnoreCase("Request pending")){
				Ounresponded[requestMonth] += 1;
				//graph.add(1);
			}
			else{
				cal.setTime(friend.getResponseDate());
				int responseMonth = cal.get(Calendar.MONTH);
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
		}*/

		StackedChart iAccept = new StackedChart();
		StackedChart iReject = new StackedChart();
		StackedChart iUnresponded = new StackedChart();
		StackedChart oAccept = new StackedChart();
		StackedChart oReject = new StackedChart();
		StackedChart oUnresponded = new StackedChart();
		
		oUnresponded.setData(Ounresponded);
		oUnresponded.setName("My unresponded requests");
		oUnresponded.setStack("Outgoing requests");
		
		iAccept.setData(Iaccept);
		iAccept.setName("I Accepted");
		iAccept.setStack("Incoming requests");
		
		iReject.setData(Ireject);
		iReject.setName("I Rejected");
		iReject.setStack("Incoming requests");
		
		iUnresponded.setData(Iunresponded);
		iUnresponded.setName("I did not respond to");
		iUnresponded.setStack("Incoming requests");
		
		oAccept.setData(Oaccept);
		oAccept.setName("My Accepted Requests");
		oAccept.setStack("Outgoing requests");
		
		oReject.setData(Oreject);
		oReject.setName("My Rejected Requests");
		oReject.setStack("Outgoing requests");
	
		hmap.put("iAccept", iAccept);
		hmap.put("iReject", iReject);
		hmap.put("iUnresponded", iUnresponded);
		hmap.put("oAccept", oAccept);
		hmap.put("oReject", oReject);
		hmap.put("oUnresponded", oUnresponded);
		
		
		return hmap;
	}

	public List<User> getAllUsers(){
		
		List<User> userList = new ArrayList<User>();
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		DBCursor<User> cursor = coll.find();
		while(cursor.hasNext()){
			User user = cursor.next();
			userList.add(user);
			System.out.println("Users.........................." + user.getName());
			
		}
		return userList;
		
	}
	public int checkPendingRequests(String uid){
		
		//List<User> userList = getAllUsers();
		DB mongo;
		mongo=db1.getDB();
		int count = 0;
		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		System.out.println("UID................" + uid);
			BasicDBObject query = new BasicDBObject();
			query.put("uid", uid);
			query.put("status", "Request pending");
			DBCursor<Friend> cursor = coll.find(query);
			while(cursor.hasNext()){
				count++;
			}
				
		return count;


	}
}