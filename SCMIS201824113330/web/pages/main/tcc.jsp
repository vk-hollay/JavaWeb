<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院系信息</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            getCourseOptions();
            getTeacherOptions();
            showMsg("${sessionScope.msg}");
            $("#tcc").addClass("active");
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
        <h1 align="center">开课信息表</h1><br/>
        <div class="row my-search-add" style="width: 930px">
            <div class="col-xs-11">
                <div class="row">
                    <div class="input-group">
                        <span class="col-xs-3">开课号</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 70px;height: 28px" id="byno">
                        </div>
                        <span class="col-xs-3">课程名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 120px;height: 28px" id="bycname">
                        </div>
                        <span class="col-xs-3">教师名</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 80px;height: 28px" id="bytname">
                        </div>
                        <span class="col-xs-3">教室</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 70px;height: 28px" id="byroom">
                        </div>
                        <span class="col-xs-3">时间</span>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" style="width: 90px;height: 28px" id="bytime">
                        </div>
                        <div class="col-xs-3">
                            <button class="btn btn-primary" onclick="MySearch('tcc');">查询</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-1">
                <a class="btn btn-success" href="javascript:void(0)" role="button" onclick="PopUpsForm($('#addTcc'),'添加开课班','430px','475px')">添加</a>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped table-bordered my-table-style" width="760px">
                <thead>
                <tr>
                    <th class="my-th">开课编号</th>
                    <th class="my-th">课程名称</th>
                    <th class="my-th">授课教师</th>
                    <th class="my-th">已选人数</th>
                    <th class="my-th">限选人数</th>
                    <th class="my-th">上课教室</th>
                    <th class="my-th">上课时间</th>
                    <th class="my-th">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.tccs}" var="tcc">
                    <tr>
                        <td>${tcc.tccno}</td>
                        <td>${tcc.cname}</td>
                        <td>${tcc.tname}</td>
                        <td>${tcc.selected}</td>
                        <td>${tcc.limited}</td>
                        <td>${tcc.classroom}</td>
                        <td>${tcc.classtime}</td>
                        <td width="120px">
                            <c:if test="${sessionScope.loginUser.userType=='学生'}">
                                <c:if test="${tcc.flag}">
                                    <div class="aa">
                                        <a href="javascript:void(0)" onclick="selectCourse('${tcc.tccno}','${sessionScope.loginUser.id}');" id="${tcc.tccno}">取消选课</a>
                                    </div>
                                </c:if>
                                <c:if test="${not tcc.flag}">
                                    <div class="aa">
                                         <a href="javascript:void(0)" onclick="selectCourse('${tcc.tccno}','${sessionScope.loginUser.id}');" id="${tcc.tccno}">选课</a>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.loginUser.userType=='管理员'}">
                                <div class="aa">
                                    <a href="javascript:void(0)" onclick='PopUpsForm($("#modifyTcc"),"修改开课信息","430px","475px","1",
                                            "${tcc.tccno}","${tcc.cno}","${tcc.tno}","${tcc.selected}",null,"${tcc.limited}",
                                            "${tcc.classroom}","${tcc.classtime}")'>修改</a>
                                    <a href="tccServlet?action=delete&tccno=${tcc.tccno}">删除</a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

<%--添加开课班 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="tccServlet?action=add" method="post" id="addTcc">
    <div class="form-group">
        <label for="tccno" class="col-xs-3 control-label Pop-up-form">开课编号</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="tccno" placeholder="请输入开课编号" name="tccno" required>
        </div>
    </div>
    <div class="form-group">
        <label for="cname" class="col-xs-3 control-label Pop-up-form">课程名称</label>
        <div class="col-xs-9">
            <select class="form-control course-options" id="cname" name="cno">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="tname" class="col-xs-3 control-label Pop-up-form">授课教师</label>
        <div class="col-xs-9">
            <select class="form-control teacher-options" name="tno" id="tname">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="selected" class="col-xs-3 control-label Pop-up-form">已选人数</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="selected" placeholder="请输入已选人数" name="selected" required>
        </div>
    </div>
    <div class="form-group">
        <label for="limited" class="col-xs-3 control-label Pop-up-form">限选人数</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="limited" placeholder="请输入限选人数" name="limited" required>
        </div>
    </div>
    <div class="form-group">
        <label for="classroom" class="col-xs-3 control-label Pop-up-form">上课教室</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="classroom" placeholder="请输入上课教室地址" name="classroom" required>
        </div>
    </div>
    <div class="form-group">
        <label for="classtime" class="col-xs-3 control-label Pop-up-form">上课时间</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="classtime" placeholder="请输入上课时间" name="classtime" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">添加</button>
    </div>
</form>

<%--修改学院 弹窗--%>
<form class="form-horizontal mystyle form-data hidden" action="tccServlet?action=modify" method="post" id="modifyTcc">
    <div class="form-group">
        <label for="0" class="col-xs-3 control-label Pop-up-form">开课编号</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="0" name="tccno" readonly>
        </div>
    </div>
    <div class="form-group">
        <label for="1" class="col-xs-3 control-label Pop-up-form">课程名称</label>
        <div class="col-xs-9">
            <select class="form-control course-options" id="1" name="cno">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="2" class="col-xs-3 control-label Pop-up-form">授课教师</label>
        <div class="col-xs-9">
            <select class="form-control teacher-options" id="2" name="tno">
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="3" class="col-xs-3 control-label Pop-up-form">已选人数</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="3" placeholder="请输入已选人数" name="selected" required>
        </div>
    </div>
    <div class="form-group">
        <label for="5" class="col-xs-3 control-label Pop-up-form">限选人数</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="5" placeholder="请输入限选人数" name="limited" required>
        </div>
    </div>
    <div class="form-group">
        <label for="6" class="col-xs-3 control-label Pop-up-form">上课教室</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="6" placeholder="请输入上课教室地址" name="classroom" required>
        </div>
    </div>
    <div class="form-group">
        <label for="7" class="col-xs-3 control-label Pop-up-form">上课时间</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="7" placeholder="请输入上课时间" name="classtime" required>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-block btn-primary sumbit-btn">修改</button>
    </div>
</form>
</html>

