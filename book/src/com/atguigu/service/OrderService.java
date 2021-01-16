package com.atguigu.service;

import com.atguigu.pojo.Cart;

/**
 * @author Hollay
 * @create 2020-11-26-21:12
 * @description
 */
public interface OrderService {

    public String creatOrder(Cart cart, Integer userId);
}
