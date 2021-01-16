<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/10
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
      $(function () {
        // 给验证码的图片，绑定单击事件
        $("#code_img").click(function () {
          // 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
          // src 属性表示验证码 img 标签的 图片路径。它可读，可写
          // alert(this.src);
          //this.src = "http://localhost:8080/tmp_VerificationCode/kaptcha.jpg?d=" + new Date();
          this.src = "kaptcha.jpg?d=" + new Date();
        });
      });
    </script>
  </head>
  <body>
  <form action="registServlet" method="get">
    用户名：<input type="text" name="username" > <br>
    验证码：<input type="text" style="width: 60px;" name="code">
    <img id="code_img" src="kaptcha.jpg" alt="" style="width: 100px; height: 28px;"> <br>
    <input type="submit" value="登录">
  </form>
  </body>
</html>
