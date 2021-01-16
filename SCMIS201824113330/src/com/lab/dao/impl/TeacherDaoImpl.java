package com.lab.dao.impl;

import com.lab.bean.Teacher;
import com.lab.dao.TeacherDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-16:27
 * @description
 */
public class TeacherDaoImpl extends BaseDao implements TeacherDao {
    @Override
    public List<Teacher> queryAllTeachers() {
        String sql = "select `tno`,`tname`,`tsex`,`tbirthday`,`teb`,`tpt` from teacher";
        return queryForList(Teacher.class, sql);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        String sql = "insert into teacher(`tno`,`tname`,`tsex`,`tbirthday`,`teb`,`tpt`) values(?,?,?,?,?,?)";
        return update(sql, teacher.getTno(), teacher.getTname(), teacher.getTsex(), teacher.getTbirthday(), teacher.getTeb(), teacher.getTpt());
    }

    @Override
    public int deleteTeacher(String tno) {
        String sql = "delete from teacher where tno=?";
        return update(sql, tno);
    }

    @Override
    public int modifyTeacher(Teacher teacher) {
        String sql = "update teacher set tname=?,tsex=?,tbirthday=?,teb=?,tpt=? where tno=?";
        return update(sql, teacher.getTname(), teacher.getTsex(), teacher.getTbirthday(), teacher.getTeb(), teacher.getTpt(), teacher.getTno());
    }

    @Override
    public List<String> getCourseByTno(String tno) {
        String sql = "SELECT c.cname FROM tcc t,course c WHERE t.cno=c.cno AND t.tno=?";
        return (List<String>)(List)queryForOneColumnList(sql, "cname", tno);
    }

    @Override
    public List<Teacher> queryByMultipleConditions(Teacher teacher) {
        String sql = "select `tno`,`tname`,`tsex`,`tbirthday`,`teb`,`tpt` from teacher where (tno like ? or ? is null) " +
                "and (tname like ? or ? is null) and (tsex=? or ? is null) and (teb=? or ? is null) and (tpt=? or ? is null)";
        return queryForList(Teacher.class, sql, "%"+teacher.getTno()+"%", teacher.getTno(), "%"+teacher.getTname()+"%", teacher.getTname(),
                teacher.getTsex(), teacher.getTsex(), teacher.getTeb(), teacher.getTeb(), teacher.getTpt(), teacher.getTpt());
    }
}
