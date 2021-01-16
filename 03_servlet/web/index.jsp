<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/10/27
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
<%--  静态包含--%>
  <%--  <%@ include file="form.html"%>--%>

<%--动态包含，，可传递参数--%>
  <jsp:include page="form.html"></jsp:include>

  <br/><br/><hr/><br/>
  这是Web下的index.jsp<br/>
  <a href="a/b/c.html">跳转到：a/b/c.html</a><br/>
  <a href="http://localhost:8080/03_servlet/forwardC">利用请求转发跳转：a/b/c.html</a>
  </body>
</html>
