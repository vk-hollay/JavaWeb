package com.lab.servlet;

import com.lab.bean.Equipment;
import com.lab.service.EquipmentService;
import com.lab.service.impl.EmployeeServiceImpl;
import com.lab.service.impl.EquipmentServiceImpl;
import com.lab.utils.FileUploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-07-21:50
 * @description
 */
@WebServlet(name = "EquipmentServlet", urlPatterns = "/equipmentServlet")
public class EquipmentServlet extends BaseServlet {

    private EquipmentService equipmentService = new EquipmentServiceImpl();

    protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Equipment> allEquipments = equipmentService.showAll();
        req.getSession().setAttribute("allEquipments", allEquipments);
        //resp.sendRedirect(req.getContextPath() + "/pages/client/equipment.jsp");
        req.getRequestDispatcher( "pages/client/equipment.jsp").forward(req, resp);
    }

    protected void addEquipment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Equipment equipment = new FileUploadUtils(req).copyParamToBean(new Equipment());
        if (equipmentService.queryById(equipment.getId()) != null) {
            req.getSession().setAttribute("msg", "添加失败！设备编号已存在！");
        } else if (equipment.getManager() != null && new EmployeeServiceImpl().existsId(equipment.getManager()) == null) {
            req.getSession().setAttribute("msg", "添加失败！设备负责人不存在！");
        } else{
            equipmentService.add(equipment);
            req.getSession().setAttribute("msg", "添加成功！");
        }
        resp.sendRedirect(req.getContextPath() + "/equipmentServlet?action=showAll");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Equipment equipment = equipmentService.queryById(id);
        int result = equipmentService.delete(id);
        FileUploadUtils.deleteFile(req, equipment.getImgPath());
        if (result > 0) {
            req.getSession().setAttribute("msg", "删除成功！");
        } else {
            req.getSession().setAttribute("msg", "删除失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/equipmentServlet?action=showAll");
    }


    protected void changeEquipmentInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileUploadUtils fileUpload = new FileUploadUtils(req);
        String oldId = fileUpload.getParameterValue("oldId");
        String originalImgPath = fileUpload.getParameterValue("originalImgPath");
        Equipment equipment = fileUpload.copyParamToBean(new Equipment());
        if (!"".equals(originalImgPath) && equipment.getImgPath() == null) {
            equipment.setImgPath(originalImgPath);
        }
        int result = equipmentService.update(equipment, oldId);
        if (result > 0) {
            req.getSession().setAttribute("msg", "修改成功！");
        } else {
            req.getSession().setAttribute("msg", "修改失败！");
        }
        resp.sendRedirect(req.getContextPath() + "/equipmentServlet?action=showAll");
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
        List<Equipment> equipments = new ArrayList<>();
        if (condition.equals("all")) {
            showAll(req, resp);
            return;
        } else if (condition.equals("id")) {
            Equipment equipment = equipmentService.queryById(parameter);
            if (equipment != null) {
                equipments.add(equipment);
            }
        } else {
            equipments = equipmentService.queryByCondition(condition, parameter);
        }
        req.getSession().setAttribute("allEquipments", equipments);
        resp.sendRedirect(req.getContextPath() + "/pages/client/equipment.jsp");
    }

}
