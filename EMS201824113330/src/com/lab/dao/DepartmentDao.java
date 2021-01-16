package com.lab.dao;

import com.lab.bean.Department;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-05-14:15
 * @description
 */
public interface DepartmentDao {

    /**
     * 查询所有部门
     * @return
     */
    public List<Department> queryAllDepartment();

    /**
     * 添加部门
     * @param department
     * @return 返回-1说明添加失败
     */
    public int addDepartment(Department department);

    /**
     * 更新部门信息
     * @param department
     * @return 返回-1说明信息更新失败
     */
    public int updateDepartment(Department department, String id);

    /**
     * 删除指定部门
     * @return 返回-1说明删除失败（注意该表存在外键约束，可能导致删除不了的情况）
     */
    public int deleteDepartment(String id);

    /**
     * 根据部门id查询
     * @param id
     * @return
     */
    public Department queryById(String id);

    public List<Department> queryByName(String name);

    public List<Department> queryByManager(String manager);
}
