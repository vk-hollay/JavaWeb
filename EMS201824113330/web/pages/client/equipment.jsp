<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/5
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>设备信息</title>

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
        .modal-dialog {
            width: 460px;
        }
        .form-control {
            width: 280px;
        }
        img {
            height: 70px;
            width: 80px;
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

            $("#add_equipment_btn, #alter_equipment_btn").click(function () {
                $("#id, #name, #specification, #buyDate, #price, #imgPath, #position, #manager").val("");
                prompt.css('visibility','hidden');
            });
            $("#id,#new_id").focus(function () {
                prompt.css('visibility','hidden');
            });

            $(".submit_btn").click(function () {
                var menthod = $(this).val();
                var id;
                //console.log(menthod);
                if (menthod === "add") {
                    id = $("#id").val();
                    if (id === "") {
                        console.log(id)
                        prompt.text("设备编号不得为空");
                        prompt.css('color', 'red');
                    } else if (fileIsLegal != null && fileIsLegal === false) {
                        prompt.text("文件不合法");
                        prompt.css('color', 'red');
                    } else {
                        $("#add_form").submit();
                    }
                } else if (menthod === "modify") {
                    id = $("#new_id").val();
                    if (id === "") {
                        prompt.text("设备编号不得为空");
                        prompt.css('color', 'red');
                    } else if (fileIsLegal != null && fileIsLegal === false) {
                        prompt.text("文件不合法");
                        prompt.css('color', 'red');
                    } else {
                        $("#modify_form").submit();
                    }
                }
                prompt.css('visibility','visible');
            });

            $("a.delete").click(function () {
                return confirm("是否确定删除编号为【" + $(this).parent().parent().find("td").eq(1).text() + "】的设备？")
            });

            $("#alter_equipment").on('show.bs.modal', function (event) {
                var btn = $(event.relatedTarget); // Button that triggered the modal
                var id = btn.data("id"); // Extract info from data-* attributes
                var name = btn.data("name"); // Extract info from data-* attributes
                var specification = btn.data("specification");
                var price = btn.data("price");
                var buyDate = btn.data("buydate");
                var position = btn.data("position");
                var imgPath = btn.data("imgpath");
                var manager = btn.data("manager");
                console.log(imgPath + "  " + id + "  " + name + "  " + specification + "  " + buyDate + "  " + position + "  " + price);

                var modal = $(this); //Update the modal's content.
                modal.find("#oldId").val(id);
                modal.find("#new_id").val(id);
                modal.find("#new_name").val(name);
                modal.find("#new_specification").val(specification);
                modal.find("#new_price").val(price);
                modal.find("#new_buyDate").val(buyDate);
                modal.find("#new_position").val(position);
                modal.find("#new_manager").val(manager);

                modal.find("#originalImgPath").val(imgPath);
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
                <li class="active">
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

            <h1 style="text-align: center; margin-top: 40px">
                <strong>设备信息表</strong>
            </h1>
            <br/>

            <%--搜索框和添加按钮--%>
            <div class="<%--col-xs-offset-4--%>" style="margin: 0 auto; width: 470px;">
                <div class="row" style="margin-bottom: 5px;padding-left: 28px">
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}"><div class="col-xs-9"></c:if>
                    <c:if test="${sessionScope.loginUser.userType == '普通员工'}"><div class="col-xs-11"></c:if>
                        <div class="input-group">
                            <span class="input-group-btn">
                                 <select class="form-control" style="width: 106px;" id="select">
                                    <option value="all">全部</option>
                                    <option value="id">设备编号</option>
                                    <option value="name">设备名称</option>
                                    <option value="specification">设备规格</option>
                                    <option value="buyDate">购入年份</option>
                                    <option value="price">价格</option>
                                    <option value="position">存放位置</option>
                                    <option value="manager">负责人</option>
                                 </select>
                             </span>
                            <input type="text" class=" form-control" id="search" placeholder="请输入要搜索的信息" >
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="inquire" value="equipmentServlet" onclick="queryByCondition()">查询</button>
                             </span>
                        </div>
                    </div>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                    <div class="col-xs-3">
                         <span class="input-group">
                             <input class="btn btn-success" type="submit" id="add_equipment_btn" value="添加" data-toggle="modal" data-target="#add_equipment">
                         </span>
                    </div>
                    </c:if>
                </div>
            </div>

            <!-- Modal 响应添加按钮-->
            <div class="modal fade" id="add_equipment" tabindex="-1" role="dialog" aria-labelledby="add_equipment_Label">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="add_equipment_Label">添加设备信息</h4>
                        </div>
                        <div class="modal-body">
                            <form action="equipmentServlet?action=addEquipment" method="post" enctype="multipart/form-data" class="form-horizontal" id="add_form">
<%--                                <input type="hidden" name="action" value="addEquipment">--%>
                                <div class="form-group">
                                    <label for="id" class="control-label col-sm-3">编&emsp;&emsp;号</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="id" placeholder="请输入编号" name="id" required>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label for="name" class="control-label col-sm-3">名&emsp;&emsp;称</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="name" placeholder="请输入名称" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="specification" class="control-label col-sm-3">规&emsp;&emsp;格</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="specification" placeholder="请输入规格" name="specification">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="buyDate" class="control-label col-sm-3">购入日期</label>
                                    <div class="col-sm-9">
                                        <input type="date" class="form-control" id="buyDate" name="buyDate">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="price" class="control-label col-sm-3">价&emsp;&emsp;格</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="price" placeholder="请输入价格" name="price">
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="control-label col-sm-3">图&emsp;&emsp;片</label>
                                    <div class="col-sm-9">
                                        <input type="file" id="imgPath" name="imgPath" onchange="checkFileSuffix(this.value)">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="position" class="control-label col-sm-3">存放位置</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="position" placeholder="请输入存放位置" name="position">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="manager" class="control-label col-sm-3">负&ensp;责&ensp;人</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="manager" placeholder="请输入负责人" name="manager">
                                    </div>
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

            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th width="90px">图片</th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>规格</th>
                    <th>购入日期</th>
                    <th>价格</th>
                    <th>存放位置</th>
                    <th>负责人</th>
                    <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.allEquipments}" var="equipment">
                    <tr>
                        <td width="90px">
                            <c:if test="${not empty equipment.imgPath}">
                                <img src="${equipment.imgPath}" alt="#" />
                            </c:if>
                        </td>
                        <td> ${equipment.id} </td>
                        <td> ${equipment.name} </td>
                        <td> ${equipment.specification} </td>
                        <td> ${equipment.buyDate} </td>
                        <td> ${equipment.price} </td>
                        <td> ${equipment.position} </td>
                        <td> ${equipment.manager} </td>
                        <c:if test="${sessionScope.loginUser.userType == '系统管理员'}">
                        <td>
                            <div class="astyle">
                            <a href="#" id="alter_equipment_btn" data-toggle="modal" data-target="#alter_equipment" data-id="${equipment.id}"
                               data-name="${equipment.name}" data-specification="${equipment.specification}" data-price="${equipment.price}"
                               data-buyDate="${equipment.buyDate}" data-position="${equipment.position}" data-imgPath="${equipment.imgPath}"
                               data-manager="${equipment.manager}">修改</a>
                            <a class="delete" href="equipmentServlet?action=delete&id=${equipment.id}" >删除</a>
                            </div>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

        <!-- Modal 响应修改按钮-->
        <div class="modal fade" id="alter_equipment" tabindex="-1" role="dialog" aria-labelledby="alter_equipment_Label">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="alter_equipment_Label">修改设备信息</h4>
                    </div>
                    <div class="modal-body">
                        <form action="equipmentServlet?action=changeEquipmentInfo" method="post" enctype="multipart/form-data" class="form-horizontal" id="modify_form">
                            <input type="hidden" id="oldId" name="oldId" value="" >
                            <input type="hidden" id="originalImgPath" name="originalImgPath" value="" >

                            <div class="form-group">
                                <label for="id" class="control-label col-sm-3">编&emsp;&emsp;号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_id" placeholder="请输入编号" name="id">
                                </div>
                            </div>
                            <div class="form-group" >
                                <label for="name" class="control-label col-sm-3">名&emsp;&emsp;称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_name" placeholder="请输入名称" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="specification" class="control-label col-sm-3">规&emsp;&emsp;格</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_specification" placeholder="请输入规格" name="specification">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="buyDate" class="control-label col-sm-3">购入日期</label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" id="new_buyDate" name="buyDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="price" class="control-label col-sm-3">价&emsp;&emsp;格</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_price" placeholder="请输入价格" name="price">
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="control-label col-sm-3">图&emsp;&emsp;片</label>
                                <div class="col-sm-9">
                                    <input type="file" id="new_imgPath" name="imgPath" onchange="checkFileSuffix(this.value)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="position" class="control-label col-sm-3">存放位置</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_position" placeholder="请输入存放位置" name="position">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="manager" class="control-label col-sm-3">负&ensp;责&ensp;人</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="new_manager" placeholder="请输入负责人" name="manager">
                                </div>
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
</body>
</html>
