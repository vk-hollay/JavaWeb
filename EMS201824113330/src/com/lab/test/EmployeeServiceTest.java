package com.lab.test;

import com.lab.bean.Employee;
import com.lab.service.EmployeeService;
import com.lab.service.impl.EmployeeServiceImpl;
import org.junit.Test;

/**
 * @author 201824113330
 * @create 2020-12-04-18:24
 * @description
 */
public class EmployeeServiceTest {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void login() {
        Employee loginUser = employeeService.login("789456", "123");
        System.out.println(loginUser);
    }

    @Test
    public void regist() {
        employeeService.regist(new Employee("15315", "000", "薇恩", "14256932546", "general", 3));
    }

    @Test
    public void existsId() {
        if (employeeService.existsId("123456") != null) {
            System.out.println("账号已存在");
        } else {
            System.out.println("账号不存在");
        }
    }

    @Test
    public void changePassword() {
        int i = employeeService.changePassword("123456", "123");
        System.out.println(i);
    }

    @Test
    public void changeInformation() {
        int i = employeeService.changeBasicInfo(new Employee("123456", null, "李青", "14785236987", null, 4));
        System.out.println(i);
    }
}