package com.lab.dao;

import com.lab.bean.Employee;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-04-15:40
 * @description
 */
public interface EmployeeDao {

    /**
     * 根据账号（员工编号）和密码查询用户信息
     * @param id
     * @param password
     * @return 返回 null说明账号或密码错误
     */
    public Employee queryByIdAndPassword(String id, String password);

    /**
     * 根据账号查询用户信息
     * @param id
     * @return 返回 null说明该用户不存在
     */
    public Employee queryById(String id);

    /**
     * 保存用户信息
     * @param employee
     * @return 返回-1说明操作失败
     */
    public int saveEmployee(Employee employee);

    /**
     * 查询所有员工信息
     * @return
     */
    public List<Employee> queryAllEmployees();

    /**
     * 修改密码
     * @param id
     * @return
     */
    public int updatePasswordById(String id, String newPassword);


    /**
     * 更新员工用户 姓名、电话、所属部门的 基础信息
     * @param employee
     * @return
     */
    public int updateBasicInfoById(Employee employee);

    /**
     * 更新员工用户所有信息
     * @param employee
     * @param oldId
     * @return
     */
    public int updateAllInfoById(Employee employee, String oldId);

    /**
     * 根据工号删除员工
     * @param id
     * @return
     */
    public int deleteById(String id);

    public List<Employee> queryByName(String name);

    public List<Employee> queryByphone(String phone);

    public List<Employee> queryByUserType(String userType);

    public List<Employee> queryByDepartment(String department);


}
