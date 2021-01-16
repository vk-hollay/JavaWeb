<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            getCourseOptions();
            showMsg("${sessionScope.msg}");
            $("#course").addClass("active");
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
        <h1 align="center">课程信息表</h1><br/>
        <div class="row my-search-add" style="width: 800px">
            <div class="col-xs-11">
                <div class="row">
                    <div class="input-group">
                        <span class="col-xs-3">课程号</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="byno">
                        </div>
                        <span class="col-xs-3">课程名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 120px;height: 28px" id="byname">
                        </div>
                        <span class="col-xs-3">先行课名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 100px;height: 28px" id="bypname">
                        </div>
                        <span class="col-xs-3">学分</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 60px;height: 28px" id="bycredit">
                        </div>
                        <div class="col-xs-3">
                            <button class="btn btn-primary" onclick="MySearch('course');">查询</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-1">
                <a class="btn btn-success add" href="javascript:void(0)" role="button" onclick="PopUpsForm($('#addCourse'),'添加课程','430px','325px')">添加</a>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped table-bordered my-table-style">
                <thead>
                <tr>
                    <th class="my-th">课程号</th>
                    <th class="my-th">课程名</th>
                    <th class="my-th">学分</th>
                    <th class="my-th">先行课</th>
                    <th class="my-th">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.courses}" var="course">
                    <tr>
                        <td>${course.cno}</td>
                        <td>${course.cname}</td>
                        <td>${course.ccredit}</td>
                        <td>${course.cpname}</td>
                        <td>
                            <div class="aa">
                                <a href="javascript:void(0)" onclick='PopUpsForm($("#modifyCourse"),"修改课程信息","430px","325px","1",
                                        "${course.cno}","${course.cname}","${course.ccredit}","${course.cpno}")'>修改</a>
                                <a href="manager/courseServlet?action=delete&cno=${course.cno}">删除</a>
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

<%--添加课程 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/courseServlet?action=add" method="post" id="addCourse">
    <div class="form-group">
        <label for="cno" class="col-xs-3 control-label Pop-up-form">课&nbsp;程&nbsp;号&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="cno" placeholder="请输入课程号" name="cno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="cname" class="col-xs-3 control-label Pop-up-form">课程名称</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="cname" placeholder="请输入课程名" name="cname" required>
        </div>
    </div>
    <div class="form-group">
        <label for="ccredit" class="col-xs-3 control-label Pop-up-form">学&emsp;分&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="ccredit" placeholder="请输入学分" name="ccredit" required>
        </div>
    </div>
    <div class="form-group">
        <label for="cpno" class="col-xs-3 control-label Pop-up-form">先&nbsp;行&nbsp;课&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control course-options" id="cpno" name="cpno">
                <option> </option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">添加</button>
    </div>
</form>

<%--修改课程 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/courseServlet?action=modify" method="post" id="modifyCourse">
    <div class="form-group">
        <label for="0" class="col-xs-3 control-label Pop-up-form">课&nbsp;程&nbsp;号&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="0" placeholder="请输入课程号" name="cno" readonly>
        </div>
    </div>
    <div class="form-group">
        <label for="1" class="col-xs-3 control-label Pop-up-form">课程名称</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="1" placeholder="请输入课程名称" name="cname" required>
        </div>
    </div>
    <div class="form-group">
        <label for="2" class="col-xs-3 control-label Pop-up-form">学&emsp;分&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="2" name="ccredit" placeholder="请输入学分" required>
        </div>
    </div>
    <div class="form-group">
        <label for="3" class="col-xs-3 control-label Pop-up-form">先&nbsp;行&nbsp;课&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control course-options" id="3" name="cpno">
                <option> </option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">修改</button>
    </div>
</form>
</html>

