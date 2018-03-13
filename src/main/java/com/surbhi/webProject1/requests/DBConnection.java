package com.surbhi.webProject1.requests;


import com.mongodb.DB;
import com.mongodb.MongoClient;  
 
import com.mongodb.client.MongoDatabase; 


public class DBConnection { 
	public DB getDB(){  
		try{  
			
			//---------- Connecting DataBase -------------------------//  
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
			//---------- Creating DataBase ---------------------------//  
			DB db = mongoClient.getDB("Reservation");  
			/*JacksonCodecRegistry jacksonCodecRegistry = new JacksonCodecRegistry();
			jacksonCodecRegistry.addCodecForClass(MyObject.class);
			MongoCollection<?> coll = mongoDatabase.getCollection("testCollection");
			MongoCollection<MyObject> collection = coll.withDocumentClass(MyObject.class).withCodecRegistry(jacksonCodecRegistry)*/;

			return db;

		}catch(Exception e){  
			System.out.println(e);  

		}
		return null;  
	}  
}  