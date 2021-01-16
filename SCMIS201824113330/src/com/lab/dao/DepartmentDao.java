package com.lab.dao;

import com.lab.bean.Department;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-22-16:52
 * @description
 */
public interface DepartmentDao {

    public List<Department> queryAllDepartments();

    public int addDepartment(Department department);

    public int deleteDepartment(String dno);

    public int modifyDepartment(Department department);

    public List<Department> queryByMultipleConditions(Department department);
}
