package com.lab.test;

import com.lab.dao.TeacherDao;
import com.lab.dao.impl.TeacherDaoImpl;
import com.lab.service.TeacherService;
import com.lab.service.impl.TeacherServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-27-21:21
 * @description
 */
public class TeacherDaoTest {

    private TeacherDao teacherDao = new TeacherDaoImpl();
    @Test
    public void queryAllTeachers() {
    }

    @Test
    public void addTeacher() {
    }

    @Test
    public void deleteTeacher() {
    }

    @Test
    public void modifyTeacher() {
    }

    @Test
    public void getCourseByTno() {
        List<String> courses = teacherDao.getCourseByTno("10341");
        for (Object s: courses) {
            System.out.println(s);
        }
    }
}