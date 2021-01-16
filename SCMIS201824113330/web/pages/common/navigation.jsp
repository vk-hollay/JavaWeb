<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/12/23
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="pages/main/index.jsp">学生选课管理信息系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <c:if test="${sessionScope.loginUser.userType=='管理员'}">
                <li id="student"><a href="manager/studentServlet?action=showAll">学生信息 <span class="sr-only">(current)</span></a></li>
                <li id="teacher"><a href="manager/teacherServlet?action=showAll">教师信息</a></li>
                <li id="department"><a href="manager/departmentServlet?action=showAll">院系信息</a></li>
                <li id="course"><a href="manager/courseServlet?action=showAll">课程信息</a></li>
                <li id="sct"><a href="manager/sctServlet?action=showAll">学生选课信息</a></li>
                </c:if>
                <li id="tcc"><a href="tccServlet?action=showAll">开课班信息</a></li>
                <c:if test="${sessionScope.loginUser.userType=='学生'}">
                    <li id="mysct"><a href="tccServlet?action=showMyCourse">我的选课信息</a></li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">欢迎您！${sessionScope.loginUser.name}！</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="javascript:void(0);">我的信息</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="userServlet?action=logout">退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
