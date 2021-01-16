package com.lab.filter;

import com.lab.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 201824113330
 * @create 2020-12-29-19:16
 * @description
 */
@WebFilter(filterName = "LoginUserFilter", urlPatterns = {"/manager/*"})
public class LoginUserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            if (loginUser.getUserType().equals("学生")) {
                req.getRequestDispatcher("/pages/main/index.jsp").forward(req, resp);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
