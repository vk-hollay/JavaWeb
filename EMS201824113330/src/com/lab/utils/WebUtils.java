package com.lab.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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
}
