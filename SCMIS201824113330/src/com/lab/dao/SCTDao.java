package com.lab.dao;

import com.lab.bean.SCT;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-15:45
 * @description
 */
public interface SCTDao {

    public List<SCT> queryAllSelectedCourses();

    public int addSelectedCourse(SCT sct);

    public int deleteSelectedCourse(String tccno, String sno);

    public int modifyGrade(Integer grade, String sno, String tccno);

    public List<String> queryTccnoBySno(String sno);

    public List<SCT> querySelectedCoursesBySno(String sno);

    public List<SCT> queryByMultipleConditions(SCT sct);
}
