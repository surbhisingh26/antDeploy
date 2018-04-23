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

import com.surbhi.webProject1.requests.DBConnection;

public class EmailService {
	DBConnection db1 = new DBConnection();
	
public void email(String recieverName,String purpose,String subject,String recieverEmail,String from){
		
		DB mongo;
		mongo=db1.getDB();
		
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		Email email = new Email();
		Date date = new Date();
		email.setPurpose(purpose);
		email.setRecieverEmail(recieverEmail);
		email.setSubject(subject);
		email.setFrom(from);
		email.setDate(date);
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
}
