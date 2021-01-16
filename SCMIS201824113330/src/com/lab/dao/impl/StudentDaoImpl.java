package com.lab.dao.impl;

import com.lab.bean.Student;
import com.lab.dao.StudentDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-22-17:28
 * @description
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public List<Student> queryAllStudents() {
        String sql = "select sno,sname,ssex,sbirthday,sdno,dname as sdname from student,department where student.sdno=department.dno order by sno";
        return queryForList(Student.class, sql);
    }

    @Override
    public int addStudent(Student student) {
        String sql = "insert into student(sno,sname,ssex,sbirthday,sdno) values(?,?,?,?,?)";
        return update(sql, student.getSno(), student.getSname(), student.getSsex(), student.getSbirthday(), student.getSdno());
    }

    @Override
    public int deleteStudent(String sno) {
        String sql = "delete from student where sno=?";
        return update(sql, sno);
    }

    @Override
    public int modifyStudent(Student student, String oldSno) {
        String sql = "update student set sno=?,sname=?,ssex=?,sbirthday=?,sdno=? where sno=?";
        return update(sql, student.getSno(), student.getSname(), student.getSsex(), student.getSbirthday(), student.getSdno(), oldSno);
    }

    @Override
    public List<Student> queryByMultipleConditions(Student student) {
        String sql = "select sno,sname,ssex,sbirthday,sdno,dname as sdname from student,department where student.sdno=department.dno and " +
                "(sno like ? or ? is null) and (sname like ? or ? is null) and (ssex=? or ? is null) and (sdno=? or ? is null)";
        return queryForList(Student.class, sql, "%"+student.getSno()+"%", student.getSno(), "%"+student.getSname()+"%", student.getSname(),
                student.getSsex(), student.getSsex(), student.getSdno(), student.getSdno());
    }
}
