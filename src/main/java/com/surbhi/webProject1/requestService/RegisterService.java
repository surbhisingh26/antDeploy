package com.surbhi.webProject1.requestService;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requests.DBConnection;
import com.surbhi.webProject1.requests.Register;

public class RegisterService {
	
	//Register reg = new Register();
	DBConnection db1 = new DBConnection();
	public boolean register(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob){
	DB mongo;
	mongo=db1.getDB();
	//MongoCollection<Registration> collection = mongo.getCollection("registration",Registration.class);
	
	DBCollection collec = (DBCollection) mongo.getCollection("registration");
	ObjectMapper myObjectMapper = new ObjectMapper();
	MongoJackModule.configure(myObjectMapper);
	JacksonDBCollection<Registration, String> coll = JacksonDBCollection.wrap(collec,Registration.class, String.class);
	Registration reg = new Registration();
	/*Date dob1 = null;
	try {
		dob1 = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	//---------- Creating Collection ------------
	DBCursor<Registration> cursor=coll.find().is("username", uname);
	if(cursor.hasNext()){
		System.out.println(cursor.next());
		return false;
	}
	 /*FindIterable<Registration> cursor = collection.find();
	Block<Registration> printBlock = new Block<Registration>(); {
	Iterator<Registration> i = cursor.iterator();
		while (i.hasNext()) {
		    Registration reg =  (Registration) i.next();
		    String username = (String)reg.find("username");
		    if(username.equalsIgnoreCase(uname)){
				return false;
		    }
		}	*/
	
	//coll.insert(reg);

	
		reg.setName(fname,lname);
		reg.setUsername(uname);
		reg.setGender(gender);
		reg.setCountry(country);
		reg.setCity(city);
		reg.setMobile(mobile);
		reg.setPassword(password);
		reg.setDob(dob);
		
	coll.insert(reg);
	return true;
	
	}
	/*public static void main(String args[]){
        
        Register re = new Register();
         
        ObjectMapper mapper = new ObjectMapper();
 
        *//**
         * Write object to file
         *//*
        try {
            mapper.writeValue(new File("registration.json"), re);//Plain JSON
            //mapper.writerWithDefaultPrettyPrinter().writeValue(new File("result.json"), carFleet);//Prettified JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }*/
}
