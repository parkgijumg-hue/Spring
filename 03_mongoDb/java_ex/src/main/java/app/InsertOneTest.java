package app;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

public class InsertOneTest {
    public static void main(String[] args) {
        // Database클래스를 쓰는순간 static{} 이 실행됨. db서버연결, db연결이 준비가 됨.
        MongoCollection<Document> collection = Database.getCollection("todo");
        System.out.println("3. todo 컬렉션 연결 성공.");

        // crud 작업 중 create(insert)
        // 몽고 db는 json형태의 Document를 insert
        // Document(json)을 먼저 만들고
        // 컬렉션에 Document를 insert함.

        Document document = new Document();
        document.append("title", "MongoDB");
        document.append("desc", "MongoDB 공부하기");
        document.append("done", false);
        InsertOneResult result = collection.insertOne(document);
        System.out.println("4. document insert 몽고db로 보냄.");
        System.out.println("==> InsertOneResult : " + result.getInsertedId());
        Database.close();
    }
}