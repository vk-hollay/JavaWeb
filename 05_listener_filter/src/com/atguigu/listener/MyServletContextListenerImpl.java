package com.atguigu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Hollay
 * @create 2020-11-01-21:13
 * @description
 */
public class MyServletContextListenerImpl implements ServletContextListener {

//    ServletContext 对象的 创建回调
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象被创建了！");
    }

//    ServletContext 对象的销毁回调
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁了！");
    }
}

//    监听器记得一定要在 web.xml 文件中配置之后才会生效