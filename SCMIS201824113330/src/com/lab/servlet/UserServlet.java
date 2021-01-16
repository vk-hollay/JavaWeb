package com.lab.servlet;

import com.lab.bean.User;
import com.lab.service.UserService;
import com.lab.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 201824113330
 * @create 2020-12-23-19:19
 * @description
 */
@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        User loginUser = userService.login(id, password);
        if (loginUser != null) {
            System.out.println("登录成功");
            req.getSession().setAttribute("loginUser",loginUser);
            resp.sendRedirect(req.getContextPath() + "/pages/main/index.jsp");
        } else {
            System.out.println("登录失败");
            req.getSession().setAttribute("msg","账号或密码错误");
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void modifyPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        int result = userService.modifyPassword(id, pwd);
        if (result > 0) {
            req.getSession().setAttribute("msg", "密码修改成功！");
        } else {
            req.getSession().setAttribute("msg", "密码修改失败！不存在此账号！");
        }
        resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
    }
}
