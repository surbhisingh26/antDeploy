package com.surbhi.webProject1.requestService;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.surbhi.webProject1.model.Friend;
import com.surbhi.webProject1.model.Notify;
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

	public String addFriend(String uid,String fid){
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();

		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("uid", uid);
		DBCursor<Friend> cursor = coll.find(query);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			if(friend.getFid().equals(fid))
					return null;
		}
		Friend friend = new Friend();
		friend.setFid(fid);
		friend.setUid(uid);
		friend.setStatus("Request sent");
		Friend friend1 = new Friend();
		friend1.setFid(uid);
		friend1.setUid(fid);
		friend1.setStatus("Request pending");
		coll.insert(friend);
		coll.insert(friend1);
		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll1 = JacksonDBCollection.wrap(collection,User.class, String.class);
		User userFriend = coll1.findOneById(fid);
		User user = coll1.findOneById(uid);
		
		String notification = "You have a friend request from "+user.getName();
		DBCollection Notifycollection = mongo.getCollection("notification");
		JacksonDBCollection<Notify, String> coll2 = JacksonDBCollection.wrap(Notifycollection,Notify.class, String.class);
		Notify notify = new Notify();
		notify.setUserId(fid);
		notify.setNotification(notification);
		notify.setLink("friendrequest");
		Date date = new Date();
		notify.setDate(date);
		coll2.insert(notify);
	
		
		return userFriend.getEmail();

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
		
		hmap.put("FriendsList",FriendsList);
		hmap.put("RequestedList",RequestedList);
		
		return hmap;

	}

	
	public List<User> friendRequest(String uid){
		
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
	
	public void friendResponse(String uid,String fid,String button){
		
		DBConnection db = new DBConnection();
		DB mongo;
		mongo=db.getDB();
		DBCollection collec = mongo.getCollection("friends");
		JacksonDBCollection<Friend, String> coll = JacksonDBCollection.wrap(collec,Friend.class, String.class);
		
		DBCollection Notifycollection = mongo.getCollection("notification");
		JacksonDBCollection<Notify, String> coll2 = JacksonDBCollection.wrap(Notifycollection,Notify.class, String.class);
		Notify notify = new Notify();
		
		DBCollection collection = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll3 = JacksonDBCollection.wrap(collection,User.class, String.class);
		//User user = coll3.findOneById(uid);
		User userFriend = coll3.findOneById(uid);
		
		
		BasicDBObject queryfid = new BasicDBObject();
		queryfid.put("uid", fid);
		BasicDBObject queryuid = new BasicDBObject();
		queryuid.put("uid", uid);
		
		System.out.println("problem "+uid);
		DBCursor<Friend> cursor = coll.find(queryuid);
		while(cursor.hasNext()){
			Friend friend = cursor.next();
			System.out.println("friend 1 "+friend.getFid());
			System.out.println(fid);
			if(friend.getFid().equals(fid)){
				if(button.equals("reject")){					
					coll.removeById(friend.getId());
					notify.setUserId(fid);
					notify.setNotification(userFriend.getName() +" rejected your friend request");
					System.out.println("Friend is" +userFriend.getName());
					notify.setLink("friends");
					Date date = new Date();
					notify.setDate(date);
					coll2.insert(notify);
				}
				else if(button.equals("accept")){
					friend.setStatus("Friends");
				coll.updateById(friend.getId(), friend);
				notify.setUserId(fid);
				notify.setNotification(userFriend.getName() +" accepted your friend request");
				System.out.println("Friend is" +userFriend.getName());
				notify.setLink("friends");
				Date date = new Date();
				notify.setDate(date);
				coll2.insert(notify);
				}
			}
		}
		DBCursor<Friend> cursor1 = coll.find(queryfid);
		while(cursor1.hasNext()){
			Friend friend = cursor1.next();
			System.out.println("friend 2 "+friend.getFid());
			System.out.println(uid);
			if(friend.getFid().equals(uid)){
				if(button.equals("reject")){					
					coll.removeById(friend.getId());
				}
				else if(button.equals("accept"))
					friend.setStatus("Friends");
				coll.updateById(friend.getId(), friend);
			}
		}
	}

}
