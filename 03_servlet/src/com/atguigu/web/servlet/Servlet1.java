package com.atguigu.web.servlet;

import javax.servlet.RequestDispatcher;
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
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        System.out.println("在Servlet1中查看参数：" + username);

        //给请求设置一个属性，并传递到Servlet2去查看
        req.setAttribute("key", "value");

        //问路：Servlet2怎么走
        /*请求转发必须要以斜杠打头，/ 斜杠表示地址为：http://ip:port/工程名/ , 映射到IDEA代码的web目录 */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");
        //走向Servlet2
        requestDispatcher.forward(req, resp);

    }
}
