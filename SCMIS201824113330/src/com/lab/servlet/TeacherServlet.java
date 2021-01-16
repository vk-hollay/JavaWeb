package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Teacher;
import com.lab.service.TeacherService;
import com.lab.service.impl.TeacherServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-16:43
 * @description
 */
@WebServlet(urlPatterns = "/manager/teacherServlet")
public class TeacherServlet extends BaseServlet {

    private TeacherService teacherService = new TeacherServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teachers = teacherService.queryAllTeachers();
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("/pages/main/teacher.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = WebUtils.copyParamToBean(req.getParameterMap(), new Teacher());
        int result = teacherService.addTeacher(teacher);
        if (result > 0) {
            req.getSession().setAttribute("msg", "添加成功！");
        } else {
            req.getSession().setAttribute("msg", "添加失败！该教工号已存在！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/teacherServlet?action=showAll");
    }

    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = WebUtils.copyParamToBean(req.getParameterMap(), new Teacher());
        int result = teacherService.modifyTeacher(teacher);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/teacherServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tno = req.getParameter("tno");
        int result = teacherService.deleteTeacher(tno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/teacherServlet?action=showAll");
    }

    protected void getTeacherOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teachers = teacherService.queryAllTeachers();
        resp.getWriter().write(new Gson().toJson(teachers));
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = WebUtils.copyParamToBean(req.getParameterMap(), new Teacher());
        List<Teacher> teachers = teacherService.queryByMultipleConditions(teacher);
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("/pages/main/teacher.jsp").forward(req, resp);
    }
}
