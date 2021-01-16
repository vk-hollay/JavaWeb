package com.lab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 201824113330
 * @create 2020-12-14-21:58
 * @description
 */
@WebFilter(filterName = "NotLoggedInFilter",
        urlPatterns = {"/pages/client/*", "/pages/common/*",
                "/pages/user/myInfo.jsp", "/departmentServlet",
                "/employeeServlet", "/equipmentServlet"})
public class NotLoggedInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Object loginUser = request.getSession().getAttribute("loginUser");
        //System.out.println("filter过滤器 ---> " + loginUser);
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}