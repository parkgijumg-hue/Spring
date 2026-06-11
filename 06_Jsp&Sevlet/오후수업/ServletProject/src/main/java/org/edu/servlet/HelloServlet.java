package org.edu.servlet;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

// /hello-servlet  주소로 요청이 왔을때
// 해당 Servlet 클래스가 응답할 수 있도록 연결하는 작업 필요
// -> web.xml에 매핑 설정 단, @WebServlet 사용 시 web.xml 작성 없이 자동 등록 가능
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    // Servlet 클래스를 만들기 위해서는
    // javax.servlet.http.HttpServlet 추상 클래스를 반드시 상속 받아야 한다.
    // -> doGet() / doPost() 를 필요한 형태로 오버라이딩 진행
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpServletRequest  : 클라이언트 + 요청 정보가 담긴 객체
        // HttpServletResponse : 서버가 클라이언트한테 응답하는 방법을 제공하는 객체

        response.setContentType("text/html");

        // Hello

        // Write : 서버가 클라이언트에게 쓰다 == 출력
        // response.getWriter() : 서버가 클라이언트에게 응답할 수 있는
        //                        출력 전용 스트림을 얻어
        PrintWriter out = response.getWriter();

        // HTML 코드를 자바(Servler)에서 작성하여
        // 클라이언트와 연결된 응답 출력용 스트림(out)을 이용해서 출력
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}