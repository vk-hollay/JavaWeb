package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Hollay
 * @create 2020-11-26-21:04
 * @description
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.saveOrderItem(new OrderItem(null, "Java编程思想", 1, new BigDecimal(100), new BigDecimal(100), "123"));
        orderItemDao.saveOrderItem(new OrderItem(null, "C语言程序设计", 3, new BigDecimal(50), new BigDecimal(150), "123"));
        orderItemDao.saveOrderItem(new OrderItem(null, "赌神", 1, new BigDecimal(200), new BigDecimal(200), "123"));
    }
}