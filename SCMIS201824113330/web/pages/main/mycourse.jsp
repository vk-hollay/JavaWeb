<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院系信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            showMsg("${sessionScope.msg}");
            $("#mysct").addClass("active");
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
        <h1 align="center">我的选课信息表</h1><br/>
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
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.myscts}" var="sct">
                    <tr>
                        <td>${sct.sno}</td>
                        <td>${sct.sname}</td>
                        <td>${sct.cno}</td>
                        <td>${sct.cname}</td>
                        <td>${sct.tname}</td>
                        <td>${sct.grade}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

