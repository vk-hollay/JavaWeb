package com.lab.dao.impl;

import com.lab.bean.Course;
import com.lab.dao.CourseDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-20:10
 * @description
 */
public class CourseDaoImpl extends BaseDao implements CourseDao {
    @Override
    public List<Course> queryAllCourses() {
        String sql = "select a.cno,a.cname,a.cpno,b.cname as cpname,a.ccredit from course a left join course b on a.cpno=b.cno";
        return queryForList(Course.class, sql);
    }

    @Override
    public int addCourse(Course course) {
        String sql = "insert into course(`cno`,`cname`,`cpno`,`ccredit`) values(?,?,?,?)";
        return update(sql, course.getCno(), course.getCname(), course.getCpno(), course.getCcredit());
    }

    @Override
    public int deleteCourse(String cno) {
        String sql = "delete from course where cno=?";
        return update(sql, cno);
    }

    @Override
    public int modifyCourse(Course course) {
        String sql = "update course set cname=?,cpno=?,ccredit=? where cno=?";
        return update(sql, course.getCname(), course.getCpno(), course.getCcredit(), course.getCno());
    }

    @Override
    public Course queryByCno(String cno) {
        String sql = "select `cno`,`cname`,`cpno`,`ccredit` from course where cno=?";
        return queryForOne(Course.class, sql, cno);
    }

    @Override
    public List<Course> queryByMultipleConditions(Course course) {
        String sql = "select a.cno,a.cname,a.cpno,b.cname as cpname,a.ccredit from course a left join course b on a.cpno=b.cno where " +
                "(a.cno like ? or ? is null) and (a.cname like ? or ? is null) and (a.cpno like ? or ? is null) and (b.cname like ? or ? is null) and (a.ccredit=? or ? is null)";
        return queryForList(Course.class, sql, "%"+course.getCno()+"%", course.getCno(), "%"+course.getCname()+"%", course.getCname(),
                course.getCpno(), course.getCpno(), "%"+course.getCpname()+"%", course.getCpname(), course.getCcredit(), course.getCcredit());
    }
}
