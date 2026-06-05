package sec01;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Iterator;

public class FindTest {
    public static void main(String[] args) {

        MongoCollection<Document> collection = Database.getCollection("study");

        // FindIterable : 조회 결과를 반복자(Iterator) 형태로 반환할 수 있는 객체
        FindIterable<Document> doc = collection.find();

        // 반복자 형태로 반환
        Iterator itr = doc.iterator();

        while (itr.hasNext()) {

            // itr.next() : 다음 행(문서)
            System.out.println("==> findResultRow : "+itr.next());
        }
        Database.close();
    }
}