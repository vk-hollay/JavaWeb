package com.lab.service.impl;

import com.lab.bean.Department;
import com.lab.bean.Employee;
import com.lab.dao.EmployeeDao;
import com.lab.dao.impl.EmployeeDaoImpl;
import com.lab.service.EmployeeService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-04-18:19
 * @description
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Employee login(String id, String password) {
        return employeeDao.queryByIdAndPassword(id,password);
    }

    @Override
    public void regist(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee existsId(String id) {
        return employeeDao.queryById(id);
    }

    @Override
    public List<Employee> showAll() {
        return employeeDao.queryAllEmployees();
    }

    @Override
    public int changePassword(String id, String newPassword) {
        return  employeeDao.updatePasswordById(id, newPassword);
    }

    @Override
    public int changeBasicInfo(Employee employee) {
        return employeeDao.updateBasicInfoById(employee);
    }

    @Override
    public int changeAllInfo(Employee employee, String oldId) {
        return employeeDao.updateAllInfoById(employee, oldId);
    }

    @Override
    public int deleteEmployee(String id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public List<Employee> queryByCondition(String condition, String parameter) {
        List<Employee> employees  = null;
        if (condition.equals("name")) {
            employees = employeeDao.queryByName(parameter);
        } else if(condition.equals("phone")) {
            employees = employeeDao.queryByphone(parameter);
        } else if (condition.equals("userType")) {
            employees = employeeDao.queryByUserType(parameter);
        } else if (condition.equals("department")) {
            employees = employeeDao.queryByDepartment(parameter);
        }
        return employees;
    }
}
