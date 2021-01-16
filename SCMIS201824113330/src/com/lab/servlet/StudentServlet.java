package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Student;
import com.lab.service.StudentService;
import com.lab.service.impl.StudentServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-23-21:14
 * @description
 */
@WebServlet(urlPatterns = "/manager/studentServlet")
public class StudentServlet extends BaseServlet {

    private StudentService studentService = new StudentServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.queryAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher( "/pages/main/student.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = WebUtils.copyParamToBean(req.getParameterMap(), new Student());
        int result = studentService.addStudent(student);
        System.out.println(result);
        if (result > 0) {
            req.getSession().setAttribute("msg", "添加成功！");
        } else {
            req.getSession().setAttribute("msg", "添加失败！该学生已存在！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/studentServlet?action=showAll");
    }

    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = WebUtils.copyParamToBean(req.getParameterMap(), new Student());
        String oldSno = req.getParameter("oldSNo");
        int result = studentService.modifyStudent(student, oldSno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/studentServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        int result = studentService.deleteStudent(sno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/studentServlet?action=showAll");
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = WebUtils.copyParamToBean(req.getParameterMap(), new Student());
        List<Student> students = studentService.queryByMultipleConditions(student);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/pages/main/student.jsp").forward(req, resp);
    }
}
