package org.edu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Scope 관련 내장 객체

        // 1. page : 현재 페이지에서만 유효한 객체 -> JSP에서 확인

        // -------------------------------------------

        // 2. request : 요청/위임 받은 페이지에서 유지
        //              (위임 되는 동안 계속 유지, 최소 2페이지)
        req.setAttribute("requestMessage","request scope입니다.");

        // -------------------------------------------

        // 3. session : 클라이언트 브라우저당 1개 객체
        //              브라우저 종료 또는 seesion 만료 시 까지 유지
        //              (브라우저 종료 전까지 어디서든 사용 가능)

        // 3-1) session 범위 객체 얻어오기
        HttpSession session = req.getSession();

        // 3-2) session에 값 세팅
        session.setAttribute("sessionMessage","session scope입니다.");

        // -------------------------------------------

        // 4. application : 웹 애플리케이션 당 1개 객체(서버에 1개만 존재)
        //                 웹 애플리케이션(서버)이 종료될 때 까지 유지
        //                 (어디서든 사용 가능)

        // 4-1) application scope 객체 얻어오기
        ServletContext application = req.getServletContext();

        // 4-2) application(변수명)에 값 세팅
        application.setAttribute("applicationMessage","application scope입니다.");

        // -------------------------------------------

        req.getRequestDispatcher("/view/scope.jsp").forward(req,resp);
    }
}
