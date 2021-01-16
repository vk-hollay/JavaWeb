package com.atguigu.test;

import java.lang.reflect.Method;

/**
 * @author Hollay
 * @create 2020-11-10-23:45
 * @description 通过反射调用业务方法，优化大量 if else代码的使用
 */
public class UserServletTest {

    public void login() {
        System.out.println("调用了login()方法");
    }

    public void regist() {
        System.out.println("调用了regist()方法");
    }

    public void updateUser() {
        System.out.println("调用了updateUser()方法");
    }

    public void updateUserPassword() {
        System.out.println("调用了updateUserPassword()方法");
    }

    public static void main(String[] args) {

        String action = "login";

/*  原本做法： 通过大量 if else去鉴别 action业务应该调用哪个方法
        if ("login".equals(action)) {
            new UserServletTest().login();
        } else if ("regist".equals(action)) {
            new UserServletTest().regist();
        } else if ("updateUser".equals(action)) {
            ...
        } else if ...
*/

//通过反射进性优化，如下代码所示
        try {
            // 获取 action 业务鉴别字符串， 获取相应的业务方法 反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);

            // 调用目标业务方法
            method.invoke(new UserServletTest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
