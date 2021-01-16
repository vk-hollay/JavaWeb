<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/1
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <br/><br/>------------jsp基本语法：-------------<br/><br/>
    <form action="a.jsp">
        <input type="submit">
    </form>
    <br/><br/>------------4个域对象及其作用范围：-------------<br/><br/>
    <form action="scope.jsp">
        <input type="submit">
    </form>

    <br/><br/>------------出现错误后页面跳转：-------------<br/><br/>
    <form action="b.jsp">
        <input type="submit">
    </form>

    <br/><br/>------------通过请求转发回传数据给页面：-------------<br/><br/>
    <form action="searchStudentServlet">
        <input type="submit">
    </form>
</body>
</html>
