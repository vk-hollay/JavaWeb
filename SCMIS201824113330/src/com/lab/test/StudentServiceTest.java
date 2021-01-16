package com.lab.test;

import com.lab.bean.Student;
import com.lab.service.StudentService;
import com.lab.service.impl.StudentServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-23-21:56
 * @description
 */
public class StudentServiceTest {

    private StudentService studentService = new StudentServiceImpl();
    @Test
    public void queryAllStudents() {
        List<Student> students = studentService.queryAllStudents();
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
}