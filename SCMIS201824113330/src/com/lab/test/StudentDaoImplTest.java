package com.lab.test;

import com.lab.bean.Student;
import com.lab.dao.StudentDao;
import com.lab.dao.impl.StudentDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-22-17:42
 * @description
 */
public class StudentDaoImplTest {

    StudentDao studentDao = new StudentDaoImpl();
    @Test
    public void queryAllStudents() {
        List<Student> students = studentDao.queryAllStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    @Test
    public void addStudent() {
    }

    @Test
    public void deleteStudent() {
    }

    @Test
    public void modifyStudent() {
    }


    @Test
    public void queryByMultipleConditions() {
        List<Student> students = studentDao.queryByMultipleConditions(new Student(null, null, "女", null, null, null));
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}