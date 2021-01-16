package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-22-16:15
 * @description
 */
public class Course {
    private String cno;
    private String cname;
    private String cpno; //先行课编号
    private String cpname; // 先行课名称
    private Integer ccredit; //学分

    public Course() {
    }

    public Course(String cno, String cname, String cpno, String cpname, Integer ccredit) {
        this.cno = cno;
        this.cname = cname;
        this.cpno = cpno;
        this.cpname = cpname;
        this.ccredit = ccredit;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpno() {
        return cpno;
    }

    public void setCpno(String cpno) {
        this.cpno = cpno;
    }

    public Integer getCcredit() {
        return ccredit;
    }

    public void setCcredit(Integer ccredit) {
        this.ccredit = ccredit;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", cpno='" + cpno + '\'' +
                ", cpname='" + cpname + '\'' +
                ", ccredit=" + ccredit +
                '}';
    }
}
