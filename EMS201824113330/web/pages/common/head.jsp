<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<%
    String basePath = request.getScheme() //获取 ip 协议
            + "://"
            + request.getServerName() //获取服务器主机名
            + ":"
            + request.getServerPort() //获取端口号
            + request.getContextPath() //获取项目路径
            + "/";

    //System.out.println(basePath);
    pageContext.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">


<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="static/js/jquery-3.2.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/js/bootstrap.min.js"></script>

<%--加载我的js文件--%>
<script src="static/js/myjs.js"></script>
