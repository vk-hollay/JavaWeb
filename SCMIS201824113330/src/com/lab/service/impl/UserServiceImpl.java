package com.lab.service.impl;

import com.lab.bean.User;
import com.lab.dao.UserDao;
import com.lab.dao.impl.UserDaoImpl;
import com.lab.service.UserService;

/**
 * @author 201824113330
 * @create 2020-12-23-18:13
 * @description
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String id, String pwd) {
        User user = userDao.queryByIdAndPassword(id, pwd);
        if (user != null && (user.getName() == null || "".equals(user.getName()))) {
            if ("管理员".equals(user.getUserType())) {
                user.setName("管理员");
            } else if ("学生".equals(user.getUserType())) {
                String userName = userDao.getUserNameFromStudentTable(user.getId());
                user.setName(userName);
            }
        }
        return user;
    }

    @Override
    public int modifyPassword(String id, String pwd) {
         return userDao.modifyPassword(id, pwd);
    }
}
