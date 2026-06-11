<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>피자 주문 페이지</title>
</head>
<body>

     <h1>피자 주문 페이지</h1>
    <%-- /pizza/order 라는 같은 주소 요청을 처리하는 Servlet이 존재하지만
         데이터 전달 방식이 get/post로 구분되기 때문에
         doGet(),doPost()로 연결되어 처리되므로 충돌 문제 없음
         --%>
     <form action="/pizza/order" method="post">
     <div class="row">
         <label>피자 :</label>
         <select name="pizza">
             <option>치즈 피자-8000</option>
             <option>콤비네이션 피자-9000</option>
             <option>쉬림프 피자-15000</option>
             <option>더블 포테이토 피자-14000</option>
             <option>하와이안 피자-12000</option>
         </select>
     </div>

     <div class="row">
         <label>사이즈 : </label>
         R <input type="radio" name="size" value="R" checked>
         L(+4000) <input type="radio" name="size" value="L">
     </div>

     <div class="row">
         <label>수량</label>
         <button type="button">-</button>
         <input type="number" name="amount" min="1" max="9" value="1">
         <button type="button">+</button>
     </div>

     <button class="order-btn">주문하기</button>

     </form>

</body>
</html>
