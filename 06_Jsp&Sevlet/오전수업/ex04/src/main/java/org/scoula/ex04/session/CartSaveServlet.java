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

@WebServlet(name = "cartSaveServlet", value = "/cart_save")
public class CartSaveServlet extends HttpServlet {

    // localhost:8080/cart_save로 요청이 들어왔을 때, get요청이 오면 doGet()을 호출함.
    // 요청할 때 전달된 데이터도 받아야하고,
    // 처리한 다음 클라이언트에게 응답도 해야함.
    // doGet()메서드를 호출할 때마다 요청과 응답을 처리할 수 있는 request, response객체를 매번 만든다.
    // 응답하고 나서는 request/response객체는 서버에서 없어짐.
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //장바구니 추가한 내용을 저장해두고 싶으면
        //선택할 수 있음.
        //서버에 저장하고 싶으면 세션기술 사용할 것.
        //브라우저에 저장하고 싶으면 쿠키기술 사용할 것.

        //단일값인 userid는 변수만들어서 바로 세션의 속성으로 저장하면 됨
        //다중값인 product의 장바구니는 장바구니 역할을 하는 list가 필요함.
        //--> 세션에 product이라는 이름으로 list를 값으로 저장함.

        //장바구니추가를 눌렀을 때 처음 장바구니에 넣는 것일 수도 있고
        //기존에 장바구니가 이미 있는 상태에서 추가할 수도 있음.
        //이미 장바구니가 저장된 것이 있는지 없는지 판단부터 해야함.
        //만약에 null이면 이미 저장된 것이 없었던 것.
        //list만들고 --> 세션에 저장하다.
        //만약에 null이 아닌 경우 이미 저장된 것이 있었던 것.
        //list를 가지고 오고 --> 물건을 추가
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        ArrayList<String> productl = (ArrayList<String>) session.getAttribute("products");
        if (productl == null) {
            productl = new ArrayList<>();
            session.setAttribute("products", productl);
        }
        String product = req.getParameter("product");
        productl.add(product);
        PrintWriter out = resp.getWriter();
        out.println("Product 추가!!<br>");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_view'>장바구니 보기</a>");
    }
}
