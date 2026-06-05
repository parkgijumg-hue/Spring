package app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionTest
{
    public static void main(String[] args) {
        String uri = "mongodb://127.0.0.1:27017";
        String db = "todo_db";
        // 몽고db와 java 연동
        // 몽고db 연동할 수 있는 자바 라이브러리가 필요
        // 몽고db driver필요
        // 1.몽고db 서버연결 --> 외부자원(네트워크) 연결시 반드시 예외처리 해야함
        //   try-catch-resources(close자동으로 해줌)
        // 2.몽고db 서버의 db연결
        // 3.몽고db 연결된 db의 collection연결
        // 4.collection의 document(json) crud
        // 5.몽고db close(중요)
        try (MongoClient client =MongoClients.create(uri)){
            System.out.println("1.몽고DB연결 성공"+client);
            MongoDatabase database = client.getDatabase(db);
        } catch(Exception e) {
            System.out.println("몽고DB연결시 에러 발생"+e.getMessage());
            e.printStackTrace();
        }
    }
}