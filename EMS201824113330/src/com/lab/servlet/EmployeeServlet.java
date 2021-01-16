package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Employee;
import com.lab.service.EmployeeService;
import com.lab.service.impl.EmployeeServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 201824113330
 * @create 2020-12-04-18:32
 * @description
 */

@WebServlet("/employeeServlet")
public class EmployeeServlet extends BaseServlet {

    EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * 处理系统管理员添加员工用户功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = WebUtils.copyParamToBean(req.getParameterMap(), new Employee());
        employeeService.regist(employee);
        req.getSession().setAttribute("msg", "添加成功!");
        resp.sendRedirect(req.getContextPath() + "/employeeServlet?action=showAll");
    }

    /**
     * 显示所有用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.showAll();
        req.getSession().setAttribute("employees", employees);
        //req.setAttribute("allEmployees", employees);
        req.getRequestDispatcher( "pages/client/employee.jsp").forward(req,resp);
    }

    /**
     * 修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String newPassword = req.getParameter("newPassword");
        int result = employeeService.changePassword(id, newPassword);
        // 把返回的结果封装成为 map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        resp.getWriter().write(new Gson().toJson(resultMap));
    }

    /**
     * 用户修改自己的个人信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void changeBasicInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = WebUtils.copyParamToBean(req.getParameterMap(), new Employee());
        int result = employeeService.changeBasicInfo(employee);
        // 把返回的结果封装成为 map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        resp.getWriter().write(new Gson().toJson(resultMap));
    }

    /**
     * 处理管理员修改员工用户信息功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void changeAllInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldId = req.getParameter("oldId");
        Employee employee = WebUtils.copyParamToBean(req.getParameterMap(), new Employee());
        int result = employeeService.changeAllInfo(employee, oldId);
        if (result > 0) {
            HttpSession session = req.getSession();
            session.setAttribute("msg", "修改成功！");
            Employee loginUser = (Employee) session.getAttribute("loginUser");
            if (loginUser.getId().equals(oldId)) {
                session.setAttribute("loginUser", employee);
            }
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/employeeServlet?action=showAll");
    }

    /**
     * 更新我的信息（即当前登录用户）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void refreshLoginUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Employee loginUser = employeeService.existsId(id);
        req.getSession().setAttribute("loginUser", loginUser);
        resp.sendRedirect(req.getContextPath() + "/pages/user/myInfo.jsp");
    }

    /**
     * 处理系统管理员删除员工用户功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int result = employeeService.deleteEmployee(id);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！该员工为部门主管！");
        }
        resp.sendRedirect(req.getContextPath() + "/employeeServlet?action=showAll");
    }

    /**
     * 按条件查找
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String condition = req.getParameter("condition");
        String parameter = req.getParameter("parameter");
        //System.out.println(condition + "  " + parameter);
        List<Employee> employees = new ArrayList<>();
        if (condition.equals("all")) {
            showAll(req, resp);
            return;
        } else if (condition.equals("id")) {
            Employee employee = employeeService.existsId(parameter);
            if (employee != null) {
                employees.add(employee);
            }
        } else {
            employees = employeeService.queryByCondition(condition, parameter);
        }
        req.getSession().setAttribute("employees", employees);
        resp.sendRedirect(req.getContextPath() + "/pages/client/employee.jsp");
    }
}
