package webProject1;
import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import webProject1.DBConnection;
class Passenger {
		
public static void main(String args[]){
	MongoDatabase mongo;
	DBConnection db1 = new DBConnection();
	mongo=db1.getDB();
	//---------- Creating Collection ------------
	MongoCollection collection = mongo.getCollection("passenger");
		Document doc=new Document();
		//String name = 
	//doc.put("name", "sur");
	//doc.put("tickets", 2);
	//doc.put("email", "surabhi@gmail.com");
	//collection.insertOne(doc);
	//System.out.println("Record inserted");

}
}
