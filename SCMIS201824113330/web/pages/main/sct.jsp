<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院系信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            showMsg("${sessionScope.msg}");
            $("#sct").addClass("active");
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
        <h1 align="center">学生选课信息表</h1><br/>
        <div class="row my-search-add" style="width: 900px">
            <div class="col-xs-12">
                <div class="row">
                    <div class="input-group">
                        <span class="col-xs-3">学号</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 110px;height: 28px" id="bysno">
                        </div>
                        <span class="col-xs-3">学生姓名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 70px;height: 28px" id="bysname">
                        </div>
                        <span class="col-xs-3">课程号</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="bycno">
                        </div>
                        <span class="col-xs-3">课程名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 110px;height: 28px" id="bycname">
                        </div>
                        <span class="col-xs-3">教师名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="bytname">
                        </div>
                        <div class="col-xs-3">
                            <button class="btn btn-primary" onclick="MySearch('sct');">查询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped table-bordered my-table-style">
                <thead>
                <tr>
                    <th class="my-th">学号</th>
                    <th class="my-th">学生姓名</th>
                    <th class="my-th">课程编号</th>
                    <th class="my-th">课程名称</th>
                    <th class="my-th">授课教师</th>
                    <th class="my-th">成绩</th>
                    <th class="my-th">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.scts}" var="sct">
                    <tr>
                        <td>${sct.sno}</td>
                        <td>${sct.sname}</td>
                        <td>${sct.cno}</td>
                        <td>${sct.cname}</td>
                        <td>${sct.tname}</td>
                        <td id="grade">${sct.grade}</td>
                        <td>
                            <div class="aa">
                                <a href="javascript:void(0)" onclick='inputGrade("${sct.grade}","${sct.sno}","${sct.tccno}")'>录入成绩</a>
                                <a href="manager/sctServlet?action=delete&tccno=${sct.tccno}&sno=${sct.sno}">删除</a>
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
</html>

