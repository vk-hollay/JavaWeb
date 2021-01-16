package com.lab.dao.impl;

import com.lab.bean.Department;
import com.lab.dao.DepartmentDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-05-14:27
 * @description
 */
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
    @Override
    public List<Department> queryAllDepartment() {
        String sql = "select * from department";
        return queryForList(Department.class, sql);
    }

    @Override
    public int addDepartment(Department department) {
        if ("".equals(department.getManager())) {
            String sql = "insert into department(`id`,`name`) values(?,?)";
            return update(sql, department.getId(), department.getName());
        } else {
            String sql = "insert into department(`id`,`name`,`manager`) values(?,?,?)";
            return update(sql, department.getId(), department.getName(), department.getManager());
        }
    }

    @Override
    public int updateDepartment(Department department, String id) {
        String sql = "update department set id=?, name=?, manager=? where id=?";
        return update(sql, department.getId(), department.getName(), department.getManager(), id);
    }


    @Override
    public int deleteDepartment(String id) {
        String sql = "delete from department where id=?";
        return update(sql, id);
    }

    @Override
    public Department queryById(String id) {
        String sql = "select * from department where id=?";
        return queryForOne(Department.class, sql, id);
    }

    @Override
    public List<Department> queryByManager(String manager) {
        String sql = "select * from department where manager=?";
        return queryForList(Department.class, sql, manager);
    }

    @Override
    public List<Department> queryByName(String name) {
        String sql = "select * from department where name=?";
        return queryForList(Department.class, sql, name);
    }
}
