package com.surbhi.webProject1.requests;


import com.mongodb.MongoClient;  
 
import com.mongodb.client.MongoDatabase; 


public class DBConnection { 
	public MongoDatabase getDB(){  
		try{  
			
			//---------- Connecting DataBase -------------------------//  
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
			//---------- Creating DataBase ---------------------------//  
			MongoDatabase db = mongoClient.getDatabase("Reservation");  

			return db;

		}catch(Exception e){  
			System.out.println(e);  

		}
		return null;  
	}  
}  