package com.lab.service;

import com.lab.bean.SCT;
import com.lab.bean.Student;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-17:10
 * @description
 */
public interface SCTService {

    public List<SCT> queryAllSelectedCourses();

    public int addSelectedCourse(SCT sct);

    public int modifyGrade(String tccno, String sno, String grade);

    public int deleteSelectedCourse(String sno, String tccno);

    public List<SCT> querySelectedCoursesBySno(String sno);

    public List<SCT> queryByMultipleConditions(SCT sct);

}
