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
	
	
	/*Timer timer;
	String id;
	HttpServletRequest request;
	public EmailService(){
		
	}
	public EmailService(int seconds){
		  timer = new Timer();
		  timer.schedule(new RemindTask(), seconds*1000);
	}
    public EmailService(int seconds,String id,HttpServletRequest request) {
        timer = new Timer();
        
        this.id = id;
        this.request = request;
        timer.schedule(new RemindTask(), seconds*1000);
	}
    
    class RemindTask extends TimerTask {
    	public void init(){
    		System.out.println("INIT...........");
    	}
        public void run() {
        	System.out.println("IN CONSTRUCTOR" +id);
            try {
				Email email = sendEmail(id);
				EmailActions emailactions = new EmailActions();
				emailactions.send(request, "", email.getRecieverEmail(), email.getPurpose(), email.getPurpose(), id);
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            timer.cancel(); //Terminate the timer thread
        }
    }*/
	
	DBConnection db1 = new DBConnection();
	
public String email(String purpose,String subject,String recieverEmail,String from,String status){
		
		DB mongo;
		mongo=db1.getDB();
		System.out.println("STATUS..."+status);
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		Email email = new Email();
		Date date = new Date();
		email.setPurpose(purpose);
		email.setRecieverEmail(recieverEmail);
		email.setSubject(subject);
		email.setFrom(from);
		email.setDate(date);
		email.setStatus(status);
		email.setViewCount(0);
		org.mongojack.WriteResult<Email, String> email1 = coll.insert(email);
		email = email1.getSavedObject();
		System.out.println("Email Id............." + email.getId());
		return email.getId();
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
	public void trackemail(String id) {
		
		DB mongo;
		mongo=db1.getDB();
		System.out.println("Id................" + id);
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		Email email = coll.findOneById(id);
		
		if(email!=null){
			
		email.setViewCount(email.getViewCount() +1);
		coll.updateById(id,email);
		}
		
		
	}
	public void updateStatus(String id, String status) {
		
		DB mongo;
		mongo=db1.getDB();
		
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		System.out.println("Id in update status is ... "+id);
		System.out.println(status);
		Email email = coll.findOneById(id);
		email.setStatus(status);
		coll.updateById(id, email);
	}
	public String checkStatus(String recieverEmail, String username,String purpose ){
		DB mongo;
		mongo=db1.getDB();
		
		DBCollection Emailcollection = mongo.getCollection("emails");
		JacksonDBCollection<Email, String> coll = JacksonDBCollection.wrap(Emailcollection,Email.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("recieverEmails", recieverEmail);
		query.put("from", username);
		query.put("purpose",purpose);
		DBCursor<Email> cursor = coll.find(query);
		Email email = cursor.next();
		return email.getStatus();
		
		
	}
	
}
