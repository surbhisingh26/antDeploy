package com.surbhi.webProject1.requestService;


import java.util.ArrayList;

import java.util.List;


import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.pojo.Friend;

import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requests.DBConnection;

public class FriendService {
	public User searchfriends(String search){
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("username", search);
		DBCursor<User> cursor = coll.find(query);

		if (cursor.hasNext()) {
			User user = cursor.next();
			return user;
		}
		else
			return null;
	}

	public String addfriend(String uid,String fid){
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		Friend friend = new Friend();
		friend.setFid(fid);
		friend.setUid(uid);
		friend.setStatus("Request sent");
		Friend friend1 = new Friend();
		friend1.setFid(uid);
		friend1.setUid(fid);
		friend1.setStatus("Friend Request");
		coll.insert(friend);
		coll.insert(friend1);
		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User user = coll1.findOneById(fid);
		return user.getEmail();

	}

	public List[] showfriends(String uid){
		
		List<User> FriendsList = new ArrayList<User>();
		List<User> RequestedList = new ArrayList<User>();
		
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
			if(friend.getStatus().equals("Friends")){
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
		List map[]=new List[2];
		map[0] = FriendsList;
		map[1] = RequestedList;
		return map;

	}
	
	public List<User> friendrequest(String uid){
		
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
			if(friend.getStatus().equals("Friend Request")){
				String fid = friend.getFid();
				User friendRequest = coll1.findOneById(fid);
				RequestList.add(friendRequest);
			}
		}
		
		return RequestList;
	}

}
