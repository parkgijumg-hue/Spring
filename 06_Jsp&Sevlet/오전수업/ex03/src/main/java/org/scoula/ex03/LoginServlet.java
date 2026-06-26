package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    //get요청이 들어오면 service()메서드가 호출됨.
    //service()메서드에서 get요청이면 doGet()을 호출하게 되어있음.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet호출됨... ==================");
        //get방식 데이터는 서블릿에서 받을 때 별도로 한글처리 필요없음.(url과 함께오기 때문)
        String userid = req.getParameter("userid");
        String passwd = req.getParameter("passwd");
        String[] sports = req.getParameterValues("sports");
        String total = Arrays.toString(sports); //배열의 내용을 하나의 String으로 만들어준다.
        //로그아웃 전까지 서버의 ram을 저장하고 싶으면
        //session으로 저장
        //브라우저에 받은 id, pw확인하는 페이지를 보여주자.!!

        // 응답 내보내기 ==> out객체 만들기전에 한글 인코딩 설정해주어야함.
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print("<html><head></head><body bgcolor=yellow>");
        out.println("id = " + userid + "<br>");
        out.println("pw = " + passwd + "<br>");
        out.println("sports = " + total + "<br>");
        out.print("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //post로 요청들어온 것 데이터 받아야함.
        //post는 http의 body로 들어온다. --> tomcat에서는 한글지원안되는
        //iso-8879-1인코딩을 꺼내줌.(한글이 깨짐.)
        //데이터를 꺼내기전에 utf-8인코딩으로 꺼내겠다라고 설정해주어야함.
        req.setCharacterEncoding("utf-8");
        doGet(req,  resp);
    }
}