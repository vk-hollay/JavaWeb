<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/2
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <%@include file="/pages/common/head.jsp"%>
    <title>登录</title>

    <style type="text/css">

      .container {
        margin: 110px auto;
        width: 430px;
      }
      .form-horizontal {
        padding-top: 28px;
        padding-left: 31px;
        padding-bottom: 6px;
        background-color: white;
        border-radius: 15px;/*边框圆角*/
        box-shadow: 0px 2px 2px 2px #e6e6e6;/*边框阴影*/
        width: 400px;
      }
      .alert-danger {
        width: 400px;
        visibility: hidden;
      }
      .body {
        background-color:#f4f0ff;
      }
      .form-control {
        width: 260px;
      }
      .btn {
        margin-right: 15px ;
      }
    </style>

    <script type="text/javascript">
      $(function () {
        window.onload = function() {
          getLoginCookie();
        };

        $("#login_btn").click(function () {
          var id = $("#id").val();
          var password = $("#password").val();
          var patter = /\S/;
          if (!patter.test(id) || !patter.test(password)) {
            $("span.msg").text("请正确输入账号和密码！");
            $("#prompt").css('visibility','visible');
            return false;
          }
        });

        var msg = '${requestScope.msg}';
        if (msg) {
          $("span.msg").text(msg);
          $("#prompt").css('visibility','visible');
        }

        $("#id").focus(function () {
          $("#prompt").css('visibility','hidden');
        });

        $("#regist_btn").click(function () {
          location.href = "pages/user/regist.jsp";
        })
      })
    </script>
  </head>
  <body class="body">
  <div class="container">
    <div class="row clearfix">
      <div class="col-md-12 column">
        <h1 style="text-align: center">设备保管及查询系统</h1><br/><br/>
        <div class="alert alert-danger" id="prompt">
          <strong>警告！</strong> <span class="msg"> ${requestScope.msg} </span>
        </div>
        <form class="form-horizontal" role="form" action="userServlet" method="post">
          <input type="hidden" name="action" value="login">
          <div class="form-group">
            <label for="id" class="col-sm-2 control-label">账号</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="id" placeholder="请输入账号" name="id" value="${requestScope.id}">
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <div class="checkbox">
                <label><input type="checkbox" name="remember" checked/>Remember me</label>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default" id="login_btn">登录</button>
              <button type="button" class="btn btn-default" id="regist_btn">注册</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <%@include file="/pages/common/footer.jsp" %>
  </body>
</html>
