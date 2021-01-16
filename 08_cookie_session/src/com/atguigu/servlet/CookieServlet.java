package com.atguigu.servlet;

import com.atguigu.servlet.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-11-23-23:00
 * @description
 */
public class CookieServlet extends BaseServlet {
    
    protected void creatCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 创建 Cookie 对象
        Cookie cookie = new Cookie("key1", "value1");
        //2 通知客户端保存 Cookie
        resp.addCookie(cookie);

        resp.getWriter().write("Cookie创建成功！");
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            // getName 方法返回 Cookie 的 key（名）
            // getValue 方法返回 Cookie 的 value 值
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "] <br/>");
        }

        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);
/*         for (Cookie cookie : cookies) {
              if ("key2".equals(cookie.getName())) {
                 iWantCookie = cookie;
                 break;
             }
         }
 */
        // 如果不等于 null，说明赋过值，也就是找到了需要的 Cookie
        if (iWantCookie != null) {
            resp.getWriter().write("找到了需要的 Cookie");
        }
    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
        // 方案一：
        // 1、先创建一个要修改的同名的 Cookie 对象
        // 2、在构造器，同时赋于新的 Cookie 值。
        Cookie cookie = new Cookie("key1","newValue1");
        // 3、调用 response.addCookie( Cookie ); 通知 客户端 保存修改
        resp.addCookie(cookie);
*/
        // 方案二：
        // 1、先查找到需要修改的 Cookie 对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
        // 2、调用 setValue()方法赋于新的 Cookie 值。
            cookie.setValue("newValue2");
        // 3、调用 response.addCookie()通知客户端保存修改
            resp.addCookie(cookie);
            resp.getWriter().write("Cookie 修改成功！");
        }
    }

    /**
     * 设置存活 1 个小时的 Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(60 * 60); // 设置 Cookie 一小时之后被删除。无效
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个存活一小时的 Cookie");
    }
    /**
     * 马上删除一个 Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先找到你要删除的 Cookie 对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            // 调用 setMaxAge(0);
            cookie.setMaxAge(0); // 表示马上删除，都不需要等待浏览器关闭
            // 调用 response.addCookie(cookie);
            resp.addCookie(cookie);
            resp.getWriter().write("key1 的 Cookie 已经被删除");
        }
    }
    /**
     * 默认的会话级别的 Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defalutLife","defaultLife");
        cookie.setMaxAge(-1);//设置存活时间
        resp.addCookie(cookie);
    }

    /*
       Cookie 的 path 属性可以有效的过滤哪些 Cookie 可以发送给服务器。哪些不发。
       path 属性是通过请求的地址来进行有效的过滤。
            CookieA path=/工程路径
            CookieB path=/工程路径/abc
             请求地址如下：
                http://ip:port/工程路径/a.html
                    CookieA 发送
                    CookieB 不发送
                http://ip:port/工程路径/abc/a.html
                    CookieA 发送
                    CookieB 发送
     */
    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1", "path1");
        // getContextPath() ===>>>> 得到工程路径
        cookie.setPath( req.getContextPath() + "/abc" ); // ===>>>> /工程路径/abc
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有 Path 路径的 Cookie");
    }
}
