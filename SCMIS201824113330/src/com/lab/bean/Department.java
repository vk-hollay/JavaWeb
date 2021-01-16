package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-22-16:12
 * @description
 */
public class Department {
    private String dno;
    private String dname;
    private String dmanager; //系主任教工号

    public Department() {
    }

    public Department(String dno, String dname, String dmanager) {
        this.dno = dno;
        this.dname = dname;
        this.dmanager = dmanager;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDmanager() {
        return dmanager;
    }

    public void setDmanager(String dmanager) {
        this.dmanager = dmanager;
    }

    @Override
    public String toString() {
        return "department{" +
                "dno='" + dno + '\'' +
                ", dname='" + dname + '\'' +
                ", dmanager='" + dmanager + '\'' +
                '}';
    }
}
