package com.lab.test;

import com.lab.bean.Department;
import com.lab.service.DepartmentService;
import com.lab.service.impl.DepartmentServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-26-0:07
 * @description
 */
public class DepartmentServiceTest {

    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Test
    public void queryAllDepartments() {
        List<Department> departments = departmentService.queryAllDepartments();
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    @Test
    public void addDepartment() {
    }

    @Test
    public void deleteDepartment() {
    }

    @Test
    public void modifyDepartment() {
    }
}