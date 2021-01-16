package com.lab.servlet;

import com.lab.bean.Department;
import com.lab.service.DepartmentService;
import com.lab.service.impl.DepartmentServiceImpl;
import com.lab.service.impl.EmployeeServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-05-16:06
 * @description
 */
@WebServlet(name = "DepartmentServlet", urlPatterns = "/departmentServlet")
public class DepartmentServlet extends BaseServlet {

    DepartmentService departmentService = new DepartmentServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.queryAll();
        HttpSession session = req.getSession();
        session.setAttribute("departments", departments);
        //resp.sendRedirect(req.getContextPath() + "/pages/client/department.jsp");
        //req.setAttribute("departments", departments);
        req.getRequestDispatcher("pages/client/department.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = WebUtils.copyParamToBean(req.getParameterMap(), new Department());
        if (departmentService.queryById(department.getId()) != null) {
            req.getSession().setAttribute("msg", "添加失败，部门代号已存在！");
        } else if (department.getManager() != null && new EmployeeServiceImpl().existsId(department.getManager()) == null) {
            req.getSession().setAttribute("msg", "添加失败，部门管理员不存在！");
        } else {
            departmentService.add(department);
            req.getSession().setAttribute("msg", "添加成功！");
        }
        resp.sendRedirect(req.getContextPath() + "/departmentServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int result = departmentService.delete(id);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/departmentServlet?action=showAll");
    }

    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = WebUtils.copyParamToBean(req.getParameterMap(), new Department());
        String oldId = req.getParameter("oldId");
        if (!oldId.equals(department.getId()) && departmentService.queryById(department.getId()) != null) {
            req.getSession().setAttribute("msg", "修改失败，部门代号已存在！");
        } else if (department.getManager() != null && new EmployeeServiceImpl().existsId(department.getManager()) == null) {
            req.getSession().setAttribute("msg", "修改失败，部门管理员不存在！");
        } else {
            departmentService.update(department, oldId);
            req.getSession().setAttribute("msg", "修改成功！");
        }
        resp.sendRedirect(req.getContextPath() + "/departmentServlet?action=showAll");
    }

    protected void queryByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String condition = req.getParameter("condition");
        String parameter = req.getParameter("parameter");
        //System.out.println(condition + "  " + parameter);
        List<Department> departments = new ArrayList<>();
        if (condition.equals("all")) {
            showAll(req, resp);
            return;
        } else if (condition.equals("id")) {
            Department department = departmentService.queryById(parameter);
            if (department != null) {
                departments.add(department);
            }
        } else {
            departments = departmentService.queryByCondition(condition, parameter);
        }
        req.getSession().setAttribute("departments", departments);
        resp.sendRedirect(req.getContextPath() + "/pages/client/department.jsp");
    }

}

