package com.lab.test;

import com.lab.bean.SCT;
import com.lab.bean.TCC;
import com.lab.dao.SCTDao;
import com.lab.dao.impl.SCTDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-27-17:04
 * @description
 */
public class SCTDaoTest {

    private SCTDao sctDao = new SCTDaoImpl();
    @Test
    public void quertAllSelectedCourses() {
        List<SCT> scts = sctDao.queryAllSelectedCourses();
        for (SCT sct : scts) {
            System.out.println(sct.toString());
        }
    }

    @Test
    public void addSelectedCourse() {
    }

    @Test
    public void deleteSelectedCourse() {
    }

    @Test
    public void modifySelectedCourse() {
    }

    @Test
    public void  queryTccnoBySno() {
        List<String> strings = sctDao.queryTccnoBySno("201824113330");
        for (String s: strings) {
            System.out.println(s);
        }
    }
}