package com.lab.dao.impl;

import com.lab.bean.User;
import com.lab.dao.UserDao;

/**
 * @author 201824113330
 * @create 2020-12-22-23:40
 * @description
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryByIdAndPassword(String id, String password) {
        String sql = "select * from user where id=? and password=?";
        return queryForOne(User.class, sql, id, password);
    }

    @Override
    public String getUserNameFromStudentTable(String id) {
        String sql = "select sname from student where sno=?";
        return (String) queryForSingleValue(sql, id);
    }


    @Override
    public int addUser(User user) {
        String sql = "insert into user(id,password,userType) values(?,?,?)";
        return update(sql, user.getId(), user.getPassword(), user.getUserType());
    }

    @Override
    public int deleteUser(String id) {
        String sql = "delete from user where id=?";
        return update(sql, id);
    }

    @Override
    public int modifyUser(User user, String oldId) {
        String sql = "update user set id=?,password=? where id=?";
        return update(sql, user.getId(), user.getPassword(), oldId);
    }

    @Override
    public int modifyPassword(String id, String password) {
        String sql = "update user set password=? where id=?";
        return update(sql, password, id);
    }
}
