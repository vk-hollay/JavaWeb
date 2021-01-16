<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/4
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--编码格式：enctype="multipart/form-data" --%>
    <form action="/07_file_upload_download/uploadServlet" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="username"/> </br>
        文件：<input type="file" name="photo"> </br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
