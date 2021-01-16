package com.lab.service.impl;

import com.lab.bean.Course;
import com.lab.dao.CourseDao;
import com.lab.dao.impl.CourseDaoImpl;
import com.lab.service.CourseService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-20:18
 * @description
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> queryAllCourses() {
        return courseDao.queryAllCourses();
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public int modifyCourse(Course course) {
        return courseDao.modifyCourse(course);
    }

    @Override
    public int deleteCourse(String cno) {
        return courseDao.deleteCourse(cno);
    }

    @Override
    public List<Course> queryByMultipleConditions(Course course) {
        return courseDao.queryByMultipleConditions(course);
    }
}
