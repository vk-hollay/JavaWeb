package com.lab.service;

import com.lab.bean.Student;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-23-20:31
 * @description
 */
public interface StudentService {
    public List<Student> queryAllStudents();

    public int addStudent(Student student);

    public int deleteStudent(String sno);

    public int modifyStudent(Student student, String oldSno);

    public List<Student> queryByMultipleConditions(Student student);
}
