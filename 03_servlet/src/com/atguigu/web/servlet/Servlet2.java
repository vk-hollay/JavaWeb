package com.atguigu.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-27-23:03
 * @description
 */
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        System.out.println("在Servlet2查看参数：" + username);

        //查看Servlet1是否有传值过来（他们共享Request域中的数据）
        Object key1 = req.getAttribute("key");
        System.out.println(key1);

        //处理Servlet2自己的业务
        System.out.println("servlet2处理自己的业务");
    }
}
