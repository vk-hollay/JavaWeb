package com.atguigu.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-28-20:48
 * @description
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("曾到此一游 Response1");
//请求重定向，
/*  //第一种方案：
        //设置响应状态码302，表示重定向。
        resp.setStatus(302);
        //设置请求头，说明新的地址在哪
        resp.setHeader("Location", "http://localhost:8080/03_servlet/response2");
*/
    //第二种方案：
        resp.sendRedirect("https://www.baidu.com/");
    }
}
