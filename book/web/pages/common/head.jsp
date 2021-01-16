<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/9
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme() //获取 ip 协议
                    + "://"
                    + request.getServerName() //获取服务器主机名
                    + ":"
                    + request.getServerPort() //获取端口号
                    + request.getContextPath() //获取项目路径
                    + "/";

    //System.out.println(basePath);
//    pageContext.setAttribute("basePath", basePath);
%>

<!--写base标签，永远固定相对路径跳转的结果-->
<%--<base href="http://localhost:8080/EMS201824113330/">--%>
<%--改为动态的 base 标签值--%>
<base href="<%=basePath%>">

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>