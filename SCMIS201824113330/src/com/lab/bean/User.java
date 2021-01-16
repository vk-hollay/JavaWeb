package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-22-16:13
 * @description
 */
public class User {
    private String id;
    private String password;
    private String name;
    private String userType;

    public User() {
    }

    public User(String id, String password, String name, String userType) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
