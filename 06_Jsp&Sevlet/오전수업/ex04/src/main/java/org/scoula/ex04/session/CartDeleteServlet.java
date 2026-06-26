package org.scoula.ex04.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cartDeleteServlet", value = "/cart_delete")
public class CartDeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        PrintWriter out = resp.getWriter();
        out.println("장바구니 비웠음!!");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a>");
    }
}
