<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>LSIT</title>
    <script>
        function changePageSize() {
            //获取用户输入的记录数
            var pageSize = document.getElementById("pageSize").value;
            //判断是否输入的数值
            var reg = /^[1-9][0-9]?$/;
            if(!reg.test(pageSize)){
                alert("请输入数值类型");
                return;
            }
            //把记录数发送到后台
            var url = "/xx/controller?cmd=query&pageSize="+pageSize;
            window.location.href=url;
        }
    </script>
</head>
<body>
<h1>欢迎${NAME_IN_SESSION}登录！</h1><br>
<a href="/xx/jsp/essay.jsp">今日武林纪实</a>
<h1 style="margin: 0 0 0 560px">武林高手榜</h1>
<table border="1" width="800" cellpadding="0" cellspacing="0" style="margin: 0px 0 0 250px">
    <form action="/xx/controller">
        <tr>
            <td colspan="9">
                <input type="hidden" name="cmd" value="query">
                <input type="text" id="search" placeholder="请输入关键字" name="search_key">
                <input type="submit" value="查询">
            </td>
        </tr>
    </form>
    <tr>
        <th>编号</th>
        <th>高手等级</th>
        <th>高手姓名</th>
        <th>登录密码</th>
        <th>年龄</th>
        <th>性别</th>
        <th>特长武功</th>
        <th>武力值</th>
        <th>头像</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pageBean.data}" var="stu">
    <tr>
        <td>${stu.id}</td>
        <td>
            <c:if test="${stu.grade_id==1}">初级</c:if>
            <c:if test="${stu.grade_id==2}">中级</c:if>
            <c:if test="${stu.grade_id==3}">高级</c:if>
            <c:if test="${stu.grade_id==4}">无敌</c:if>
        </td>
        <td>${stu.username}</td>
        <td>${stu.password}</td>
        <td>${stu.age}</td>
        <td>
            <c:if test="${stu.sex==true}">男</c:if>
            <c:if test="${stu.sex==false}">女</c:if>
        </td>
        <td>${stu.intro}</td>
        <td>${stu.account}</td>
        <td><img src="${stu.headimg}" alt="暂无" width="60px" height="60px"></td>
        <td>
            <a href="/xx/controller?cmd=del&id=${stu.id}">删除</a>&nbsp;&nbsp;
            <a href="/xx/controller?cmd=turntoedit&id=${stu.id}">修改</a>
        </td>
        </c:forEach>
    <tr>
        <td colspan="9">
            <a href="./controller?cmd=turntoadd">添加武林高手</a>
        </td>
    </tr>
    <tr>
        <td colspan="9" align="center">
            <c:choose>
                <c:when test="${pageBean.currentPage==pageBean.firstPage}">
                    首页&nbsp;上一页
                </c:when>
                <c:otherwise>
                    <a href="/xx/controller?cmd=query&currentPage=${pageBean.firstPage}&pageSize=${pageBean.pageSize}">首页</a>
                    <a href="/xx/controller?cmd=query&currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}">上一页</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageBean.currentPage==pageBean.totalPage}">
                    下一页&nbsp;尾页
                </c:when>
                <c:otherwise>
                    <a href="/xx/controller?cmd=query&currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}">下一页</a>
                    <a href="/xx/controller?cmd=query&currentPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}">尾页</a>
                </c:otherwise>
            </c:choose>
            当前第${pageBean.currentPage}页/共${pageBean.totalPage}页，
            共${pageBean.totalCount}条，每页显示<input type="text" name="pageSize" id="pageSize" size="2" value="${pageBean.pageSize}" onblur="changePageSize()">条。
        </td>
    </tr>
</table>
</body>
</html>
