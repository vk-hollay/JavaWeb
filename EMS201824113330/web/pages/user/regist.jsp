<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/3
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>注册</title>

    <style type="text/css">
        .container {
            margin: 80px auto;
            width: 600px;
        }
        .body {
            background-color:#f4f0ff;
        }
        .form-horizontal {
            padding-top: 30px;
            padding-left: 60px;
            padding-bottom: 5px;
            background-color: white;
            border-radius: 15px;/*边框圆角*/
            box-shadow: 0px 2px 2px 2px #e6e6e6;/*边框阴影*/
        }
        #img {
            width: 23px;
            height: 23px;
        }
        .form-control {
            width: 260px;
        }
        .control-label {
            width: 90px;
        }
        .btn {
            margin-right: 15px ;
        }
        a {
            text-decoration: none;
            font-size: 1.1em;
        }
        a:hover{
            color: red;
        }
    </style>

    <script type="text/javascript">

        $(function () {
            getDepartmentOptions();

            var ids = $("#id");
            var names = $("#name");
            var pwds = $("#password");
            var repwds = $("#repwd");
            var phones = $("#phone");
            var idExists = 0;
            // 注册页面， 用户名输入框， 光标丢失焦点事件, ajax判断用户名是否已存在
            ids.blur(function () {
                var id = this.value;
                if (id !== "") {
                    $.getJSON("userServlet", "action=existsId&id=" + id, function (data) {
                        //console.log(data)
                        if (data.existsId != null) {
                            $("span.id_msg").append("<img src='static/img/wrong.jpg' id='img' alt=''>");
                            idExists = 1;
                        } else {
                            $("span.id_msg").append("<img src='static/img/right.jpg' id='img' alt=''>");
                            idExists = 0;
                        }
                    })
                }
            });
            // 判断输入的密码和确认密码是否一致
            repwds.blur(function () {
                var password = pwds.val();
                var repwd = repwds.val();
                if (repwd !== "") {
                    if (password === repwd) {
                        $("span.repwd_msg").append("<img src='static/img/right.jpg' id='img' alt=''>");
                    } else {
                        $("span.repwd_msg").append("<img src='static/img/wrong.jpg' id='img' alt=''>");
                    }
                }
            });
            names.blur(function () {
                var name = names.val();
                if (name !== "") {
                    $("span.name_msg").append("<img src='static/img/right.jpg' id='img' alt=''>");
                }
            });
            pwds.blur(function () {
                var pwd = pwds.val();
                if (pwd !== "") {
                    $("span.pwd_msg").append("<img src='static/img/right.jpg' id='img' alt=''>");
                }
            });
            phones.blur(function () {
                var phone = phones.val();
                if (phone !== "") {
                    $("span.phone_msg").append("<img src='static/img/right.jpg' id='img' alt=''>");
                }
            });
            ids.focus(function () {
                $("span.id_msg img").remove()
            });
            names.focus(function () {
                $("span.name_msg img").remove()
            });
            pwds.focus(function () {
                $("span.pwd_msg img").remove()
            });
            repwds.focus(function () {
                $("span.repwd_msg img").remove()
            });

            phones.focus(function () {
                $("span.phone_msg img").remove()
            });
            $("#regists_btn").click(function () {
                var patter = /\S/;
                var id= ids.val();
                var name= names.val();
                var password = pwds.val();
                var repwd = repwds.val();
                var phone = phones.val();
                if (!patter.test(id)||!patter.test(name) ||!patter.test(password)||!patter.test(repwd)||!patter.test(phone)||password!==repwd||idExists===1) {
                    return false;
                }
            });
        })
    </script>
</head>
<body class="body">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="userServlet" method="post">
                <input type="hidden" name="action" value="regists">
                <div class="form-group">
                    <label for="id" class="col-sm-2 control-label">员&ensp;工&ensp;ID</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="id" placeholder="请输入员工编号" name="id">
                    </div>
                    <span class="col-sm-4 id_msg"><%--<img src="static/img/wrong.jpg" id="img">--%></span>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">姓&emsp;&emsp;名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="name">
                    </div>
                    <span class="col-sm-4 name_msg"></span>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密&emsp;&emsp;码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
                    </div>
                    <span class="col-sm-4 pwd_msg"></span>
                </div>
                <div class="form-group">
                    <label for="repwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="repwd" placeholder="确认密码" name="repwd">
                    </div>
                    <span class="col-sm-4 repwd_msg"></span>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">联系电话</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="phone" placeholder="请输入联系电话" name="phone">
                    </div>
                    <span class="col-sm-4 phone_msg"></span>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">所属部门</label>
                    <div class="col-sm-10">
                        <select class="form-control department_option" name="department"> </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户类型</label>
                    <div class="radio col-sm-10">
                        <label>
                            <input type="radio" name="userType" id="optionsRadios1" value="普通员工" checked>普通员工
                        </label>
                        <label>
                            <input type="radio" name="userType" id="optionsRadios2" value="系统管理员">系统管理员
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id="regists_btn">立即注册</button>
                        <a href="pages/user/login.jsp">已有账号，立即登录</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
