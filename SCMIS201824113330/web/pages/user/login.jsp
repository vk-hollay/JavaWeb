<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <%@include file="/pages/common/head.jsp"%>
    <title>登录</title>

    <script type="text/javascript">
      $(function () {
        showMsg("${sessionScope.msg}");

        $("#modify-pwd-btn").click(function () {
          var pwd = $("#pwd").val();
          var repwd = $("#repwd").val();
          if (pwd !== repwd) {
            layer.msg("两次密码不一致！", {time : 1400});
            return false;
          }
        })
      })
    </script>
  </head>
  <body class="login_background">
  <c:if test="${not empty sessionScope.msg}">
    <c:set scope="session" var="msg" value=""> </c:set>
  </c:if>
  <div class="container login_page">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <h1 style="text-align: center"><strong>学生选课管理信息系统</strong></h1><br/><br/><br/><br/>
        <form class="form-horizontal my-login-form" role="form" action="userServlet?action=login" method="post">
          <input type="hidden" name="action" value="login">
          <div class="form-group">
            <label for="id" class="col-sm-2 col-xs-2 control-label">账号</label>
            <div class="col-sm-10 col-xs-10">
              <input type="text" class="form-control my-login-input" id="id" placeholder="请输入账号" name="id" required>
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-2 col-xs-2 control-label">密码</label>
            <div class="col-sm-10 col-xs-10">
              <input type="password" class="form-control my-login-input" id="password" placeholder="请输入密码" name="password" required>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-offset-2" style="padding-left: 18px">
              <div>
                <a href="javascript:void(0);" onclick="alterPwd();">忘记密码</a>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-offset-1" style="padding-left:14px; padding-right: 75px;">
              <button type="submit" class="btn btn-info btn-block" id="login_btn">登录</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

  <%--修改密码表单弹窗， display:none 隐藏--%>
  <form id="modifyPwd" class="hidden" action="userServlet?action=modifyPassword" method="post">
    <div class="form-group">
      <label for="id1">账号</label>
      <input type="text" class="form-control my-login-input" id="id1" placeholder="请输入账号" name="id" required>
    </div>
    <div class="form-group">
      <label for="pwd">密码</label>
      <input type="password" class="form-control my-login-input" id="pwd" placeholder="请输入新密码" name="pwd" required>
    </div>
    <div class="form-group">
      <label for="repwd">确认密码</label>
      <input type="password" class="form-control my-login-input" id="repwd" placeholder="请再次输入密码" required>
    </div>
    <div>
      <button type="submit" class="btn btn-primary" id="modify-pwd-btn">提交</button>
    </div>
  </form>
  </body>
</html>
