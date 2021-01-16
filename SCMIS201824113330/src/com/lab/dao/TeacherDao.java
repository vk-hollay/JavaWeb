package com.lab.dao;

import com.lab.bean.Student;
import com.lab.bean.Teacher;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-22-16:55
 * @description
 */
public interface TeacherDao {

    public List<Teacher> queryAllTeachers();

    public int addTeacher(Teacher teacher);

    public int deleteTeacher(String tno);

    public int modifyTeacher(Teacher teacher);

    public List<String> getCourseByTno(String tno);

    public List<Teacher> queryByMultipleConditions(Teacher teacher);
}
