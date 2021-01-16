package com.lab.service;

import com.lab.bean.Student;
import com.lab.bean.Teacher;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-16:38
 * @description
 */
public interface TeacherService {

    public List<Teacher> queryAllTeachers();

    public int addTeacher(Teacher teacher);

    public int deleteTeacher(String tno);

    public int modifyTeacher(Teacher teacher);

    public List<Teacher> queryByMultipleConditions(Teacher teacher);
}
