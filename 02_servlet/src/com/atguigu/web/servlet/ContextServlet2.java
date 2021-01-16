package com.atguigu.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-25-23:00
 * @description
 */

public class ContextServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 ServletContext 对象
        ServletContext servletContext = getServletContext();

        System.out.println("保存之前: Context1 获取 key1 的值是:"+ servletContext.getAttribute("key1"));
//        ServletContext 对象是一个域对象, 可以像 Map一样存取数据, 这里的域指的是存取数据的操作范围，为整个 web工程。
        /**
         * ServletContext 是在 web 工程部署启动的时候创建。在 web 工程停止的时候销毁。
         * 一个 web 工程，只有一个 ServletContext 对象实例。
         */
        servletContext.setAttribute("key1", "value1");
        System.out.println("Context1 中获取域数据 key1 的值是:"+ servletContext.getAttribute("key1"));
    }
}
