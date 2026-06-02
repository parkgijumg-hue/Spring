import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {
    public static void main(String[] args) {
        // DB명(jdbc_ex), 사용자(scoula), 비밀번호(1234)는 본인 환경에 맞게 바꾸세요.
        String url =
                "jdbc:mysql://localhost:3306/jdbc_ex"
                        + "?useSSL=false"
                        + "&allowPublicKeyRetrieval=true"
                        + "&serverTimezone=Asia/Seoul"
                        + "&characterEncoding=UTF-8";
        String user = "scoula";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("MySQL 연결 성공!");
        } catch (Exception e) {
            System.out.println("MySQL 연결 실패");
            e.printStackTrace();
        }
    }
}

