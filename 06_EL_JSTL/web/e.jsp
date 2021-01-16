<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/2
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    empty运算：<br/>
    empty 运算可以判断一个数据是否为空，如果为空，则输出 true,不为空输出 false。<br/>


    <%
    //        1、值为null值的时候，为空
        request.setAttribute("emptyNull", null);
    //        2、值为空串的时候，为空
        request.setAttribute("emptyStr", "");
    //        3、值是Object类型数组，长度为零的时候
        request.setAttribute("emptyArr", new Object[]{});
    //        4、list集合，元素个数为零
        List<String> list = new ArrayList<>();
    //        list.add("abc");
        request.setAttribute("emptyList", list);
    //        5、map集合，元素个数为零
        Map<String,Object> map = new HashMap<String, Object>();
    //        map.put("key1", "value1");
        request.setAttribute("emptyMap", map);
    %>
    ${ empty emptyNull } <br/>
    ${ empty emptyStr } <br/>
    ${ empty emptyArr } <br/>
    ${ empty emptyList } <br/>
    ${ empty emptyMap } <br/>

    <hr/>

    三元运算:<br/>
    ${ 12 != 12 ? "国哥帅呆":"国哥又骗人啦" }


    <hr/>

    <%
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("a.a.a", "aaaValue");
        map1.put("b+b+b", "bbbValue");
        map1.put("c-c-c", "cccValue");
        request.setAttribute("mp", map1);
    %>

    ${ mp['a.a.a'] } <br>
    ${ mp["b+b+b"] } <br>
    ${ mp['c-c-c'] } <br>
</body>
</html>
