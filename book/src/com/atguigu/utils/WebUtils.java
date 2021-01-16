package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

/**
 * @author Hollay
 * @create 2020-11-11-19:40
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
            // 把所有请求的参数都注入到 user 对象中
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

}
