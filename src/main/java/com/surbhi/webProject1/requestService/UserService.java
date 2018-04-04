package com.surbhi.webProject1.requestService;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mongojack.DBCursor;

import org.mongojack.JacksonDBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.surbhi.webProject1.pojo.User;
import com.surbhi.webProject1.requests.DBConnection;

public class UserService {


	DBConnection db1 = new DBConnection();
	public boolean registerUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob,String bgcolor,String imagepath){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
		Date date = null;
		try {	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------- Creating Collection ------------
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<User> cursor = coll.find(query);

		if (cursor.hasNext()) {
			return false;
		}
		registration.setuType("User");
		registration.setName(fname,lname);
		registration.setUsername(uname);
		registration.setGender(gender);
		registration.setCountry(country);
		registration.setCity(city);
		registration.setMobile(mobile);
		registration.setPassword(password);
		registration.setDob(date);
		registration.setBgcolor(bgcolor);

		coll.insert(registration);
		return true;

	}
	public void updateUser(String fname,String lname,String uname,String country,String city,String mobile,String password,String gender,String dob){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		User registration = new User();
		Date date = null;
		try {	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);
		DBCursor<User> cursor = coll.find(query);
		if (cursor.hasNext()) {

			registration.setuType("User");
			registration.setName(fname,lname);
			registration.setUsername(uname);
			registration.setGender(gender);
			registration.setCountry(country);
			registration.setCity(city);
			registration.setMobile(mobile);
			registration.setPassword(password);
			registration.setDob(date);

		}
		coll.updateById(registration.getUsername(), registration);
	}
	public void updateColor(String bgcolor,String uname){
		DB mongo;
		mongo=db1.getDB();

		DBCollection collec = mongo.getCollection("registration");
		JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
		//Registration registration = new Registration();					
		BasicDBObject query = new BasicDBObject();
		query.put("username", uname);

		DBCursor<User> cursor = coll.find(query);
		if (cursor.hasNext()) {



			coll.update(new BasicDBObject("username", uname),
					new BasicDBObject("$set", new BasicDBObject("bgcolor", bgcolor)));
		}
	}
	public void updatePic(String path,String uid){
		try{
			DB mongo;
			mongo=db1.getDB();

			DBCollection collec = mongo.getCollection("registration");
			JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collec,User.class, String.class);
			System.out.println("uid is ..."+uid);

			User user = coll.findOneById(uid);
			
			System.out.println("uid is ..."+uid);
			user.setImagepath(path);
			System.out.println("path is ..."+user.getImagepath());

			coll.updateById(uid, user);



			/*//File imageFile = new File(filecontent);

			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(mongo, "images");

			// get image file from local drive


			GridFSInputFile gfsFile = gfsPhoto.createFile(filecontent,path);

			gfsFile.setContentType("image");
			// set a new filename for identify purpose
			//gfsFile.setFilename(filename);

			// save the image file into mongoDB
			gfsFile.save();


			System.out.println("Done");

			String newFileName = path;
			System.out.println(newFileName);
		    GridFS gfsPhoto1 = new GridFS(mongo, "images");
		    GridFSDBFile imageForOutput = gfsPhoto1.findOne(newFileName);
		   System.out.println(imageForOutput.getContentType());
		   System.out.println(imageForOutput.getMD5());

		   System.out.println(imageForOutput.getChunkSize());

		   System.out.println(imageForOutput.getInputStream());
		   System.out.println(imageForOutput.getMetaData());


		  //  imageForOutput.writeTo(out)writeTo("C:/image/"+path);
		    return imageForOutput.getInputStream();*/

		}
		catch(Exception e){
			e.printStackTrace();
		}


	}
	public void showPic(){

		//    System.out.println(imageForOutput);
	}

}
