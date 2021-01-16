<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/5
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <meta http-equiv="refresh" content="3">--%>
    <%@include file="/pages/common/head.jsp"%>

    <title>员工信息</title>

    <style type="text/css">

        .body {
            /*background-color:#f4f0ff;*/
        }
        .container-fluid {
            margin: 20px auto;
        }
        .table {
            margin: 0 auto;
            width: 900px;
        }
        .prompt {
            display: block;
            text-align: center;
        }
        .modal-dialog {
            width: 350px;
        }
        .astyle a {
            text-decoration: none;
        }
        .astyle a:hover {
            color: lightcoral;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            getDepartmentOptions();

            /* 页面完全加载后才出 alert 弹窗*/
            window.onload = function() {
                if (${not empty sessionScope.msg}) {
                    alert("${sessionScope.msg}");
                }
            };

            var prompt = $(".prompt");
            var idExists = false;
            $("#add_employee_btn, #alter_employee_btn").click(function () {
                $("#id, #name, #password, #phone").val("");
                prompt.css('visibility', 'hidden');
            });
            $(".submit_btn").click(function () {
                 var menthod = $(this).val();
                 var id, password;
                 if (menthod === "add") {
                     id = $("#id").val();
                     password = $("#password").val();
                     if (id === "" || password === "") {
                         prompt.text("工号和密码不得为空!");
                         prompt.css('color','red');
                     } else if (idExists) {
                         prompt.text("工号已存在！");
                         prompt.css('color','red');
                     } else {
                         $("#add_form").submit();
                     }
                 } else if (menthod === "modify") {
                     var oldId = $("#oldId").val();
                     id = $("#new_id").val();
                     password = $("#new_password").val();
                     if (id === "" || password === "") {
                         prompt.text("工号和密码不得为空!");
                         prompt.css('color','red');
                     } else if (idExists && id !== oldId) {
                         prompt.text("工号已存在！");
                         prompt.css('color','red');
                     } else {
                         $("#modify_form").submit();
                     }
                 }
                 prompt.css('visibility','visible');
             });
            $("#id, #new_id").blur(function () {
                var id = this.value;
                if (id !== "") {
                    $.getJSON("userServlet", "action=existsId&id=" + id, function (data) {
                        if (data.existsId != null) {
                            //prompt.text("工号已存在！");
                            //prompt.css('color','red');
                            //prompt.css('visibility','visible');
                            idExists = true;
                        } else {
                            //prompt.css('visibility','hidden');
                            idExists = false;
                        }
                    })
                }
            });
            $("a.delete").click(function () {
                return confirm("是否确定删除工号【" + $(this).parent().parent().find("td:first").text() + "】的用户？")
            });
            $("#alter_employee").on('show.bs.modal', function (event) {
                var btn = $(event.relatedTarget); // Button that triggered the modal
                var id = btn.data("id"); // Extract info from data-* attributes
                var name = btn.data("name"); // Extract info from data-* attributes
                var phone = btn.data("phone");
                var pwd = btn.data("pwd");
                var userType = btn.data("usertype");
                var department = btn.data("department");

                var modal = $(this); //Update the modal's content.
                modal.find("#oldId").val(id);
                modal.find("#new_id").val(id);
                modal.find("#new_name").val(name);
                modal.find("#new_password").val(pwd);
                modal.find("#new_phone").val(phone);
                modal.find("#new_department").val(department);
                // 根据 value值设置 radio的默认选中值
                $("input:radio[value=" + userType + "]").prop('checked', true);
            })
        })
    </script>
</head>
<body class="body">
<c:if test="${not empty sessionScope.msg}">
    <c:set var="msg" value="" scope="session"> </c:set>
</c:if>
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
                <li class="active">
                    <a href="employeeServlet?action=showAll"><strong>员工</strong></a>
                </li>
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

            <h1 style="text-align: center; margin-top: 40px">
                <strong>员工信息表</strong>
            </h1>
            <br/>

            <%--搜索框和添加按钮--%>
            <div class="<%--col-xs-offset-4--%>" style="margin: 0px auto; width: 470px;">
                <div class="row" style="margin-bottom: 5px;padding-left: 28px">
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}"><div class="col-xs-9"></c:if>
                    <c:if test="${sessionScope.loginUser.userType == '普通员工'}"><div class="col-xs-11"></c:if>
                        <div class="input-group">
                            <span class="input-group-btn">
                                 <select class="form-control" style="width: 106px;" id="select">
                                    <option value="all">全部</option>
                                    <option value="id">员工工号</option>
                                    <option value="name">员工姓名</option>
                                    <option value="phone">联系电话</option>
                                    <option value="userType">用户类型</option>
                                    <option value="department">部门编号</option>
                                 </select>
                             </span>
                            <input type="text" class=" form-control" id="search" placeholder="请输入要搜索的信息" >
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="inquire" value="employeeServlet" onclick="queryByCondition()">查询</button>
                            </span>
                        </div>

                    </div>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                    <div class="col-xs-3">
                         <span class="input-group">
                            <button class="btn btn-success" id="add_employee_btn" data-toggle="modal" data-target="#add_employee">添加</button>
                         </span>
                    </div>
                    </c:if>
                </div>
            </div>

            <!-- Modal 响应添加按钮-->
            <div class="modal fade" id="add_employee" tabindex="-1" role="dialog" aria-labelledby="add_employeent_Label">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="add_employee_Label">添加员工信息</h4>
                        </div>
                        <div class="modal-body">
                            <form action="employeeServlet?action=addEmployee" method="post" id="add_form">
                                <div class="form-group">
                                    <label for="id">工号</label>
                                    <input type="text" class="form-control" id="id" placeholder="请输入工号" name="id" >
                                </div>
                                <div class="form-group">
                                    <label for="name">姓名</label>
                                    <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="name">
                                </div>
                                <div class="form-group">
                                    <label for="password">密码</label>
                                    <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password" >
                                </div>
                                <div class="form-group">
                                    <label for="phone">联系电话</label>
                                    <input type="text" class="form-control" id="phone" placeholder="请输入联系电话" name="phone">
                                </div>
                                <div class="form-group">
                                    <label>所属部门</label>
                                    <select class="form-control department_option" name="department" > </select>
                                </div>
                                <div class="form-group">
                                    <label>用户类型</label>&emsp;
                                    <input type="radio" name="userType" id="optionsRadios1" value="普通员工" checked>普通员工&nbsp;
                                    <input type="radio" name="userType" id="optionsRadios2" value="系统管理员">系统管理员
                                </div>
                            </form>
                            <span class="prompt">&nbsp;</span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary submit_btn" value="add">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                    <th>密码</th>
                    </c:if>
                    <th>联系电话</th>
                    <th>用户类型</th>
                    <th>所属部门</th>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                    <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.employees}" var="employee">
                    <tr>
                        <td> ${employee.id} </td>
                        <td> ${employee.name} </td>
                        <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                        <td> ${employee.password}</td>
                        </c:if>
                        <td> ${employee.phone} </td>
                        <td> ${employee.userType} </td>
                        <td> ${employee.department} </td>
                        <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                        <td>
                            <div class="astyle">
                            <a href="#" id="alter_employee_btn" data-toggle="modal" data-target="#alter_employee" data-id="${employee.id}"
                               data-name="${employee.name}" data-userType="${employee.userType}" data-pwd="${employee.password}"
                               data-phone="${employee.phone}" data-department="${employee.department}">修改</a>
                            <a class="delete" href="employeeServlet?action=deleteEmployee&id=${employee.id}" >删除</a>
                            </div>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- Modal 响应修改事件-->
            <div class="modal fade" id="alter_employee" tabindex="-1" role="dialog" aria-labelledby="alter_employee_Label">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="alter_employee_Label">修改员工信息</h4>
                        </div>
                        <div class="modal-body">
                            <form action="employeeServlet?action=changeAllInfo" method="post" id="modify_form">
                                <input type="hidden" id="oldId" name="oldId" value="">
                                <div class="form-group">
                                    <label for="new_id">工号</label>
                                    <input type="text" class="form-control" id="new_id" placeholder="请输入工号" name="id" >
                                </div>
                                <div class="form-group">
                                    <label for="new_name">姓名</label>
                                    <input type="text" class="form-control" id="new_name" placeholder="请输入姓名" name="name">
                                </div>
                                <div class="form-group">
                                    <label for="new_password">密码</label>
                                    <input type="password" class="form-control" id="new_password" placeholder="请输入密码" name="password" >
                                </div>
                                <div class="form-group">
                                    <label for="new_phone">联系电话</label>
                                    <input type="text" class="form-control" id="new_phone" placeholder="请输入联系电话" name="phone">
                                </div>
                                <div class="form-group">
                                    <label>所属部门</label>
                                    <select class="form-control department_option" id="new_department" name="department"> </select>
                                </div>
                                <div class="form-group">
                                    <label>用户类型</label>&emsp;
                                    <input type="radio" name="userType" value="普通员工" checked>普通员工&nbsp;
                                    <input type="radio" name="userType" value="系统管理员">系统管理员
                                </div>
                            </form>
                            <span class="prompt">&nbsp;</span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary submit_btn" value="modify">提交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

