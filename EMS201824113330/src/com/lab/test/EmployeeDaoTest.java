package com.lab.test;

import com.lab.bean.Employee;
import com.lab.dao.EmployeeDao;
import com.lab.dao.impl.EmployeeDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-04-16:48
 * @description
 */
public class EmployeeDaoTest {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Test
    public void queryByIdAndPassword() {
        if (employeeDao.queryByIdAndPassword("123456", "123456") == null) {
            System.out.println("账号或密码错误");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void queryById() {
        if (employeeDao.queryById("123456") == null) {
            System.out.println("账号不存在");
        } else {
            System.out.println("账号已存在");
        }
    }

    @Test
    public void saveEmployee() {
        int i = employeeDao.saveEmployee(new Employee("789456", "123", "哈哈", "123456789", "manager", 3));
        System.out.println(i);
    }

    @Test
    public void queryAllEmployees() {
        List<Employee> employees = employeeDao.queryAllEmployees();
        for (Employee employee: employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void changePassword() {
        int i = employeeDao.updatePasswordById("123456", "123456");
        System.out.println(i);
    }

    @Test
    public void changeInformation() {
        int i = employeeDao.updateAllInfoById(new Employee("123456", "222", "李白", "15489632564", null,  null), "1111");
        System.out.println(i);
    }
}