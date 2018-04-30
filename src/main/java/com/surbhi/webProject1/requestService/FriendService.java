package com.surbhi.webProject1.requestService;



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
import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requests.DBConnection;

public class FriendService {
	public User searchFriends(String search){
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("username", search);
		DBCursor<User> cursor = coll.find(query);

		if (cursor.hasNext()) {
			User searcheduser = cursor.next();
			return searcheduser;	
		}

		return null;
	}
	public String getFriendStatus(User searcheduser,String uid){

		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();
		DBCollection collection = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll1 = JacksonDBCollection.wrap(collection,Friend.class, String.class);
		BasicDBObject findquery = new BasicDBObject();
		findquery.put("uid", uid);
		DBCursor<Friend> cursor1 = coll1.find(findquery);
		while(cursor1.hasNext()){
			Friend friend = cursor1.next();
			System.out.println("id found... "+friend.getFid());
			if(friend.getFid().equals(searcheduser.getId())){
				System.out.println("id found... "+searcheduser.getId());
				String status = friend.getStatus();
				System.out.println("status found... "+friend.getStatus());
				return status;
			}
		}
		return null;

	}

	public User addFriend(String uid,String fid){
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();
		//Map<String, Object> hmap = new HashMap<String, Object>();
		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);


	/*	BasicDBObject query = new BasicDBObject();
		query.put("uid", uid);
		DBCursor<Friend> cursor = coll.find(query);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			if(friend.getFid().equals(fid))
				return null;
		}*/
		Date date = new Date();
		Friend friend = new Friend();
		friend.setFid(fid);
		friend.setUid(uid);
		friend.setStatus("Request sent");
		friend.setRequestDate(date);
		Friend friend1 = new Friend();
		friend1.setFid(uid);
		friend1.setUid(fid);
		friend1.setStatus("Request pending");
		friend1.setRequestDate(date);
		coll.insert(friend);
		coll.insert(friend1);
		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User userFriend = coll1.findOneById(fid);
		User user = coll1.findOneById(uid);

		String notification = "You have a friend request from "+user.getName();
		NotificationService notificationservice = new NotificationService();

		String link = "friendrequest";
		notificationservice.send(fid,notification,link,date);

		System.out.println("lalala......................." + userFriend.getUsername());

		return userFriend;

	}

	public Map<String, Object> showFriends(String uid){

		List<User> FriendsList = new ArrayList<User>();
		List<User> RequestedList = new ArrayList<User>();

		Map<String, Object> hmap = new HashMap<String, Object>();
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("uid", uid);
		DBCursor<Friend> cursor = coll.find(query);
		while(cursor.hasNext()){
			System.out.println("Query found...");
			Friend friend = cursor.next();
			String fid = friend.getFid();
			DBCollection collection = mongo.getCollection("registration");
			JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
			if(friend.getStatus().contains("accepted")){
				System.out.println("Friend found...");
				User userFriend = coll1.findOneById(fid);
				FriendsList.add(userFriend);

			}
			else if(friend.getStatus().equals("Request sent")){
				System.out.println("Request found...");
				User userRequested = coll1.findOneById(fid);
				RequestedList.add(userRequested);


			}
		}

		hmap.put("FriendsList",FriendsList);
		hmap.put("RequestedList",RequestedList);

		return hmap;

	}


	public List<User> showfriendRequest(String uid){

		List<User> RequestList = new ArrayList<User>();
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);

		BasicDBObject query = new BasicDBObject();
		query.put("uid", uid);
		DBCursor<Friend> cursor = coll.find(query);
		while(cursor.hasNext()){
			DBCollection collection = mongo.getCollection("registration");
			JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
			Friend friend = cursor.next();
			if(friend.getStatus().equals("Request pending")){
				String fid = friend.getFid();
				User friendRequest = coll1.findOneById(fid);
				RequestList.add(friendRequest);
			}
		}

		return RequestList;
	}

	public Map<String, Object> friendResponse(String uid,String fid,String button){
		Map<String, Object> hmap = new HashMap<String, Object>();
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();
		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);

		NotificationService notificationservice = new NotificationService();


		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll3 = JacksonDBCollection.wrap(collection,User.class, String.class);
		//User user = coll3.findOneById(uid);
		User userFriend = coll3.findOneById(fid);

		Date date = new Date();
		BasicDBObject queryfid = new BasicDBObject();
		queryfid.put("uid", fid);
		queryfid.put("fid",uid);
		BasicDBObject queryuid = new BasicDBObject();
		queryuid.put("uid", uid);
		queryuid.put("fid", fid);

		System.out.println("problem "+uid);
		DBCursor<Friend> cursor = coll.find(queryuid);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			System.out.println("friend 1 "+friend.getFid());
			System.out.println(fid);
			friend.setResponseDate(date);
			String link = "friendrequest";
			String notification=null;
			if(button.equalsIgnoreCase("reject")){
				friend.setStatus("I rejected request");

				coll.updateById(friend.getId(),friend);

				notification = userFriend.getName() +" rejected your friend request";					
				System.out.println("Friend is" +userFriend.getName());


			}
			else if(button.equalsIgnoreCase("accept")){
				friend.setStatus("I accepted request");

				coll.updateById(friend.getId(), friend);
				notification = userFriend.getName() +" accepted your friend request";

				System.out.println("Friend is" +userFriend.getName());



			}
			notificationservice.send(fid,notification,link,date);
		}
		DBCursor<Friend> cursor1 = coll.find(queryfid);
		while(cursor1.hasNext()){
			Friend friend = cursor1.next();
			System.out.println("friend 2 "+friend.getFid());
			System.out.println(uid);
			friend.setResponseDate(date);
			if(button.equals("reject")){
				friend.setStatus("My request rejected");

			}
			else if(button.equals("accept"))
				friend.setStatus("My request accepted");
			coll.updateById(friend.getId(), friend);
		}

		System.out.println("Email is.... "+userFriend.getEmail());
		hmap.put("recieveremail",userFriend.getEmail());
		System.out.println("email is.... "+hmap.get("mailTo"));
		hmap.put("recievername", userFriend.getUsername());
		return hmap;
	}

}
