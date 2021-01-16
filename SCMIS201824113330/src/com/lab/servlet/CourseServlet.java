package com.lab.servlet;

import com.google.gson.Gson;
import com.lab.bean.Course;
import com.lab.bean.Department;
import com.lab.service.CourseService;
import com.lab.service.impl.CourseServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-20:20
 * @description
 */
@WebServlet(urlPatterns = "/manager/courseServlet")
public class CourseServlet extends BaseServlet {

    private CourseService courseService = new CourseServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.queryAllCourses();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/pages/main/course.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = WebUtils.copyParamToBean(req.getParameterMap(), new Course());
        int result = courseService.addCourse(course);
        if (result > 0) {
            req.getSession().setAttribute("msg", "添加成功！");
        } else {
            req.getSession().setAttribute("msg", "添加失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/courseServlet?action=showAll");
    }


    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = WebUtils.copyParamToBean(req.getParameterMap(), new Course());
        int result = courseService.modifyCourse(course);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/courseServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cno = req.getParameter("cno");
        int result = courseService.deleteCourse(cno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/courseServlet?action=showAll");
    }

    protected void getCourseOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.queryAllCourses();
        resp.getWriter().write(new Gson().toJson(courses));
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ccredit为Interger类型，当前端传过来的ccredit为""时，注入到 bean中自动被赋值为0，所以下面要判断当ccredit为""时赋为null
        Course course = WebUtils.copyParamToBean(req.getParameterMap(), new Course());
        if ("".equals(req.getParameter("ccredit"))) {
            course.setCcredit(null);
        }
        List<Course> courses = courseService.queryByMultipleConditions(course);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/pages/main/course.jsp").forward(req, resp);
    }
}
