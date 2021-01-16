package com.lab.bean;

import java.io.Serializable;

/**
 * @author 201824113330
 * @create 2020-12-04-14:41
 * @description
 */
public class Employee {
    private String id;
    private String password;
    private String name;
    private String phone;
    private String userType;
    private Integer department;

    public Employee() {
    }

    public Employee(String id, String password, String name, String phone, String userType, Integer department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.userType = userType;
        this.department = department;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", userType='" + userType + '\'' +
                ", department=" + department +
                '}';
    }
}
