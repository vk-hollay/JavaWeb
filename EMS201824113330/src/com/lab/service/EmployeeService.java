package com.lab.service;

import com.lab.bean.Employee;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-04-17:34
 * @description
 */
public interface EmployeeService {

    /**
     * 用户登录
     * @param id
     * @param password
     * @return 返回null则用户登录失败，提示账号或密码错误
     */
    public Employee login(String id, String password);

    /**
     * 注册用户
     * @param employee
     */
    public void regist(Employee employee);

    /**
     * 检查账号是否已存在
     * @param id
     * @return true存在，false不存在
     */
    public Employee existsId(String id);

    /**
     * 显示所有用户
     * @return
     */
    public List<Employee> showAll();

    /**
     * 根据条件查找（不包括id）
     * @param condition
     * @param parameter
     * @return
     */
    public List<Employee> queryByCondition(String condition, String parameter);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @return
     */
    public int changePassword(String id, String newPassword);

    /**
     * 修改基础信息：姓名、电话、所属部门
     * @param employee
     * @return
     */
    public int changeBasicInfo(Employee employee);


    /**
     * 修改员工用户所有信息
     * @param employee
     * @param id
     * @return
     */
    public int changeAllInfo(Employee employee, String id);

    /**
     * 删除所指定 id 的员工
     * @param id
     * @return
     */
    public int deleteEmployee(String id);
}
