<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            showMsg("${sessionScope.msg}");
            $("#teacher").addClass("active");
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
        <h1 align="center">教师信息表</h1><br/>
        <div class="row my-search-add" style="width: 730px;">
            <div class="col-xs-11">
                <div class="row">
                    <div class="input-group">
                        <span class="col-xs-3">教工号</span>
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
                            <select class="department-options col-xs-3 my-select" style="width:84px" id="byeb">
                                <option value="">学历</option>
                                <option>学士</option>
                                <option>硕士</option>
                                <option>博士</option>
                            </select>
                        </div>
                        <div class="col-xs-3 my-select" >
                            <select class="department-options col-xs-3 my-select" style="width:100px" id="bypt">
                                <option value="">职称</option>
                                <option>助教</option>
                                <option>讲师</option>
                                <option>副教授</option>
                                <option>教授</option>
                            </select>
                        </div>
                        <div class="col-xs-3">
                            <button class="btn btn-primary" onclick="MySearch('teacher');">查询</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-1">
                <a class="btn btn-success" href="javascript:void(0)" role="button" onclick="PopUpsForm($('#addTeacher'),'添加教师','430px','420px')">添加</a>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped table-bordered my-table-style">
                <thead>
                <tr>
                    <th class="my-th">教工号</th>
                    <th class="my-th">姓名</th>
                    <th class="my-th">性别</th>
                    <th class="my-th">年龄</th>
                    <th class="my-th">学历</th>
                    <th class="my-th">职称</th>
                    <th class="my-th" colspan="3">主讲课程</th>
                    <th class="my-th">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.teachers}" var="teacher">
                    <tr>
                        <td>${teacher.tno}</td>
                        <td>${teacher.tname}</td>
                        <td>${teacher.tsex}</td>
                        <td>${teacher.tage}</td>
                        <td>${teacher.teb}</td>
                        <td>${teacher.tpt}</td>
                        <td width="130px">${teacher.tcourse[0]}</td>
                        <td width="130px">${teacher.tcourse[1]}</td>
                        <td width="130px">${teacher.tcourse[2]}</td>
                        <td>
                            <div class="aa">
                            <a href="javascript:void(0)" onclick='PopUpsForm($("#modifyTeacher"),"修改教师信息","430px","420px","1","${teacher.tno}",
                                    "${teacher.tname}","${teacher.tbirthday}","${teacher.teb}","${teacher.tsex}","${teacher.tpt}")'>修改</a>
                            <a href="manager/teacherServlet?action=delete&tno=${teacher.tno}">删除</a>
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

<%--添加教师 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/teacherServlet?action=add" method="post" id="addTeacher">
    <div class="form-group">
        <label for="tno" class="col-xs-3 control-label Pop-up-form">学&emsp;号&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="tno" placeholder="请输入教工号" name="tno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-xs-3 control-label Pop-up-form">姓&emsp;名&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="tname" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label Pop-up-form">性&emsp;别&nbsp;&nbsp;</label>
        <div class="radio col-xs-9">
            <label>
                <input type="radio" name="tsex" value="男" checked>男
            </label>
            <label>
                <input type="radio" name="tsex" value="女">女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="birthday" class="col-xs-3 control-label Pop-up-form">出生日期</label>
        <div class="col-xs-9">
            <input type="date" class="form-control" id="birthday" name="tbirthday" required>
        </div>
    </div>
    <div class="form-group">
        <label for="teb" class="col-xs-3 control-label Pop-up-form">学&emsp;历&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control department-options" id="teb" name="teb">
                <option>学士</option>
                <option>硕士</option>
                <option>博士</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="tpt" class="col-xs-3 control-label Pop-up-form">职&emsp;称&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control department-options" id="tpt" name="tpt">
                <option>助教</option>
                <option>讲师</option>
                <option>副教授</option>
                <option>教授</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">添加</button>
    </div>
</form>

<%--修改学生弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="manager/teacherServlet?action=modify" method="post" id="modifyTeacher">
    <div class="form-group">
        <label for="0" class="col-xs-3 control-label Pop-up-form">学&emsp;号&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="0" placeholder="请输入教工号" name="tno" readonly>
        </div>
    </div>
    <div class="form-group">
        <label for="1" class="col-xs-3 control-label Pop-up-form">姓&emsp;名&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="1" placeholder="请输入姓名" name="tname" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label Pop-up-form">性&emsp;别&nbsp;&nbsp;</label>
        <div class="radio col-xs-9">
            <label>
                <input type="radio" name="tsex" value="男" checked>男
            </label>
            <label>
                <input type="radio" name="tsex" value="女">女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="2" class="col-xs-3 control-label Pop-up-form">出生日期</label>
        <div class="col-xs-9">
            <input type="date" class="form-control" id="2" name="tbirthday" required>
        </div>
    </div>
    <div class="form-group">
        <label for="3" class="col-xs-3 control-label Pop-up-form">学&emsp;历&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control department-options" id="3" name="teb">
                <option>学士</option>
                <option>硕士</option>
                <option>博士</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="5" class="col-xs-3 control-label Pop-up-form">职&emsp;称&nbsp;&nbsp;</label>
        <div class="col-xs-9">
            <select class="form-control department-options" id="5" name="tpt">
                <option>助教</option>
                <option>讲师</option>
                <option>副教授</option>
                <option>教授</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">修改</button>
    </div>
</form>

</html>

