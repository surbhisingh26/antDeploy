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
import com.surbhi.webProject1.model.Email;
import com.surbhi.webProject1.model.Unsubscribe;
import com.surbhi.webProject1.requests.DBConnection;

public class EmailService {
	DBConnection db1 = new DBConnection();
	
public void email(String purpose,String subject,String recieverEmail,String from,String status,Date date){
		
		DB mongo;
		mongo=db1.getDB();
		System.out.println("STATUS..."+status);
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		Email email = new Email();
		
		email.setPurpose(purpose);
		email.setRecieverEmail(recieverEmail);
		email.setSubject(subject);
		email.setFrom(from);
		email.setDate(date);
		email.setStatus(status);
		email.setViewCount(0);
		coll.insert(email);
	}
	public Map<String, Object> emails(String uid, int page, int limit, String sortBy, String ascending){
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		DB mongo;
		mongo=db1.getDB();
		List<Email> mailList = new ArrayList<Email>();
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		BasicDBObject query = new BasicDBObject();
		if(ascending.equalsIgnoreCase("true")){
			query.put(sortBy, -1);
		}
		else
			query.put(sortBy, 1);
		DBCursor<Email> cursor = coll.find().skip((page-1)*(limit)).limit(limit).sort(query);
		while(cursor.hasNext()){
			Email email = cursor.next();
			mailList.add(email);
		}
		Long count = Emailcollection.count();
		hmap.put("mailList",mailList);
		hmap.put("count",count);
		return hmap;
		
	}
	public void unsubscribe(String emails) {
		
		DB mongo;
		mongo=db1.getDB();
		DBCollection collection = mongo.getCollection("unsubscribe");
		JacksonDBCollection<Unsubscribe, String> coll = JacksonDBCollection.wrap(collection,Unsubscribe.class, String.class);
		Unsubscribe unsubscribe = new Unsubscribe();
		unsubscribe.setEmails(emails);
		Date date = new Date();
		unsubscribe.setDate(date);
		coll.insert(unsubscribe);
	}
	
	public Boolean checkEmailSubscription(String email){
		DB mongo;
		mongo=db1.getDB();
		DBCollection collection = mongo.getCollection("unsubscribe");
		JacksonDBCollection<Unsubscribe, String> coll = JacksonDBCollection.wrap(collection,Unsubscribe.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("emails", email);
		DBCursor<Unsubscribe> cursor = coll.find(query);
		if(cursor.hasNext()){
			return false;
		}
		return true;
	}
	public void trackemail(String from,String to,Date date) {
		
		DB mongo;
		mongo=db1.getDB();
		
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("from", from);
		query.put("recieverEmail",to);
		query.put("date", date);
		System.out.println("Query is .... "+query);
		DBCursor<Email> cursor = coll.find(query);
		Email email = new Email();
		if(cursor.hasNext()){
			System.out.println("Condition true");
		email.setViewCount(email.getViewCount() +1);
		String id = email.getId();
		coll.updateById(id,email);
		}
		
		
	}
}
