package com.atguigu.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-24-20:57
 * @description
 */
public class HelloServlet2 extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
//       重写init方法里面一定要调用父类的init(ServletConfig config)操作
        super.init(config);
        System.out.println("重写了init初始化方法，做了一些工作");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的doGet方法");

//       getServletConfig()获取 ServletConfig对象
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);

//        1.可以获取 Servlet 程序的别名 servlet-name 的值
        System.out.println("HelloServlet程序的别名是：" +  servletConfig.getServletName());
//        2、获取初始化参数 init-param
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("url"));
//        3、获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的doPost方法");
    }
}
