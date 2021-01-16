package com.lab.test;

import com.lab.bean.Department;
import com.lab.dao.DepartmentDao;
import com.lab.dao.impl.DepartmentDaoImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-05-14:41
 * @description
 */
public class DepartmentDaoTest {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    @Test
    public void queryAllDepartment() {
        List<Department> departments = departmentDao.queryAllDepartment();
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    @Test
    public void addDepartment() {
        int i = departmentDao.addDepartment(new Department("103", "运营部", "143625"));
        System.out.println(i);

    }

    @Test
    public void updateDepartment() {
        int i = departmentDao.updateDepartment(new Department("102", "销售部", "143625"), "107");
        System.out.println(i);
    }

    @Test
    public void deleteDepartment() {
        int i = departmentDao.deleteDepartment("107");
        System.out.println(i);
    }


}