package com.lab.servlet;

import com.lab.bean.SCT;
import com.lab.bean.TCC;
import com.lab.bean.User;
import com.lab.service.SCTService;
import com.lab.service.TCCService;
import com.lab.service.impl.SCTServiceImpl;
import com.lab.service.impl.TCCServiceImpl;
import com.lab.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-17:19
 * @description
 */
@WebServlet(urlPatterns = "/tccServlet")
public class TCCServlet extends BaseServlet {

    private TCCService tccService = new TCCServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        List<TCC> tccs = tccService.queryAllTeachCourseClass(loginUser);
        req.setAttribute("tccs", tccs);
        req.getRequestDispatcher("/pages/main/tcc.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TCC tcc = WebUtils.copyParamToBean(req.getParameterMap(), new TCC());
        int result = tccService.addTeachCourseClass(tcc);
        if (result > 0) {
            req.getSession().setAttribute("msg", "添加成功！");
        } else {
            req.getSession().setAttribute("msg", "添加失败！与已有开课班冲突！");
        }
        resp.sendRedirect(req.getContextPath() + "/tccServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tccno = req.getParameter("tccno");
        int result = tccService.deleteTeachCourseClass(tccno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/tccServlet?action=showAll");

    }

    protected void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TCC tcc = WebUtils.copyParamToBean(req.getParameterMap(), new TCC());
        int result = tccService.modifyTeachCourseClass(tcc);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/tccServlet?action=showAll");
    }

    protected void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tccno = req.getParameter("tccno");
        String sno = req.getParameter("sno");
        String k = req.getParameter("k");
        TCC tcc = tccService.selectCourse(tccno, sno, k);
        if ("1".equals(k)) {
            if (tcc == null) {
                req.getSession().setAttribute("msg", "选课失败！人数已满！");
            } else {
                req.getSession().setAttribute("msg", "选课成功！");
            }
        } else {
            req.getSession().setAttribute("msg", "已成功取消该选课！");
        }
        resp.sendRedirect(req.getContextPath() + "/tccServlet?action=showAll");
    }

    protected void showMyCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        List<SCT> scts = new SCTServiceImpl().querySelectedCoursesBySno(loginUser.getId());
        req.setAttribute("myscts", scts);
        req.getRequestDispatcher("/pages/main/mycourse.jsp").forward(req, resp);
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        TCC tcc = WebUtils.copyParamToBean(req.getParameterMap(), new TCC());
        List<TCC> tccs = tccService.queryByMultipleConditions(tcc, loginUser);
        req.setAttribute("tccs", tccs);
        req.getRequestDispatcher("/pages/main/tcc.jsp").forward(req, resp);
    }
}
