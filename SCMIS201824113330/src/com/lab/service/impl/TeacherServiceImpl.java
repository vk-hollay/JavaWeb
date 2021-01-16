package com.lab.service.impl;

import com.lab.bean.Student;
import com.lab.bean.Teacher;
import com.lab.dao.TeacherDao;
import com.lab.dao.impl.TeacherDaoImpl;
import com.lab.service.TeacherService;
import com.lab.utils.WebUtils;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-26-16:39
 * @description
 */
public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public List<Teacher> queryAllTeachers() {
        List<Teacher> teachers = teacherDao.queryAllTeachers();
        if (teachers != null) {
            for (Teacher teacher : teachers) {
                int age = WebUtils.getAge(teacher.getTbirthday());
                teacher.setTage(age);
                List<String> courses = teacherDao.getCourseByTno(teacher.getTno());
                teacher.setTcourse(courses);
            }
        }
        return teachers;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String tno) {
        return teacherDao.deleteTeacher(tno);
    }

    @Override
    public int modifyTeacher(Teacher teacher) {
        return teacherDao.modifyTeacher(teacher);
    }

    @Override
    public List<Teacher> queryByMultipleConditions(Teacher teacher) {
        List<Teacher> teachers = teacherDao.queryByMultipleConditions(teacher);
        if (teachers != null) {
            for (Teacher teacher1 : teachers) {
                int age = WebUtils.getAge(teacher1.getTbirthday());
                teacher1.setTage(age);
                List<String> courses = teacherDao.getCourseByTno(teacher1.getTno());
                teacher1.setTcourse(courses);
            }
        }
        return teachers;
    }
}
