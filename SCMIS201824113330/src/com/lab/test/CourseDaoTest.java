package com.lab.test;

import com.lab.bean.Course;
import com.lab.dao.CourseDao;
import com.lab.dao.impl.CourseDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-26-23:35
 * @description
 */
public class CourseDaoTest {

    private CourseDao courseDao = new CourseDaoImpl();
    @Test
    public void queryAllCourses() {
        List<Course> courses = courseDao.queryAllCourses();
        for (Course course: courses) {
            System.out.println(course.toString());
        }
    }

    @Test
    public void addCourse() {
    }

    @Test
    public void deleteCourse() {
    }

    @Test
    public void modifyCourse() {
    }

    @Test
    public void queryByCno() {
    }
}