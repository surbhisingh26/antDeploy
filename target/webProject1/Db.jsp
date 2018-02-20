<%@ page language="java" 
    contentType="text/html; 
    charset=windows-1256"
     pageEncoding="windows-1256" 
     import="com.mongodb.BasicDBObject"
     import="com.mongodb.DB"
     import="com.mongodb.DBCollection"
     import="com.mongodb.DBCursor"
     import="com.mongodb.MongoClient"
     import="java.net.UnknownHostException"
      %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <html> 
     <head>
     <meta http-equiv="Content-Type" content="text/html; charset=windows-1256"> 
     <title> Test Page</title>
     </head> 
     <body> 



   <% MongoClient mongoClient = null;
try {
    mongoClient = new MongoClient();
} catch (UnknownHostException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}
// or, to connect to a replica set, supply a seed list of members
//MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
  //                                    new ServerAddress("localhost", 27018),
    //                                  new ServerAddress("localhost", 27019)));

DB db = mongoClient.getDB("Reservation");
DBCollection coll;
coll = db.getCollection("passenger");
BasicDBObject doc = new BasicDBObject("Number1", 1).
        append("Number2", 2).append("Number3", 3);

//System.out.println("Data Display");
coll.insert(doc);
DBCursor cursor = coll.find();
try {
   while(cursor.hasNext()) {
       System.out.println(cursor.next());
   }
} finally {
   //mongoClient.dropDatabase("test");
   cursor.close();

}
    %>
     </body>
     </html>