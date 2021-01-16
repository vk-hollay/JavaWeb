<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            getDepartmentOptions();
            showMsg("${sessionScope.msg}");
            $("#student").addClass("active");
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
            <h1 align="center">学生信息表</h1><br/>
            <div class="row my-search-add" style="width: 660px">
                <div class="col-xs-11">
                    <div class="row">
                         <div class="input-group">
                                <span class="col-xs-3">学号</span>
                                <div class="col-xs-3">
                                <input type="text" class="form-control" style="width: 120px;height: 28px" id="byno">
                                </div>
                                <span class="col-xs-3">姓名</span>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control" style="width: 80px;height: 28px" id="byname">
                                </div>
                                <div class="col-xs-3" >
                                <select class="col-xs-3 my-select" id="bysex">
                                    <option value="">性别</option>
                                    <option>男</option>
                                    <option>女</option>
                                </select>
                                </div>
                                <div class="col-xs-3 my-select" >
                                <select class="department-options col-xs-3 my-select" style="width: 132px" id="bydepart">
                                    <option value="">全部学院</option>
                                </select>
                                </div>
                                <div class="col-xs-3">
                                    <button class="btn btn-primary" onclick="MySearch('student');">查询</button>
                                </div>
                         </div>
                    </div>
                </div>
                <div class="col-xs-1">
                    <a class="btn btn-success" href="javascript:void(0)" role="button" onclick="PopUpsForm($('#addStudent'),'添加学生','430px','370px')">添加</a>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover table-striped table-bordered my-table-style">
                    <thead>
                        <tr>
                            <th class="my-th">学号</th>
                            <th class="my-th">姓名</th>
                            <th class="my-th">性别</th>
                            <th class="my-th">年龄</th>
                            <th class="my-th">系别</th>
                            <th class="my-th">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.students}" var="student">
                        <tr>
                            <td>${student.sno}</td>
                            <td>${student.sname}</td>
                            <td>${student.ssex}</td>
                            <td>${student.sage}</td>
                            <td>${student.sdname}</td>
                            <td>
                                <div class="aa">
                                <a href="javascript:void(0)" onclick='PopUpsForm($("#modifyStudent"),"修改学生信息","430px","370px","1","${student.sno}",
                                        "${student.sname}","${student.sbirthday}","${student.sdno}","${student.ssex}")'>修改</a>
                                <a href="manager/studentServlet?action=delete&sno=${student.sno}">删除</a>
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

<%--添加学生弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/studentServlet?action=add" method="post" id="addStudent">
    <div class="form-group">
        <label for="sno" class="col-xs-3 control-label Pop-up-form">学&emsp;号&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="sno" placeholder="请输入学号" name="sno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-xs-3 control-label Pop-up-form">姓&emsp;名&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="sname" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label Pop-up-form">性&emsp;别&nbsp;&nbsp;</label>
        <div class="radio col-xs-9">
            <label>
                <input type="radio" name="ssex" value="男" checked>男
            </label>
            <label>
                <input type="radio" name="ssex" value="女">女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="birthday" class="col-xs-3 control-label Pop-up-form">出生日期</label>
        <div class="col-xs-9">
            <input type="date" class="form-control" id="birthday" name="sbirthday" required>
        </div>
    </div>
    <div class="form-group">
        <label for="dno" class="col-xs-3 control-label Pop-up-form">所属学院</label>
        <div class="col-xs-9">
            <select class="form-control department-options" id="dno" name="sdno">
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">添加</button>
    </div>
</form>

<%--修改学生弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/studentServlet?action=modify" method="post" id="modifyStudent">
    <input type="hidden" id="oldNo" name="oldSNo" value="">
    <div class="form-group">
        <label for="0" class="col-xs-3 control-label Pop-up-form">学&emsp;号&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="0" placeholder="请输入学号" name="sno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="1" class="col-xs-3 control-label Pop-up-form">姓&emsp;名&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="1" placeholder="请输入姓名" name="sname" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label Pop-up-form">性&emsp;别&nbsp;&nbsp;</label>
        <div class="radio col-xs-9">
            <label>
                <input type="radio" name="ssex" value="男" checked>男
            </label>
            <label>
                <input type="radio" name="ssex" value="女">女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="2" class="col-xs-3 control-label Pop-up-form">出生日期</label>
        <div class="col-xs-9">
            <input type="date" class="form-control" id="2" name="sbirthday" required>
        </div>
    </div>
    <div class="form-group">
        <label for="3" class="col-xs-3 control-label Pop-up-form">所属学院</label>
        <div class="col-xs-9">
            <select class="form-control department-options" name="sdno" id="3">
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">修改</button>
    </div>
</form>

</html>

