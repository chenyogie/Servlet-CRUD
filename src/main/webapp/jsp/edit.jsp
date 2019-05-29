<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ADD</title>
</head>
<body>
<%--
    文件上传必须使用：
    method="post"
    enctype="multipart/form-data"
--%>
<form action="/xx/controller" method="post" enctype="multipart/form-data">
    <table border="1" width="500px" style="margin: 100px 0 0 250px">
        <input type="hidden" name="cmd" value="${cmd}">
        <c:if test="${stu.id!=null}">
            <tr>
                <td>ID</td>
                <td><input type="text" name="id" value="${stu.id}"></td>
            </tr>
        </c:if>
        <tr>
            <td>高手姓名:</td>
            <td>
                <input type="text" name="username" value="${stu.username}">
            </td>
        </tr>
        <tr>
            <td>登录密码:</td>
            <td>
                <input type="text" name="password" value="${stu.password}">
            </td>
        </tr>
        <tr>
            <td>高手年龄:</td>
            <td>
                <input type="text" name="age" value="${stu.age}">
            </td>
        </tr>
        <tr>
            <td>高手级别:</td>
            <td>
                <select name="grade_id">
                    <option value="1"
                            <c:if test="${stu.grade_id==1}">selected</c:if> >初级
                    </option>
                    <option value="2"
                            <c:if test="${stu.grade_id==2}">selected</c:if> >中极
                    </option>
                    <option value="3"
                            <c:if test="${stu.grade_id==3}">selected</c:if> >高级
                    </option>
                    <option value="4"
                            <c:if test="${stu.grade_id==4}">selected</c:if> >无敌
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td>高手性别:</td>
            <td>
                <input type="radio" name="sex" value="true"
                       <c:if test="${stu.sex==true}">checked='checked'</c:if> >男
                <input type="radio" name="sex" value="false"
                       <c:if test="${stu.sex==false}">checked='checked'</c:if> >女
            </td>
        </tr>
        <tr>
            <td>特长武功:</td>
            <td>
                <input type="text" name="intro" value="${stu.intro}">
            </td>
        </tr>
        <tr>
            <td>武力值:</td>
            <td>
                <input type="text" name="account" value="${stu.account}">
            </td>
        </tr>
        <tr>
            <td>头像</td>
            <td><input type="file" name="headimg" value="${stu.headimg}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="确定提交">
            </td>
        </tr>

    </table>
</form>
</body>
</html>
