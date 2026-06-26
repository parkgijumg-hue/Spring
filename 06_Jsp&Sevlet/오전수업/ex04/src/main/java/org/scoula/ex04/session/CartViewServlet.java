package org.scoula.ex04.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "cartViewServlet", value = "/cart_view")
public class CartViewServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        ArrayList<String> products = (ArrayList<String>) session.getAttribute("products");
        session.setMaxInactiveInterval(20);
        PrintWriter out = resp.getWriter();
        out.println("장바구니 리스트상품 : "+products);
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_delete'>장바구니 비우기</a>");

    }
}
