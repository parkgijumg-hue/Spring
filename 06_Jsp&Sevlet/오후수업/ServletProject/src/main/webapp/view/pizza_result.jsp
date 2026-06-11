<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 방법이 2가지로 결과를 출력할 수 있음
<%@ %> : 지시자 태그(페이지 정의)
	"charset=UTF-8" : 현재 문서가 UTF-8 문자 인코딩 형식으로 작성되어 있음
	pageEncoding="UTF-8" : 현재 문서를 해석할 때 UTF-8 문자 인코딩을 이용해서 해석

	<% %> : 스크립틀릿(scriptlet) : JSP에서 자바 코드를 작성할 수 있는 영역
	-> JSTL 라이브러리를 이용해서 태그 형식으로 변경

	<%= %> : 표현식(Expression) : 자바 코드의 값을 HTML 형식으로 표현(출력)
	-> EL(Expression Language)을 이용해서 간단하게 값을 출력

	-> ${key값} : EL 표현식 : JSP에서 자바 객체의 값을 간단하게 출력할 수 있는 방법

--%>

<%--   <% 자바코드 %> 형태로 작성--%>
<% // 자바코드 작성 영역(scriptlet, 스크립틀릿)
   // JSP 페이지에서 자바코드를 작성할 수 있는 영역


    String pizza = request.getParameter("pizza");
    String pizzaname = request.getParameter("pizzaname");
//    String size = request.getParameter("size");
//    String amount = request.getParameter("amount");

%>
<html>
<head>
    <title>피자 주문 결과</title>
</head>
<body>
<form>
    <h1>피자 주문 결과</h1>
    <ul>
        <li>피자+가격 : <b><%=pizza%></b></li>
        <li>피자  : <b>${pizzaName}</b>입니다.</li>
        <li>사이즈 : <b>${size}</b>입니다.</li>
        <li>수량  :  <b>${amount}</b>개입니다.</li>
        <li>합계  :  <b>${price}  </b></li>

    </ul>



</form>

</body>
</html>
