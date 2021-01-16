package com.lab.test;

import com.lab.bean.TCC;
import com.lab.dao.TCCDao;
import com.lab.dao.impl.TCCDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-27-16:06
 * @description
 */
public class TCCDaoTest {

    private TCCDao tccDao = new TCCDaoImpl();
    @Test
    public void queryAllTeachCourseClass() {
        List<TCC> tccs = tccDao.queryAllTeachCourseClass();
        for (TCC tcc : tccs) {
            System.out.println(tcc.toString());
        }

    }

    @Test
    public void addTeachCourseClass() {
        int result = tccDao.addTeachCourseClass(new TCC("2", "10021", null, "10152", null, 100, 0, "1-315", "周一1-2节"));
        System.out.println(result);
    }

    @Test
    public void deleteTeachCourseClass() {
    }

    @Test
    public void modifyTeachCourseClass() {
    }
}