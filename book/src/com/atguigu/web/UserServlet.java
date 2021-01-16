package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Hollay
 * @create 2020-11-10-22:07
 * @description
 */
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 处理登录功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String remember = req.getParameter("remember");
        System.out.println(username + "  " + remember);


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
            // 保存用户登录的信息到 Session 域中
            req.getSession().setAttribute("user", userService.login(loginUser));
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注销功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        /* // 测试 req.getParameterMap() 方法
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet() ) {
            System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));
        }*/

        // 一次性把所有的请求参数 注入 到 JavaBean 中
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
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
                User registUser = new User(null, username, password, email);
                //用户名可用，则调用 userService.registUser() 进行注册， 注册信息保存到数据库
                userService.registUser(registUser);
                // 保存用户注册的信息到 Session 域中，注册完自动登录
                req.getSession().setAttribute("user", registUser);
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

    /**
     * 用 ajax请求 判断用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 username
        String username = req.getParameter("username");
        // 调用 userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为 map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }
}
