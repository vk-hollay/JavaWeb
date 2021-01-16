package com.lab.dao;

import com.lab.bean.TCC;
import com.lab.bean.Teacher;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-15:50
 * @description
 */
public interface TCCDao {

    public List<TCC> queryAllTeachCourseClass();

    public int addTeachCourseClass(TCC tcc);

    public int deleteTeachCourseClass(String tccno);

    public int modifyTeachCourseClass(TCC tcc);

    public TCC queryByTccno(String tccno);

    public List<TCC> queryByMultipleConditions(TCC tcc);
}
