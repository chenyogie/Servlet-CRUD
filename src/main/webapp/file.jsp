<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>File</title>
</head>
<body>
<form action="/xx/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="username">
    <input type="password" name="password">
    <%--测试前台如果传递的是复选框的值，对应的请求对象中封装map的方法--%>
    <%--<input type="checkbox" name="hobby" value="a">a
    <input type="checkbox" name="hobby" value="b">b
    <input type="checkbox" name="hobby" value="c">c
    <input type="checkbox" name="hobby" value="d">d--%>
    <input type="file" name="headimg">
    <input type="submit" value="提交">
</form>
</body>
</html>
