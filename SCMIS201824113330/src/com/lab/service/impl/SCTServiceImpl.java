package com.lab.service.impl;

import com.lab.bean.SCT;
import com.lab.dao.SCTDao;
import com.lab.dao.impl.SCTDaoImpl;
import com.lab.service.SCTService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-17:14
 * @description
 */
public class SCTServiceImpl implements SCTService {

    private SCTDao sctDao = new SCTDaoImpl();
    @Override
    public List<SCT> queryAllSelectedCourses() {
        return sctDao.queryAllSelectedCourses();
    }

    @Override
    public int addSelectedCourse(SCT sct) {
        return sctDao.addSelectedCourse(sct);
    }

    @Override
    public int deleteSelectedCourse(String sno, String tccno) {
        return sctDao.deleteSelectedCourse(tccno, sno);
    }

    @Override
    public int modifyGrade(String tccno, String sno, String grade) {
        int result;
        if ("".equals(grade)) {
            result = sctDao.modifyGrade(null, sno, tccno);
        } else {
            int gradeInt = Integer.parseInt(grade);
            if (gradeInt >= 0 && gradeInt <= 100) {
                result = sctDao.modifyGrade(gradeInt, sno, tccno);
            } else {
                result = -1;
            }
        }
        return result;
    }

    @Override
    public List<SCT> querySelectedCoursesBySno(String sno) {
        return sctDao.querySelectedCoursesBySno(sno);
    }

    @Override
    public List<SCT> queryByMultipleConditions(SCT sct) {
        return sctDao.queryByMultipleConditions(sct);
    }
}
