<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/1
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page  errorPage="/error500.jsp" %>
<!--
errorPage表示错误后自动跳转去的路径　<br/>
这个路径一般都是以斜杠打头，它表示请求地址为http://ip:port/工程路径/
映射到代码的 Web目录
-->
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--出现错误--%>
    <%= 12 / 0 %>

</body>
</html>
