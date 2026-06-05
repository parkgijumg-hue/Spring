package sec01;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
// imports static : 클래스명을 생략하고 메소드명만 작성 가능

public class FindOneTest {
    public static void main(String[] args) {

        MongoCollection<Document> collection = Database.getCollection("study");

        // 존재하는 _id
        String id = "6a2267bc58209c08428ebe73";

        // Filters.eq()
        Bson query = eq("_id", new ObjectId(id));

        // 조건을 만족하는 결과 중 1행(문서 1개)만 조회
        // collection.find() == db.study.find()
        Document doc = collection.find(query).first();
        System.out.println("==> findByIdResult : " + doc);
        Database.close();
    }
}