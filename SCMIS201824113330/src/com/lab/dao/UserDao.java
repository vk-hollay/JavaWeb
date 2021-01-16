package com.lab.dao;

import com.lab.bean.User;

/**
 * @author 201824113330
 * @create 2020-12-22-23:38
 * @description
 */
public interface UserDao {

    public User queryByIdAndPassword(String id, String password);

    public String getUserNameFromStudentTable(String id);

    public int addUser(User user);

    public int deleteUser(String id);

    public int modifyUser(User user, String oldId);

    public int modifyPassword(String id, String password);
}
