package com.atguigu.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Hollay
 * @create 2020-10-28-18:30
 * @description
 */
public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println(resp.getCharacterEncoding());//默认为ISO-8859-1，显示不了中文字符

//解决响应的中文乱码问题：
/*  //方案一：
        //设置服务器字符集为UTF-8
        resp.setCharacterEncoding("UTF-8");
        //通过响应头，设置浏览器也使用UTF-8字符集
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
 */
    //方案二：
        //setContentType()方法同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
        //此方法一定要在获取流对象之前使用才有效
        resp.setContentType("text/html;charset=UTF-8");

        //往客户端回传字符串数据
        PrintWriter writer = resp.getWriter();
        writer.write("哈哈哈response's content!!!");

    }
}
