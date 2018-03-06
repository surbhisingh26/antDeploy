package webProject1.requestService;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import webProject1.requests.DBConnection;

public class TableData {
 public void DBdata(){
	 DBConnection db = new DBConnection();
		MongoDatabase mongo;
		mongo=db.getDB();
		//---------- Creating Collection ------------
		MongoCollection<Document> collection = mongo.getCollection("passenger");
		FindIterable<Document> cursor = collection.find();
		Iterator<Document> i = cursor.iterator();
		while (i.hasNext()) {
			Document obj =  (Document) i.next();
			String name = (String)obj.get("name");
			int tickets = (Integer)obj.get("tickets");
			String email= (String)obj.get("email");
			String total = (String)obj.get("TotalPay");
		}
	
 }
}
