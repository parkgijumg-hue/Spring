package sec01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class ConnectionTest
{
    public static void main(String[] args) {
        String uri = "mongodb://127.0.0.1:27017";
        String db = "practice_db";
        try (MongoClient client =MongoClients.create(uri)){
            System.out.println("1.몽고DB연결 성공"+client);
            MongoDatabase database = client.getDatabase(db);
        } catch(Exception e) {
            System.out.println("몽고DB연결시 에러 발생"+e.getMessage());
            e.printStackTrace();
        }
    }
}