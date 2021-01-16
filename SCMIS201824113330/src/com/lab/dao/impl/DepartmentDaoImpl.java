package com.lab.dao.impl;

import com.lab.bean.Department;
import com.lab.dao.DepartmentDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-25-23:49
 * @description
 */
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
    @Override
    public List<Department> queryAllDepartments() {
        String sql = "select dno,dname,dmanager from department";
        return queryForList(Department.class, sql);
    }

    @Override
    public int addDepartment(Department department) {
        String sql = "insert into department(`dno`,`dname`,`dmanager`) values(?,?,?)";
        return update(sql, department.getDno(), department.getDname(), department.getDmanager());
    }

    @Override
    public int deleteDepartment(String dno) {
        String sql = "delete from department where dno=?";
        return update(sql, dno);
    }

    @Override
    public int modifyDepartment(Department department) {
        String sql = "update department set dname=?, dmanager=? where dno=?";
        return update(sql, department.getDname(), department.getDmanager(), department.getDno());
    }

    @Override
    public List<Department> queryByMultipleConditions(Department department) {
        String sql = "select dno,dname,dmanager from department where (dno like ? or ? is null) " +
                "and (dname like ? or ? is null) and (dmanager like ? or ? is null)";
        return queryForList(Department.class, sql, "%"+department.getDno()+"%", department.getDno(), "%"+department.getDname()+"%",
                department.getDname(), "%"+department.getDmanager()+"%", department.getDmanager());
    }
}
