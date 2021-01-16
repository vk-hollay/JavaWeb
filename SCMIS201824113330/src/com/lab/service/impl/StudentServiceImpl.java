package com.lab.service.impl;

import com.lab.bean.Student;
import com.lab.bean.User;
import com.lab.dao.StudentDao;
import com.lab.dao.UserDao;
import com.lab.dao.impl.StudentDaoImpl;
import com.lab.dao.impl.UserDaoImpl;
import com.lab.service.StudentService;
import com.lab.utils.WebUtils;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-23-20:36
 * @description
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public List<Student> queryAllStudents() {
        List<Student> students = studentDao.queryAllStudents();
        if (students != null) {
            for (Student student : students) {
                int age = WebUtils.getAge(student.getSbirthday());
                student.setSage(age);
            }
        }
        return students;
    }

    /**
     * 添加学生后自动为其注册本系统登录账号
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        int result = studentDao.addStudent(student);
        if (result > 0) {
            // 截取学号后4位作为默认密码
            String sno = student.getSno();
            String pwd = sno.substring(sno.length() - 4);
            new UserDaoImpl().addUser(new User(sno, pwd, student.getSname(), "学生"));
        }
        return result;
    }

    /**
     * 删除学生后自动删除本系统该用户
     * @param sno
     * @return
     */
    @Override
    public int deleteStudent(String sno) {
        int result = studentDao.deleteStudent(sno);
        if (result > 0) {
            new UserDaoImpl().deleteUser(sno);
        }
        return result;
    }

    @Override
    public int modifyStudent(Student student, String oldSno) {
        int result = studentDao.modifyStudent(student, oldSno);
        if (result == 0) {
            return result;
        }
        UserDao userDao = new UserDaoImpl();
        String pwd = oldSno.substring(oldSno.length() - 4);
        User user = userDao.queryByIdAndPassword(oldSno, pwd);
        if (user == null) {  // 说明密码已被用户自行更改，不是默认密码，故密码不用修改
            User newUser = new User(student.getSno(), null, student.getSname(), null);
            userDao.modifyUser(newUser, oldSno);
        } else { // 说明密码是默认密码，重置默认密码为学号后四位
            String newPwd = student.getSno().substring(student.getSno().length() - 4);
            User newUser = new User(student.getSno(), newPwd, student.getSname(), null);
            userDao.modifyUser(newUser, oldSno);
        }
        return result;
    }

    @Override
    public List<Student> queryByMultipleConditions(Student student) {
        List<Student> students = studentDao.queryByMultipleConditions(student);
        if (students != null) {
            for (Student student1 : students) {
                int age = WebUtils.getAge(student1.getSbirthday());
                student1.setSage(age);
            }
        }
        return students;
    }
}
