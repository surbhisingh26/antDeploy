package com.surbhi.webProject1.requestService;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.surbhi.webProject1.pojo.Registration;
import com.surbhi.webProject1.requests.DBConnection;

public class RegisterService {
	//Register reg = new Register();
	DBConnection db1 = new DBConnection();
	public boolean register(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob){
	MongoDatabase mongo;
	mongo=db1.getDB();
	/*Date dob1 = null;
	try {
		dob1 = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	//---------- Creating Collection ------------
	MongoCollection<Registration> collection = mongo.getCollection("registration",Registration.class);
	// FindIterable<Registration> cursor = collection.find();
	// Block<Registration> printBlock = new Block<Registration>(); {
	//	//Iterator<Registration> i = cursor.iterator();
		/*while (i.hasNext()) {
		    Registration reg =  (Registration) i.next();
		    String username = (String)reg.find("username");
		    if(username.equalsIgnoreCase(uname)){
				return false;
			}*/
		//}	
	/*MongoClientOptions settings = MongoClientOptions.builder().readPreference(readPreference)
		    .codecRegistry(com.mongodb.MongoClient.getDefaultCodecRegistry()).build();
		MongoClient mgc= new MongoClient(servers,credentials,settings);*/


 
		Registration reg = new Registration();
		/*ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(reg);*/
		reg.setName(fname,lname);
		reg.setUsername(uname);
		reg.setGender(gender);
		reg.setCountry(country);
		reg.setCity(city);
		reg.setMobile(mobile);
		reg.setPassword(password);
		reg.setDob(dob);
		/*ObjectMapper objectMapper = new ObjectMapper();
	 	try {
	 		 
            objectMapper.writeValue(new File("c:\\registration.json"), register(null, null, null, null, null, null, null, null, null));
 
            // display to console
            Object json = objectMapper.readValue(objectMapper.writeValueAsString(register(null, null, null, null, null, null, null, null, null)), Object.class);
 
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
 
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException ex) {
            e.printStackTrace();
        } catch (IOException exc) {
            e.printStackTrace();
        }
    }
	*/
	collection.insertOne(reg);
	return true;
	/*System.out.println("First name is "+fname);
	System.out.println("Last name is "+lname);
	System.out.println("User name is "+uname);
	System.out.println("Country is "+country);
	System.out.println("city is "+city);
	System.out.println("Mobile is "+mobile);
	System.out.println("Password is "+password);
	System.out.println("Gender is "+gender);*/
	}
	public static void main(String args[]){
        
      //  Registration reg = new Registration();
         
        ObjectMapper mapper = new ObjectMapper();
 
        /**
         * Write object to file
         */
        try {
            mapper.writeValue(new File("registration"), Registration.class);//Plain JSON
            //mapper.writerWithDefaultPrettyPrinter().writeValue(new File("result.json"), carFleet);//Prettified JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
}
