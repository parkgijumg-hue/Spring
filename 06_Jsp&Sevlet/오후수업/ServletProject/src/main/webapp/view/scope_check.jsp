<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% // 스크립틀릿 : JSP에서 Java 코드를 작성하는 영역
    pageContext.setAttribute("pageMessage","page scope입니다.");
    String sessionMessage = (String)session.getAttribute("sessionMessage");
//        String applicationMessage = application.getAttribute("applicationMessage").toString();

%>
<h1> 내장 객체 확인</h1>
<ul>
    <li>page: ${pageScope.pageMessage}</li>
    <li>request: ${requestScope.requestMessage}</li>
    <li>session: ${sessionScope.sessionMessage}</li>
    <li>application: ${applicationScope.applicationMessage}</li>
</ul>

<hr>

<p>EL 속성 검색 우선순위: page → request → session → application</p>
<a href="${pageContext.request.contextPath}/">메인으로 이동</a>
</body>
</html>
