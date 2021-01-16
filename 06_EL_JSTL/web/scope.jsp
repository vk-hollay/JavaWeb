<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/2
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        pageContext.setAttribute("key1", "pageContext1");
        pageContext.setAttribute("key2", "pageContext2");
        request.setAttribute("key2", "requset");
        session.setAttribute("key2", "session");
        application.setAttribute("key2", "application");
    %>
<%--    EL 获取四个特定域中的属性--%>
    ${ key2 } <br/>
    ${ applicationScope.key2 }  <br/>
    ${ requestScope.key2 }  <br/>
    ${ sessionScope.key2 }  <br/>
    ${ pageScope.key2 }  <br/>


</body>
</html>
