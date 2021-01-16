package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Department;
import com.lab.bean.Employee;
import com.lab.service.DepartmentService;
import com.lab.service.EmployeeService;
import com.lab.service.impl.DepartmentServiceImpl;
import com.lab.service.impl.EmployeeServiceImpl;
import com.lab.utils.CookieUtils;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 201824113330
 * @create 2020-12-15-14:23
 * @description 处理用户登录注册注销功能
 */
@WebServlet(name = "UserServlet" ,urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * 处理登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        String remember = req.getParameter("remember");
        //System.out.println("  " + remember );

        Cookie userId = null;
        Cookie userPwd = null;
        if (remember != null) {
            userId = CookieUtils.findCookie("userId", req.getCookies());
            userPwd = CookieUtils.findCookie("userPwd", req.getCookies());
            if (userId != null && userPwd != null) {
                //调用 setValue()方法赋于新的 Cookie 值。
                userId.setValue(id);
                userPwd.setValue(password);
                // 调用 response.addCookie()通知客户端保存修改
                resp.addCookie(userId);
                resp.addCookie(userPwd);
            } else if(userId == null && userPwd == null) {
                userId = new Cookie("userId", id);
                userPwd = new Cookie("userPwd", password);
                resp.addCookie(userId);
                resp.addCookie(userPwd);
            }
        } else {
            userId = CookieUtils.findCookie("userId", req.getCookies());
            userPwd= CookieUtils.findCookie("userPwd", req.getCookies());
            if (userId != null && userPwd != null) {
                userId.setMaxAge(0); // 马上删除 cookie
                userPwd.setMaxAge(0);
                // 调用 response.addCookie()通知客户端保存修改
                resp.addCookie(userId);
                resp.addCookie(userPwd);
            }
        }

        Employee loginUser = employeeService.login(id, password);

        if (loginUser == null) {
            System.out.println("登录失败");
            req.setAttribute("msg", "账号或密码错误！");
            req.setAttribute("id", id);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登录成功");
            req.getSession().setAttribute("loginUser", loginUser);
//            req.getRequestDispatcher("pages/client/employee.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/equipmentServlet?action=showAll");
        }
    }

    protected void getLoginCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie userId = CookieUtils.findCookie("userId", req.getCookies());
        Cookie userPwd = CookieUtils.findCookie("userPwd", req.getCookies());
        // 如果不等于 null，说明赋过值，也就是找到了需要的 Cookie
        if (userId != null && userPwd != null) {
            // 把返回的结果封装成为 map对象
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", userId);
            resultMap.put("pwd", userPwd);
            resp.getWriter().write(new Gson().toJson(resultMap));
        }
    }

    /**
     * 处理注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁 Session
        req.getSession().invalidate();
        // 回首页
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regists(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = WebUtils.copyParamToBean(req.getParameterMap(), new Employee());
        if (employeeService.existsId(employee.getId()) != null) {
            System.out.println("账号已存在");
        } else {
            employeeService.regist(employee);
            System.out.println("注册成功");
            // 注册成功顺带登录进入
            req.getSession().setAttribute("loginUser", employee);
            resp.sendRedirect(req.getContextPath() + "/equipmentServlet?action=showAll");
        }
    }

    /**
     * 检查指定账号是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void existsId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Employee existsId = employeeService.existsId(id);
        // 把返回的结果封装成为 map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsId", existsId);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * ajax获取所有部门选项用于部门多选框的选择
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getDepartmentOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = new DepartmentServiceImpl().queryAll();
        // 把返回的结果封装成为 map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", departments);
        resp.getWriter().write(new Gson().toJson(resultMap));
    }
}
