<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 방법 1 --%>
<h3>@RequestParam으로 전달 받은 경우 : param.속성명 </h3>
이름 : ${param.name} <br>
나이: ${param.age} <br>
<h3>@ModelAttribute로 전달 받은 경우 : DTO클래스명(소문자시작).필드명 </h3>
이름 : ${sampleDTO.name} <br>
나이 : ${dto.age} <br>
</body>
</html>

