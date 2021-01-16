package com.lab.service.impl;

import com.lab.bean.Department;
import com.lab.dao.DepartmentDao;
import com.lab.dao.impl.DepartmentDaoImpl;
import com.lab.service.DepartmentService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-25-23:58
 * @description
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Department> queryAllDepartments() {
        return departmentDao.queryAllDepartments();
    }

    @Override
    public int addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public int deleteDepartment(String dno) {
        return departmentDao.deleteDepartment(dno);
    }

    @Override
    public int modifyDepartment(Department department) {
        return departmentDao.modifyDepartment(department);
    }

    @Override
    public List<Department> queryByMultipleConditions(Department department) {
        return departmentDao.queryByMultipleConditions(department);
    }
}
