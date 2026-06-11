<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>
</head>
<body>
    <h1> index라는 이름이 붙은 파일은 메인 페이지 역할을 한다.</h1>
    <br/>

    <hr>

    <pre>
        form 태그 : 내부에 작성된 input 태그의 값을
                   지정된 서버 또는 페이지로 제출(전달)하는 역할
    </pre>

    <form action="${pageContext.request.contextPath}/example1" method="get">

        이름 : <input type="text" name="name"><br>
        나이 : <input type="text" name="age"><br>

        <button type="submit">서버로 제출하기</button>
    </form>

<hr>
<h3>JSP 테스트 </h3>
<%-- 서버 요청 주소 작성
     - GET 방식 요청 종류 : a 태그, form 태그 (method="get"),직접 주소 작성, JS(location)
     - POST 방식 요청 종류 : form 태그 (method="post")
        --%>
<a href="/pizza/order">피자 주문하기</a>

<hr>

<a href="/scope">Servlet/JSP 내장 객체와 범위</a>
</body>
</html>
