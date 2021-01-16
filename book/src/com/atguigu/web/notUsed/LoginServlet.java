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
 * @create 2020-10-30-23:05
 * @description
 */
public class LoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userService.login()处理登录业务
        User loginUser = new User(null, username, password, null);
        if (userService.login(loginUser) == null) {
            System.out.println("登录失败！");
            //把错误信息，和回显的表单项信息，保存到 Request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            //跳回登路页面
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登录成功！");
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
        }

    }
}
