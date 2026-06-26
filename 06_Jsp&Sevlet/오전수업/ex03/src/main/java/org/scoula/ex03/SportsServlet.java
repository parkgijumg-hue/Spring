package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sportsServlet", value = "/sports")
public class SportsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String[] sport = req.getParameterValues("sports");
        String sex = req.getParameter("sex");

        PrintWriter out = resp.getWriter();
        if(sport != null) {
            for(String s: sport) {
                out.println("좋아하는 운동 : " + s + "<br>");
            }
        }else{
            out.print("좋아하는 운동이 없습니다.");
        }
        out.println("성별 : " + sex);


    }

}
