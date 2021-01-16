package com.lab.service;

import com.lab.bean.Department;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-05-15:41
 * @description
 */
public interface DepartmentService {

    public int add(Department department);

    public int delete(String id);

    /**
     * 修改指定 id 的部门信息
     * @param department  新的部门信息
     * @param id 指定要所要修改信息的部门的 id
     * @return
     */
    public int update(Department department, String id);

    public List<Department> queryAll();

    public Department queryById(String id);

    /**
     * 根据条件查询
     * @param condition 条件 name / manager
     * @param parameter 条件参数
     * @return
     */
    public List<Department> queryByCondition(String condition, String parameter);

}
