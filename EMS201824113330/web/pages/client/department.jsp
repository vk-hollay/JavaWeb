<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/5
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>部门信息</title>
    <style type="text/css">

        .body {
            /*background-color:#f4f0ff;*/
        }
        .container-fluid {
            margin: 20px auto;
        }
        .table {
            width: 500px;
            margin: 0 auto;
        }
        .prompt {
             display: block;
             text-align: center;
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
            /* 页面完全加载后才出 alert 弹窗*/
            window.onload = function() {
                if (${not empty sessionScope.msg}) {
                    alert("${sessionScope.msg}");
                }
            };

            var prompt = $(".prompt");
            $("#add_department_btn, #alter_department_btn").click(function () {
                prompt.css('visibility','hidden');
            });
            $(".submit_btn").click(function () {
                var menthod = $(this).val();
                //alert(menthod);
                var id, name;
                if (menthod === "add") {
                    id = $("#id").val();
                    name = $("#name").val();
                    if (id === "" || name === "") {
                        prompt.text("部门代号和名称不得为空！");
                        prompt.css('color','red');
                    } else {
                        $("#add_form").submit();
                    }
                } else if (menthod === "modify") {
                    id = $("#new_id").val();
                    name = $("#new_name").val();
                    if (id === "" || name === "") {
                        prompt.text("部门代号和名称不得为空！");
                        prompt.css('color','red');
                    } else {
                        $("#modify_form").submit();
                    }
                }
                prompt.css('visibility','visible');
            });
            $("a.delete").click(function () {
                return confirm("是否确定删除【" + $(this).parent().parent().find("td").eq(1).text() + "】？")
            });
            $("#alter_department").on('show.bs.modal', function (event) {
                var btn = $(event.relatedTarget); // Button that triggered the modal
                var id = btn.data("id"); // Extract info from data-* attributes
                var name = btn.data("name");
                var manager = btn.data("manager");
                //console.log(id + " " + name + " " + manager);
                var modal = $(this); //Update the modal's content.
                modal.find("#oldId").val(id);
                modal.find("#new_id").val(id);
                modal.find("#new_name").val(name);
                modal.find("#new_manager").val(manager);
            });
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
                <li class="active">
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

            <h1 style="text-align: center; margin-top: 40px">
                <strong>部门信息表</strong>
            </h1>
            <br/>

            <%--搜索框和添加按钮--%>

            <div class="<%--col-xs-offset-2--%>" style="margin: 0 auto; width: 470px">
                <div class="row" style="margin-bottom: 5px; padding-left: 28px">
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}"><div class="col-xs-9"></c:if>
                    <c:if test="${sessionScope.loginUser.userType == '普通员工'}"><div class="col-xs-11"></c:if>
                        <div class="input-group">
                            <span class="input-group-btn">
                                <select class="form-control" style="width: 106px" id="select">
                                     <option value="all">全部</option>
                                     <option value="id">部门代号</option>
                                     <option value="name">部门名称</option>
                                     <option value="manager">部门主管</option>
                                 </select>
                             </span>
                             <input type="text" class=" form-control" placeholder="请输入要搜索的信息" id="search" >
                             <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="inquire" value="departmentServlet" onclick="queryByCondition()">查询</button>
                             </span>
                        </div>
                    </div>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                    <div class="col-xs-3">
                         <span class="input-group">
                             <input class="btn btn-success" type="submit" id="add_department_btn" value="添加" data-toggle="modal" data-target="#add_department">
                         </span>
                    </div>
                    </c:if>
                </div>
            </div>

            <!-- Modal 响应添加按钮-->
            <div class="modal fade" id="add_department" tabindex="-1" role="dialog" aria-labelledby="add_department_Label">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="add_department_Label">添加部门信息</h4>
                        </div>
                        <div class="modal-body">
                            <form action="departmentServlet" method="post" id="add_form">
                                <input type="hidden" name="action" value="add">
                                <div class="form-group">
                                    <label for="id">部门代号</label>
                                    <input type="text" class="form-control" id="id" placeholder="请输入部门代号" name="id">
                                </div>
                                <div class="form-group">
                                    <label for="name">部门名称</label>
                                    <input type="text" class="form-control" id="name" placeholder="请输入部门名称" name="name">
                                </div>
                                <div class="form-group">
                                    <label for="manager">部门主管</label>
                                    <input type="text" class="form-control" id="manager" placeholder="请输入部门主管工号" name="manager">
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
                        <th> 部门代号 </th>
                        <th> 部门名称 </th>
                        <th> 部门主管 </th>
                        <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                        <th> 操作 </th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.departments}" var="department">
                        <tr>
                            <td> ${department.id} </td>
                            <td> ${department.name} </td>
                            <td> ${department.manager} </td>
                            <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                            <td>
                                <div class="astyle">
                                <a id="alter_department_btn" data-toggle="modal" data-target="#alter_department" data-id="${department.id}" data-name="${department.name}" data-manager="${department.manager}" href="#">修改</a>
                                <a class="delete" href="departmentServlet?action=delete&id=${department.id}" >删除</a>
                                </div>
                            </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <!-- Modal 响应修改事件-->
                    <div class="modal fade" id="alter_department" tabindex="-1" role="dialog" aria-labelledby="alter_department_Label">
                        <div class="modal-dialog modal-sm" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="alter_department_Label">修改部门信息</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="departmentServlet" method="post" id="modify_form">
                                        <input type="hidden" name="action" value="modify">
                                        <input type="hidden" id="oldId" name="oldId" value="">
                                        <div class="form-group">
                                            <label for="new_id">部门代号</label>
                                            <input type="text" class="form-control" id="new_id" placeholder="请输入部门代号" name="id">
                                        </div>
                                        <div class="form-group">
                                            <label for="new_name">部门名称</label>
                                            <input type="text" class="form-control" id="new_name" placeholder="请输入部门名称" name="name">
                                        </div>
                                        <div class="form-group">
                                            <label for="new_manager">部门主管</label>
                                            <input type="text" class="form-control" id="new_manager" placeholder="请输入部门主管工号" name="manager">
                                        </div>
                                    </form>
                                    <span class="prompt" >&nbsp;</span>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary submit_btn" value="modify">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>