package com.lab.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author 201824113330
 * @create 2020-12-05-1:34
 * @description
 */
public class WebUtils {
    /**
     * 把 Map 中的值注入到对应的 JavaBean 属性中。
     * @param value
     * @param bean
     * @return
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            System.out.println("注入之前：" + bean);
            // 把所有请求的参数都注入到 bean 对象中
            BeanUtils.populate(bean, value);

            System.out.println("注入之后1：" + bean);
            // 遍历 bean对象的属性， 将属性值为""的属性置为 null
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                //String attributeName = field.getName();
                field.setAccessible(true); //bean中的成员变量为private,故必须进行此操作
                Object attributeValue = field.get(bean);
                if ("".equals(attributeValue)) {
                    field.set(bean, null);
                }
                //System.out.println(attributeName + " "  + attributeValue);
            }
            System.out.println("注入之后2：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 根据出生日期求当前年龄
     * @param birthday
     * @return
     */
    public static int getAge(String birthday) {
        Date birthdayDate = null;
        int age = 0;
        try {
            // 把出生日期字符串转换为日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthdayDate = sdf.parse(birthday);
//            System.out.println(birthdayDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        if (cal.before(birthdayDate)) { //出生日期晚于当前时间，直接返回 0
            return age;
        }
        // 当前年、月、日
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1; //月份从 0 开始算，故要 +1
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
//        System.out.println(yearNow + " " + monthNow + " " + dayOfMonthNow);
        // 出生日期年、月、日
        cal.setTime(birthdayDate);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1; //月份从 0 开始算，故要 +1
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
//        System.out.println(yearBirth + " " + monthBirth + " " + dayOfMonthBirth);

        // 求年龄
        age  = yearNow - yearBirth;   //计算整岁数
        if (monthNow == monthBirth && dayOfMonthNow < dayOfMonthBirth) { //当前日期在生日之前，年龄减一
            age -= 1;
        } else if (monthNow < monthBirth) { //当前月份在生日之前，年龄减一
            age -= 1;
        }
        return age;
    }

}
