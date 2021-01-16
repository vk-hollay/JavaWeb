package com.lab.dao;

import com.lab.bean.Course;
import com.lab.bean.Student;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-20:08
 * @description
 */
public interface CourseDao {

    public List<Course> queryAllCourses();

    public int addCourse(Course course);

    public int deleteCourse(String cno);

    public int modifyCourse(Course course);

    public Course queryByCno(String cno);

    public List<Course> queryByMultipleConditions(Course course);

}
