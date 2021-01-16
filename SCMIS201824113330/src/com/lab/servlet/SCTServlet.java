package com.lab.servlet;

import com.lab.bean.Department;
import com.lab.bean.SCT;
import com.lab.service.SCTService;
import com.lab.service.impl.SCTServiceImpl;
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
@WebServlet(urlPatterns = "/manager/sctServlet")
public class SCTServlet extends BaseServlet {

    private SCTService sctService = new SCTServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SCT> scts = sctService.queryAllSelectedCourses();
        req.setAttribute("scts", scts);
        req.getRequestDispatcher("/pages/main/sct.jsp").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tccno = req.getParameter("tccno");
        String sno = req.getParameter("sno");
        int result = sctService.deleteSelectedCourse(sno, tccno);
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/sctServlet?action=showAll");
    }

    protected void modifyGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tcco = req.getParameter("tccno");
        String sno = req.getParameter("sno");
        String grade = req.getParameter("grade");
        int result = sctService.modifyGrade(tcco, sno, grade);
        if (result > 0) {
            req.getSession().setAttribute("msg", "成绩已成功录入！");
        } else {
            req.getSession().setAttribute("msg", "成绩录入失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/manager/sctServlet?action=showAll");
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SCT sct = WebUtils.copyParamToBean(req.getParameterMap(), new SCT());
        List<SCT> scts = sctService.queryByMultipleConditions(sct);
        req.setAttribute("scts", scts);
        req.getRequestDispatcher("/pages/main/sct.jsp").forward(req, resp);
    }
}
