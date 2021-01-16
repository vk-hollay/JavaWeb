<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/5
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>我的信息</title>

    <style type="text/css">

        .body {
            /*background-color:#f4f0ff;*/
        }
        .container-fluid {
            margin: 20px auto;
        }
        .table {
            width: 300px;
            margin: 0 auto;
        }
        .cls {
            margin: 15px auto;
            width: 300px;
        }
        #changePwdPrompt, #changeInfoPrompt{
            visibility: hidden;
            margin-left: 92px;
        }
        #changeInfo_btn {
            margin-left: 130px;
        }
    </style>

    <script type="text/javascript">

        $(function () {
            getDepartmentOptions();

            var changePwdPrompt = $("#changePwdPrompt");
            var changeInfoPrompt = $("#changeInfoPrompt");

            $("#changePwd_btn").click(function () {
                $("#new_pwd, #repwd").val("");
                changePwdPrompt.css('visibility','hidden');
            });
            $("#changeInfo_btn").click(function () {
                $("#department").find("option[value=" + ${sessionScope.loginUser.department} + "]").attr("selected", true);
                changeInfoPrompt.css('visibility','hidden');
            });
             $("#save_change").click(function () {
                 var newPwd = $("#new_pwd").val();
                 var rePwd = $("#repwd").val();
                 if (newPwd === "" || rePwd === "") {
                     changePwdPrompt.html("请正确输入");
                     changePwdPrompt.css('color','red');
                 } else if (newPwd !== rePwd) {
                     changePwdPrompt.html("两次密码不一致");
                     changePwdPrompt.css('color','red');
                 } else {
                     $.getJSON("employeeServlet", "action=changePassword&id=${sessionScope.loginUser.id}&newPassword=" + newPwd, function (data) {
                         //console.log(data)
                         if (data.result === 1) {
                             changePwdPrompt.text("密码修改成功！");
                             changePwdPrompt.css('color','green');
                         }
                     })
                 }
                 changePwdPrompt.css('visibility','visible');
             });
             $("#new_pwd, #repwd").focus(function () {
                 changePwdPrompt.css('visibility','hidden');
             });
             $("#save_change_2").click(function () {
                 var name = $("#name").val();
                 var phone = $("#phone").val();
                 if (name === "" || phone === "") {
                     changeInfoPrompt.text("信息不得为空");
                     changeInfoPrompt.css("color", "red");
                 } else {
                     //alert( $("#changeInfo_form").serialize());
                     // 把参数序列化  serialize()可以把表单中所有表单项的内容都获取到，并以 name=value&name=value 的形式进行拼接。
                     $.getJSON("employeeServlet","action=changeBasicInfo&id=${sessionScope.loginUser.id}&" + $("#changeInfo_form").serialize(),function (data) {
                         //console.log(data);
                         if (data.result === 1) {
                             changeInfoPrompt.text("信息修改成功！");
                             changeInfoPrompt.css("color", "green");
                         }
                     });
                 }
                 changeInfoPrompt.css('visibility','visible');
             });
             $("#close").click(function () {
                 location.href = "employeeServlet?action=refreshLoginUserInfo&id=${sessionScope.loginUser.id}";
             });
        })
    </script>
</head>
<body class="body">
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <ul class="nav nav-tabs">
                <li>
                    <a href="equipmentServlet?action=showAll"><strong>设备</strong></a>
                </li>
                <li>
                    <a href="departmentServlet?action=showAll"><strong>部门</strong></a>
                </li>
                <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                <li>
                    <a href="employeeServlet?action=showAll"><strong>员工</strong></a>
                </li>
                </c:if>
                <li class="dropdown pull-right">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">欢迎您！${sessionScope.loginUser.name}！</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="pages/user/myInfo.jsp">我的信息</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="userServlet?action=logout">注销</a>
                        </li>
                    </ul>
                </li>
            </ul>

            <h3 style="text-align: center; margin-top: 40px">
                <strong>我的信息</strong>
            </h3>
            <br/>
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th width="80px"> 工号 </th>
                    <td> ${sessionScope.loginUser.id} </td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th> 姓名 </th>
                    <td> ${sessionScope.loginUser.name} </td>
                </tr>
                </tbody>
                <tbody>
                <tr>
                    <th> 联系电话 </th>
                    <td> ${sessionScope.loginUser.phone} </td>
                </tr>
                </tbody>
                <tbody>
                <tr>
                    <th> 用户类型 </th>
                    <td> ${sessionScope.loginUser.userType} </td>
                </tr>
                <tr>
                    <th> 所属部门 </th>
                    <td> ${sessionScope.loginUser.department} </td>
                </tr>

                </tbody>
            </table>
            <div class="cls">
                <!-- Button trigger modal -->
                <input class="btn btn-warning" type="submit" id="changeInfo_btn" value="修改信息" data-toggle="modal" data-target="#alter_msg">
                <input class="btn btn-info " type="submit" id="changePwd_btn" value="修改密码" data-toggle="modal" data-target="#alter_pwd">
            </div>

            <!-- Modal -->
            <div class="modal fade" id="alter_pwd" tabindex="-1" role="dialog" aria-labelledby="alter_pwd_Label">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="alter_pwd_Label">修改密码</h4>
                        </div>
                        <div class="modal-body">
                            <form method="post" id="form">
                                <div class="form-group">
                                    <label for="new_pwd">新密码</label>
                                    <input type="password" class="form-control" id="new_pwd" placeholder="请输入新密码">
                                </div>
                                <div class="form-group">
                                    <label for="repwd">确认密码</label>
                                    <input type="password" class="form-control" id="repwd" placeholder="请再次输入密码" name="newPassword">
                                </div>
                            </form>
                            <span id="changePwdPrompt">&nbsp;</span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="save_change">保存更改</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="alter_msg" tabindex="-1" role="dialog" aria-labelledby="alter_msg_Label">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="alter_msg_Label">修改信息</h4>
                    </div>
                    <div class="modal-body">
                        <form method="post" id="changeInfo_form">
                            <div class="form-group">
                                <label for="name">姓名</label>
                                <input type="text" class="form-control" id="name" placeholder="" name="name" value="${sessionScope.loginUser.name}">
                            </div>
                            <div class="form-group">
                                <label for="phone">电话</label>
                                <input type="tel" class="form-control" id="phone" placeholder="" name="phone" value="${sessionScope.loginUser.phone}">
                            </div>
                            <div class="form-group">
                                <label>部门</label>
                                <select class="form-control department_option" id="department" name="department"> </select>
                            </div>
                        </form>
                        <span id="changeInfoPrompt">&nbsp;</span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                        <button type="button" class="btn btn-primary" id="save_change_2">保存更改</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
