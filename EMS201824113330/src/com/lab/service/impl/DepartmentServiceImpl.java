package com.lab.service.impl;

import com.lab.bean.Department;
import com.lab.dao.DepartmentDao;
import com.lab.dao.impl.DepartmentDaoImpl;
import com.lab.service.DepartmentService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-05-15:56
 * @description
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public int add(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public int delete(String id) {
        return departmentDao.deleteDepartment(id);
    }

    @Override
    public int update(Department department, String id) {
        return departmentDao.updateDepartment(department, id);
    }

    @Override
    public List<Department> queryAll() {
        return departmentDao.queryAllDepartment();
    }

    @Override
    public Department queryById(String id) {
        return departmentDao.queryById(id);
    }

    @Override
    public List<Department> queryByCondition(String condition, String parameter) {
        List<Department> departments = null;
        if (condition.equals("name")) {
            departments = departmentDao.queryByName(parameter);
        } else if(condition.equals("manager")) {
            departments = departmentDao.queryByManager(parameter);
        }
        return departments;
    }
}
