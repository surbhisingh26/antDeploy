package webProject1.requestService;
import java.util.Iterator;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import webProject1.requests.DBConnection;
public class UserValidService {
	DBConnection db = new DBConnection();
	public String checkValid(String uname,String password){
		MongoDatabase mongo;
		mongo=db.getDB();
		//---------- Creating Collection ------------
		MongoCollection<Document> collection = mongo.getCollection("registration");
		FindIterable<Document> cursor = collection.find();
		Iterator<Document> i = cursor.iterator();
		while (i.hasNext()) {
			Document obj =  (Document) i.next();
			String username = (String)obj.get("username");
			String pass = (String)obj.get("password");
			if(username.equalsIgnoreCase(uname)){
				if(pass.equals(password)){
					String name = (String)obj.get("name");
					
					return name;
				}
				else
					return password;
			}
		}
		return uname;
	}
}