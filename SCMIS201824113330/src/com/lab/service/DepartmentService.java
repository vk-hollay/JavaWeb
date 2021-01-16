package com.lab.service;

import com.lab.bean.Department;
import com.lab.bean.Student;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-25-23:55
 * @description
 */
public interface DepartmentService {

    public List<Department> queryAllDepartments();

    public int addDepartment(Department department);

    public int deleteDepartment(String dno);

    public int modifyDepartment(Department department);

    public List<Department> queryByMultipleConditions(Department department);
}
