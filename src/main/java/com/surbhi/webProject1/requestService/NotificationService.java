package com.surbhi.webProject1.requestService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.surbhi.webProject1.model.Notify;
import com.surbhi.webProject1.requests.DBConnection;

public class NotificationService {
	DBConnection db1 = new DBConnection();
public void send(String uid,String notification,String link,Date date){
	
	DB mongo;
	mongo=db1.getDB();

	
	DBCollection collection = mongo.getCollection("notification");
	JacksonDBCollection<Notify, String> coll = JacksonDBCollection.wrap(collection,Notify.class, String.class);
	Notify notify = new Notify();
	
	notify.setUserId(uid);
	notify.setNotification(notification);
	notify.setLink(link);
	notify.setDate(date);
	coll.insert(notify);
	
}
public List<Notify> show(String uid){
	
	DB mongo;
	mongo=db1.getDB();
	List<Notify> Notifications = new ArrayList<Notify>();
	DBCollection collec = mongo.getCollection("notification");
	JacksonDBCollection<Notify, String> coll = JacksonDBCollection.wrap(collec,Notify.class, String.class);
	BasicDBObject query = new BasicDBObject();
	System.out.println("Uid id "+uid);
	query.put("userId", uid);
	BasicDBObject sortBy = new BasicDBObject();
	sortBy.put("date", -1);
	DBCursor<Notify> cursor = coll.find(query).sort(sortBy);
	while(cursor.hasNext()){
		Notify notify = cursor.next();
		Notifications.add(notify);
		System.out.println("Notifications are "+notify.getNotification());
		System.out.println("Notifications are "+notify);
	}
	return Notifications;
	
}
}
