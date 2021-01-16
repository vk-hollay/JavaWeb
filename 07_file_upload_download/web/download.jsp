<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/5
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <form action="/07_file_upload_download/downloadServlet" method="get">
        <input type="submit" name="fileName" value="d.jpg"><a>d.jpg</a>
    </form>--%>
<%
    ArrayList<String> fileList = new ArrayList<>();
    File file = new File("E:\\IdeaJavaProject\\JavaWeb\\07_file_upload_download\\web\\file");
    File[] files = file.listFiles();
    for (File f: files) {
        fileList.add(f.getName());
        System.out.println(f.getName());
    }

%>
    <%
        for (String fileName: fileList) {
    %>
        <a href="/07_file_upload_download/downloadServlet?fileName=<%=fileName %>"> <%=fileName%> </a> <br/>
<%--        <a href="/07_file_upload_download/downloadServlet?fileName=<%=fileList.get(1) %>"> <%=fileList.get(1)%> </a> <br/>--%>
    <%
        }
    %>
</body>
</html>
