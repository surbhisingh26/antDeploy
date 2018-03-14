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
			return db;

		}catch(Exception e){  
			System.out.println(e);  

		}
		return null;  
	}  
}  