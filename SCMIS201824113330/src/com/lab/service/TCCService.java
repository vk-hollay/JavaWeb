package com.lab.service;

import com.lab.bean.Student;
import com.lab.bean.TCC;
import com.lab.bean.User;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-17:07
 * @description
 */
public interface TCCService {

    public List<TCC> queryAllTeachCourseClass(User user);

    public int addTeachCourseClass(TCC tcc);

    public int deleteTeachCourseClass(String tccno);

    public int modifyTeachCourseClass(TCC tcc);

    /**
     * 处理学生端选课选课和取消选课功能
     * @param tccno
     * @param sno
     * @param k   1为选课， 0为取消选课
     * @return
     */
    public TCC selectCourse(String tccno, String sno, String k);

    public List<TCC> queryByMultipleConditions(TCC tcc, User user);
}
