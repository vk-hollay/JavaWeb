package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Department;
import com.lab.service.DepartmentService;
import com.lab.service.impl.DepartmentServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 201824113330
 * @create 2020-12-26-0:09
 * @description
 */
@WebServlet(urlPatterns = "/manager/departmentServlet")
public class DepartmentServlet extends BaseServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.queryAllDepartments();
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/pages/main/department.jsp").forward(req, resp);
    }

    protected void getDepartmentsOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.queryAllDepartments();
        resp.getWriter().write(new Gson().toJson(departments));
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = WebUtils.copyParamToBean(req.getParameterMap(), new Department());
        int result = departmentService.addDepartment(department);
        if (result > 0) {
            req.getSession().setAttribute("msg", "添加成功！");
        } else {
            req.getSession().setAttribute("msg", "添加失败！该学院编号已存在！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/departmentServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dno = req.getParameter("dno");
        int result = departmentService.deleteDepartment(dno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/departmentServlet?action=showAll");
    }

    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = WebUtils.copyParamToBean(req.getParameterMap(), new Department());
        int result = departmentService.modifyDepartment(department);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/departmentServlet?action=showAll");
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = WebUtils.copyParamToBean(req.getParameterMap(), new Department());
        List<Department> departments = departmentService.queryByMultipleConditions(department);
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/pages/main/department.jsp").forward(req, resp);
    }
}
