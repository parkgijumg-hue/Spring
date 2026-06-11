package org.edu.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "pizzaOrderServlet", value = "/pizza/order")
public class PizzaOrderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        // RequestDispatcher : 요청 발송자
        // -> 지정된 JSP로 요철ㅇ 정보(req), 응답 정보(resp)를 전송(발송)하는 역할
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/pizza_order.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ** POST 방식 한글 꺠짐 문제 **

        // - 데이터 전달 방식 차이점
        // GET : 주소(URL)에 데이터를 포함해서 전달 -> 주소창에 한글이 그대로 노출

        // POST : HTTP Body에 데이터를 포함해서 전달 -> 한글이 깨져서 노출

        // - 해결 방법 : POST 방식으로 전달된 데이터는 UTF-8로 인코딩 되어 있기 때문에,
        //             서버에서 해당 데이터를 UTF-8로 디코딩 해주는 작업 필요

        // 반드시 getParameter()보다 먼저 작성
//        request.setCharacterEncoding("UTF-8");

        String pizza = request.getParameter("pizza");
        String size = request.getParameter("size");
        int amount =Integer.parseInt(request.getParameter("amount"));

        // 얻어온 파라미터 확인
//        System.out.println("pizza : " + pizza);
//        System.out.println("size : " + size);
//        System.out.println("amount : " + amount);

        // 피자 이름, 가격 나누기
        String[] pizzaInfo = pizza.split("-");

        String pizzaName = pizzaInfo[0];
        int price = Integer.parseInt(pizzaInfo[1]);



        // L 사이즈인 경우 4000원 추가
        if(size.equals("L")) {
            price += 4000;
        }

        // 총 가격 구하기
        price *= amount;

        System.out.println("pizzaName : " + pizzaName);
        System.out.println("price : " + price);
        // JSP에 전달할 값 저장
//        request.setAttribute("pizza", pizza);
        request.setAttribute("size", size);
        request.setAttribute("amount", amount);
        request.setAttribute("price", price);
        // JSP로 요청 위임
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/pizza_result.jsp");

        // JSP로 전달하는 request에는 파라미터가 담겨 있지만
        // Servlet에서 만든 변수인 pizzaname,price 은 없다.

        // * 해결방법 *
        // request에 속성(Attribute)로 추가하면 JSP에서 꺼내 쓸 수 있다.
        // (주의!) forword 하기 전에 추가해야함!
        request.setAttribute("pizzaName", pizzaName);

        dispatcher.forward(request, response);
    }
}
