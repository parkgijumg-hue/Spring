package sec01;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class InsertManyTest {
    public static void main(String[] args) {
        // Database 유틸리티 클래스를 이용해서 study 컬렉션(테이블) 연결 객체 가져오기
        MongoCollection<Document> collection = Database.getCollection("study");

        // 문서 리스트 객체 생성
        List<Document> insertList = new ArrayList<>();

        Document document1 = new Document();
        Document document2 = new Document();

        document1.append("title", "Dune2 영화보기");
        document1.append("desc", "이번 주말 IMAX로 Dune2 영화보기");
        document1.append("done", false);
        document2.append("title", "Java MongoDB 연동");
        document2.append("desc", "Java로 MongoDB 연동 프로그래밍 연습하기");
        document2.append("done", true);

        // 문서 리스트에 추가
        insertList.add(document1);
        insertList.add(document2);

        System.out.println("삽입할 document list의 개수"+insertList.size());

        // 한 번에 insert
        InsertManyResult result = collection.insertMany(insertList);
        System.out.println("==> InsertManyResult : " + result.getInsertedIds());
        Database.close();
    }
}