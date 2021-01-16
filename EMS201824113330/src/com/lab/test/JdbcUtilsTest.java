package com.lab.test;

import com.lab.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;


/**
 * @author 201824113330
 * @create 2020-12-04-15:35
 * @description
 */
public class JdbcUtilsTest {

    @Test
    public void test1() {
        for (int i = 0; i < 20; i++) {
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            JdbcUtils.close(conn);
        }
    }
}