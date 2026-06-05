package app;

import app.Database;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Iterator;
public class FindTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("todo");
        // 전체검색
        FindIterable<Document> doc = collection.find();
        Iterator itr = doc.iterator();
        while (itr.hasNext()) {
            System.out.println("==> findResultRow : "+itr.next());
        }
        Database.close();
    }
}