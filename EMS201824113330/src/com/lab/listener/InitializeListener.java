package com.lab.listener;

/**
 * @author 201824113330
 * @create 2020-12-11-12:25
 * @description
 */

import com.lab.utils.JdbcUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener()
public class InitializeListener implements ServletContextListener {

    /**
     * 项目在启动初始化的时候就会执行的方法（启动服务时执行）
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 项目启动的同时加载数据库驱动，因为第一次加载数据库会比较慢
        Connection connection = JdbcUtils.getConnection();
        //System.out.println(connection);
        JdbcUtils.close(connection);
    }
}
