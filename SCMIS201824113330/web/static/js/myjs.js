// 显示修改密码弹窗
function alterPwd() {
    var position = $("#modifyPwd");
    position.removeClass("hidden");
    layer.open({
        type: 1,
        area: ['310px', '340px'], //宽高
        title: "修改密码",
        content : position,
        cancel: function () {
            position.addClass("hidden");
        }
    })
}

// 显示提示信息, 参数：提示内容
function showMsg(msg) {
    // console.log(typeof (msg));
    if (msg !== "" && msg != null) {
        layer.msg(msg, {time : 1500});
    }
}

// 表单弹窗 （用于弹出添加、修改表单） Positioning：选择器 | title：标题 | status：1为修改，0为添加 | data：需要传递到弹窗的数据
function PopUpsForm(Positioning, title, width, high, status, ...data) {
    Positioning.removeClass("hidden");
    layer.open({
        type: 1, //type=2是打开页面地址，type=1是打开html标签
        title: title,
        resize: false,
        area: [width, high], // 宽 高
        // content: ['pages/main/addStudentForm.jsp', 'yes'], //iframe的url，no代表不显示滚动条
        content: Positioning,
        success: function () {
            if (status === "1") {
                //用于修改信息的表单回显
                 console.log(data);
                $("#oldNo").val(data[0]);
                $("#0").val(data[0]);
                $("#1").val(data[1]);
                $("#2").val(data[2]);
                $("#3").val(data[3]);
                $("#5").val(data[5]);
                $("#6").val(data[6]);
                $("#7").val(data[7]);
                // 根据 value值设置 radio的默认选中值
                $("input:radio[value=" + data[4] + "]").prop('checked', true);
            } else {
                // 根据 value值设置 radio的默认选中值
                $("input:radio[value=男]").prop('checked', true);
            }
        },
        cancel: function () {
            Positioning.addClass("hidden");
        }
    })
}

// 获取所属学院选项
function getDepartmentOptions() {
    $.getJSON("manager/departmentServlet", "action=getDepartmentsOptions", function (data) {
        // console.log(typeof data);
        // console.log(data);
        for (var i in data) {
            // console.log(data[i]["dname"]);
            $(".department-options").append("<option value=" + data[i]["dno"] + ">" + data[i]["dname"] + "</option>")
        }
    })
}

// 获取课程选项
function getCourseOptions() {
    $.getJSON("manager/courseServlet", "action=getCourseOptions", function (data) {
        // console.log(data);
        for (var i in data) {
            $(".course-options").append("<option value=" + data[i]["cno"] + ">" + data[i]["cno"] + "--" + data[i]["cname"] + "</option>")
        }
    })
}

//获取教师选项
function  getTeacherOptions() {
    $.getJSON("manager/teacherServlet", "action=getTeacherOptions", function (data) {
        // console.log(data);
        for (var i in data) {
            $(".teacher-options").append("<option value=" + data[i]["tno"] + ">" + data[i]["tno"] + "--" + data[i]["tname"] + "</option>")
        }
    })
}

// 处理学生端选课和取消选课功能
function selectCourse(tccno, sno) {
    var position = $("#" + tccno);
    var val = position.text();
    var k;
    if (val === "选课") {
        k = 1;
    } else {
        k = 0;
    }
    location.href = "tccServlet?action=selectCourse&tccno=" + tccno + "&sno=" + sno + "&k=" + k;
}

// 处理录入成绩功能
function inputGrade(grade, sno, tccno) {
    layer.open({
        type: 1,
        title: '请输入成绩并确认',
        resize: false,
        btn: ['确定', '取消'],
        area: ["280px","152px"],
        content: '<input type="text" class="form-control" style="margin: 10px;width: 260px" name="grade" id="grade2" value=' + grade + '>',
        yes: function (index, layero) { //确定按钮的处理函数
          location.href = "manager/sctServlet?action=modifyGrade&grade=" + $("#grade2").val() + "&sno=" + sno + "&tccno=" + tccno;
        },
        btn2: function (index, layero) { //取消按钮后的处理函数
            layer.close(index);
        }
    })
}

//处理查询功能
function MySearch(XXX) {
    if (XXX === "student") {
        location.href = "manager/studentServlet?action=query&sno=" + $("#byno").val() + "&sname="
            + $("#byname").val() + "&ssex=" + $("#bysex").val() + "&sdno=" + $("#bydepart").val();
    } else if (XXX === 'teacher') {
        location.href = "manager/teacherServlet?action=query&tno=" + $("#byno").val() + "&tname="
            + $("#byname").val() + "&tsex=" + $("#bysex").val() + "&teb=" + $("#byeb").val() + "&tpt=" + $("#bypt").val();
    } else if (XXX === 'course') {
        location.href = "manager/courseServlet?action=query&cno=" + $("#byno").val() + "&cname="
            + $("#byname").val() + "&cpname=" + $("#bypname").val() + "&ccredit=" + $("#bycredit").val();
    } else if (XXX === 'department') {
        location.href = "manager/departmentServlet?action=query&dno=" + $("#byno").val() + "&dname="
            + $("#byname").val() + "&dmanager=" + $("#bymanager").val();
    } else if (XXX === 'tcc') {
        location.href = "tccServlet?action=query&tccno=" + $("#byno").val() + "&cname="
            + $("#bycname").val() + "&tname=" + $("#bytname").val() + "&classroom=" + $("#byroom").val() + "&classtime=" + $("#bytime").val();
    } else if (XXX === 'sct') {
        location.href = "manager/sctServlet?action=query&sno=" + $("#bysno").val() + "&sname="
            + $("#bysname").val() + "&cno=" + $("#bycno").val() + "&cname=" + $("#bycname").val() + "&tname=" + $("#bytname").val();
    }
}

/*
function openSelectCourse() {
    var position = $("#operater");
    if (position.text() === "开启学生端选课") {
        $.ajax({
            type: "POST",
            url:"pages/main/tcc.jsp",//请求页面
            data: {data:"open"},
            dataType: "json",
        });
        position.text("关闭学生端选课");
        showMsg("已关闭学生端选课功能！")
    } else if (position.text() === "关闭学生端选课") {
        $.ajax({
            type: "POST",
            url:"pages/main/tcc.jsp",//请求页面
            data: {data:"close"},
            dataType: "json",
        });
        position.text("开启学生端选课");
        showMsg("已开启学生端选课功能！")
    }
}*/
