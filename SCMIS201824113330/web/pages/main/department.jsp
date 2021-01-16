<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院系信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            showMsg("${sessionScope.msg}");
            $("#department").addClass("active");
        })
    </script>
</head>
<body>
<c:if test="${not empty sessionScope.msg}">
    <c:set scope="session" var="msg" value=""> </c:set>
</c:if>
<%@include file="/pages/common/navigation.jsp"%>
<div class="container">
    <div class="row" >
        <h1 align="center">院系信息表</h1><br/>
        <div class="row my-search-add" style="width: 670px">
            <div class="col-xs-11">
                <div class="row">
                    <div class="input-group">
                        <span class="col-xs-3">学院编号</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="byno">
                        </div>
                        <span class="col-xs-3">学院名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 120px;height: 28px" id="byname">
                        </div>
                        <span class="col-xs-3">学院主任</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="bymanager">
                        </div>
                        <div class="col-xs-3">
                            <button class="btn btn-primary" onclick="MySearch('department');">查询</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-1">
                <a class="btn btn-success add" href="javascript:void(0)" role="button" onclick="PopUpsForm($('#addDepartment'),'添加学生','430px','280px')">添加</a>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped table-bordered my-table-style">
                <thead>
                <tr>
                    <th class="my-th">学院编号</th>
                    <th class="my-th">学院名</th>
                    <th class="my-th">学院主任</th>
                    <th class="my-th">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.departments}" var="department">
                    <tr>
                        <td>${department.dno}</td>
                        <td>${department.dname}</td>
                        <td>${department.dmanager}</td>
                        <td>
                            <div class="aa">
                            <a href="javascript:void(0)" onclick='PopUpsForm($("#modifyDepartment"),"修改学院信息","430px","280px","1",
                                    "${department.dno}","${department.dname}","${department.dmanager}")'>修改</a>
                            <a href="manager/departmentServlet?action=delete&dno=${department.dno}">删除</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

<%--添加学院 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/departmentServlet?action=add" method="post" id="addDepartment">
    <div class="form-group">
        <label for="dno" class="col-xs-3 control-label Pop-up-form">学院编号</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="dno" placeholder="请输入学院编号" name="dno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="dname" class="col-xs-3 control-label Pop-up-form">学院名称</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="dname" placeholder="请输入姓名" name="dname" required>
        </div>
    </div>
    <div class="form-group">
        <label for="dmanager" class="col-xs-3 control-label Pop-up-form">学院主任</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="dmanager" placeholder="请输入学院主任姓名" name="dmanager" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">添加</button>
    </div>
</form>

<%--修改学院 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/departmentServlet?action=modify" method="post" id="modifyDepartment">
    <div class="form-group">
        <label for="0" class="col-xs-3 control-label Pop-up-form">学院编号</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="0" placeholder="请输入学院编号" name="dno" readonly>
        </div>
    </div>
    <div class="form-group">
        <label for="1" class="col-xs-3 control-label Pop-up-form">学院名称</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="1" placeholder="请输入姓名" name="dname" required>
        </div>
    </div>
    <div class="form-group">
        <label for="2" class="col-xs-3 control-label Pop-up-form">学院主任</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="2" name="dmanager" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">修改</button>
    </div>
</form>
</html>

