package com.lab.service;

import com.lab.bean.User;

/**
 * @author 201824113330
 * @create 2020-12-23-18:10
 * @description
 */
public interface UserService {

    public User login(String id, String pwd);

    public int modifyPassword(String id, String pwd);
}
