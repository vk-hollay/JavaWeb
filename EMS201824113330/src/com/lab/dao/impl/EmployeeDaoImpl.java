package com.lab.dao.impl;

import com.lab.bean.Employee;
import com.lab.dao.EmployeeDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-04-16:36
 * @description
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    @Override
    public Employee queryByIdAndPassword(String id, String password) {
        String sql = "select * from employee where id=? and password=?";
        return queryForOne(Employee.class, sql, id, password);
    }

    @Override
    public Employee queryById(String id) {
        String sql = "select * from employee where id=?";
        return queryForOne(Employee.class, sql, id);
    }

    @Override
    public int saveEmployee(Employee employee) {
        String sql = "insert into employee(`id`,`password`,`name`,`phone`,`usertype`,`department`) values(?,?,?,?,?,?)";
        return update(sql, employee.getId(), employee.getPassword(), employee.getName(), employee.getPhone(), employee.getUserType(), employee.getDepartment());
    }

    @Override
    public List<Employee> queryAllEmployees() {
        String sql = "select * from employee order by id";
        return queryForList(Employee.class, sql);
    }

    @Override
    public int updatePasswordById(String id, String newPassword) {
       String sql = "update employee set password=? where id=?";
       return update(sql, newPassword, id);
    }

    @Override
    public int updateBasicInfoById(Employee employee) {
        String sql = "update employee set name=?, phone=?, department=? where id=?";
        return update(sql, employee.getName(), employee.getPhone(), employee.getDepartment(), employee.getId());
    }

    public int updateAllInfoById(Employee employee, String oldId) {
        String sql = "update employee set id=?, name=?, phone=?, password=?, userType=?, department=? where id=?";
        return update(sql, employee.getId(), employee.getName(), employee.getPhone(), employee.getPassword(), employee.getUserType(), employee.getDepartment(), oldId);
    }

    @Override
    public int deleteById(String id) {
        String sql = "delete from employee where id=?";
        return update(sql, id);
    }

    @Override
    public List<Employee> queryByName(String name) {
        String sql = "select *  from employee where name=?";
        return queryForList(Employee.class, sql, name);
    }

    @Override
    public List<Employee> queryByphone(String phone) {
        String sql = "select *  from employee where phone=?";
        return queryForList(Employee.class, sql, phone);
    }

    @Override
    public List<Employee> queryByUserType(String userType) {
        String sql = "select *  from employee where userType=?";
        return queryForList(Employee.class, sql, userType);
    }

    @Override
    public List<Employee> queryByDepartment(String department) {
        String sql = "select *  from employee where department=?";
        return queryForList(Employee.class, sql, department);
    }
}
