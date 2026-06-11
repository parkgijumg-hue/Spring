package org.edu.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

// /example1으로 요청 시 해당 서블릿이 처리할 수 있도록 작성

// Servlet 클래스 만들 때 반드시 HttpServlet 상속 받아야 함!
@WebServlet(name = "example1",value = "/example1")
public class ServletEx1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");

        // 요청시 전달된 input 태그 값(==파라미터)을 얻어오는 방법
        // request.getParameter("input 태그 name 속성 값)
        String name = request.getParameter("name");
        String age = request.getParameter("age");


        response.getWriter().println("<h1>/example1 요청 성공</h1>");
        response.getWriter().println("<p>이름 : " + name + "</p>");
        response.getWriter().println("<p>나이 : " + age + "</p>");

    }
}
