package com.surbhi.webProject1.requestService;


import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

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

}
