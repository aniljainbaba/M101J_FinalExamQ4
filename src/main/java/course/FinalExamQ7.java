package course;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class FinalExamQ7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 MongoClient client = new MongoClient();
	         DB db = client.getDB("photosharing");
	         int i =0;
	         DBCollection album = db.getCollection("albums");
	         DBCollection image = db.getCollection("images");
	         
	         DBCursor cur = image.find();
	         cur.next();
	         
	         while (cur.hasNext()){
	             Object id = cur.curr().get("_id");
	            DBCursor curalbum = album.find(new BasicDBObject("images", id));
	            if(!curalbum.hasNext()){
	                image.remove(new BasicDBObject("_id", id));
	                System.out.println("delete id is " + id);
	            }
	            cur.next();
	         }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	}

}
