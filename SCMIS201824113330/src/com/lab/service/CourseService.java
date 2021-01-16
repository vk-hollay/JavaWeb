package com.lab.service;

import com.lab.bean.Course;
import com.lab.bean.Student;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-20:16
 * @description
 */
public interface CourseService {

    public List<Course> queryAllCourses();

    public int addCourse(Course course);

    public int modifyCourse(Course course);

    public int deleteCourse(String cno);

    public List<Course> queryByMultipleConditions(Course course);
}
