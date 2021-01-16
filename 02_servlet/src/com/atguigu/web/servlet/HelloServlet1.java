package com.atguigu.web.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-24-16:52
 * @description
 */
public class HelloServlet1 implements Servlet {

//    构造器方法和init初始化方法只在第一次访问的时候创建 Servlet程序时会调用。

/*    Servlet 程序和 ServletConfig 对象都是由 Tomcat 负责创建，我们负责使用。
    Servlet 程序默认是第一次访问的时候创建，ServletConfig 是每个 Servlet 程序创建时，
    就创建一个对应的 ServletConfig 对象。
*/
    public HelloServlet1() {
        System.out.println("1. 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2. init初始化方法");
//        1.可以获取 Servlet 程序的别名 servlet-name 的值
        System.out.println("HelloServlet程序的别名是：" +  servletConfig.getServletName());
//        2、获取初始化参数 init-param
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("url"));
//        3、获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service方法是专门用来处理请求和响应的，每次访问都会调用
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3. service方法 ====== Hello Servlet1 被访问了");
        // 类型转换(因为他有getMenthod()方法)
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求方式
        String method = httpServletRequest.getMethod();
        // GET 和 POST 请求的分发处理
        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
            doPost();
        }
    }

    /**
     * 做get请求的操作
     */
    public void doGet() {
        System.out.println("get请求");
    }

    /**
     * 做post请求的操作
     */
    public void doPost() {
        System.out.println("post请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() { //destroy()方法在web程停止的时候调用
        System.out.println("4. destroy销毁");
    }
}
