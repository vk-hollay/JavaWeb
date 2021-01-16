
// <select class="form-control" name="department" id="department_option"> </select>
// 通过ajax获取所有部门选项数据，添加到 <select> 中
function getDepartmentOptions() {
    $.getJSON("userServlet", "action=getDepartmentOptions", function (data) {
        //console.log(data);
        if (data !== null) {
            for (var i in data) {
                for (var j = 0; j < data[i].length; j++) {
                    //console.log(data[i][j]["id"]);
                    $(".department_option").append("<option value=" + data[i][j]["id"] + ">" + data[i][j]["name"] + "</option>")
                }
            }
        }
    })
}

// 根据条件查询
function queryByCondition() {
    var XXXServlet = $("#inquire").val();
    var condition = $("#select").val();
    var parameter = $("#search").val();
    location.href = XXXServlet + "?action=queryByCondition&condition=" + condition + "&parameter=" + parameter;
}

// 判断上传的文件是否合法
var fileIsLegal = null;
function checkFileSuffix(filename) {
    console.log(filename);
    var flag = false; //状态
    var arr = ["jpg", "png", "jpeg", "gif"];
    //取出上传文件的 后缀名
    var suffix = filename.substr(filename.lastIndexOf(".") + 1);
    //循环比较
    console.log(suffix);
    for (var i = 0; i < arr.length; i++) {
        if (suffix === arr[i]) {
            flag = true; //一旦找到合适的，立即退出循环
            break;
        }
    }
    //条件判断
    if (!flag) {
        $(".prompt").text("文件不合法");
        $(".prompt").css('color', 'red');
        $(".prompt").css('visibility','visible');
        fileIsLegal = false;
    } else {
        $(".prompt").text("");
        $(".prompt").css('visibility','hidden');
        fileIsLegal = true;
    }
}

// 实现登录页面 remember me功能
function getLoginCookie() {
    $.getJSON("userServlet", "action=getLoginCookie", function (data) {
        console.log(data);
        //console.log(data.id.value);
        //console.log(data.pwd.value);
        $("#id").val(data.id.value);
        $("#password").val(data.pwd.value);
    })
}

