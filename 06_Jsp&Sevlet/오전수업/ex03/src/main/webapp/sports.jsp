<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sports</title>
</head>
<body>
<h1>getParameterValues 실습</h1>
<form action="sports" method="post">
    <fieldset>
        <legend>좋아하는 운동 및 성별</legend>
        <label>야구
            <input type="checkbox" name="sports" value="야구">
        </label>
        <label>축구
            <input type="checkbox" name="sports" value="축구">
        </label>
        <label>농구
            <input type="checkbox" name="sports" value="농구">
        </label>
        <br>
        <label>남
            <input type="radio" name="sex" value="남" checked>
        </label>
        <label>여
            <input type="radio" name="sex" value="여">
        </label>
        <br>
        <input type="submit" value="전송">
    </fieldset>
</form>
</body>
</html>
