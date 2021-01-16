package com.atguigu.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-25-18:05
 * @description
 */
public class ContextServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 ServletContext 对象
        ServletContext servletContext = getServletConfig().getServletContext();

//  1、获取 web.xml 中配置的上下文参数 context-param
        String username = servletContext.getInitParameter("username");
        System.out.println("context-param 参数 username 的值是:" + username);
        System.out.println("context-param 参数 password 的值是:" + servletContext.getInitParameter("password"));
// 2、获取当前的工程路径，格式: /工程路径
        System.out.println("当前工程路径：" + servletContext.getContextPath());
// 3、获取工程部署后在服务器硬盘上的绝对路径
/**
 *      斜杠被服务器解析地址为:http://ip:port/工程名/  映射到 IDEA代码的 web目录<br/>
 */
        System.out.println("工程部署的路径是：" + servletContext.getRealPath("/"));
    }
}
