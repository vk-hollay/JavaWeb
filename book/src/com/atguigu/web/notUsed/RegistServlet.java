package com.atguigu.web.notUsed;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-10-30-20:11
 * @description
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)) {
            //检查用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名 [" + username + "] 已存在！");
                //把回显信息保存在Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //用户名已存在，不可用，返回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //用户名可用，则调用 userService.registUser() 进行注册， 注册信息保存到数据库
                userService.registUser(new User(null, username, password, email));
                //跳转到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //把回显信息保存在Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码 [" + code + "] 错误！");
            //返回注册页面 regist.jsp
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
