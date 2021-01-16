package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-22-16:39
 * @description  选课信息表
 */
public class SCT {
    private String tccno;
    private String cno;
    private String cname;
    private String tname;
    private String sno;
    private String sname;
    private Integer grade;

    public SCT() {
    }

    public SCT(String tccno, String cno, String cname, String tname, String sno, String sname, Integer grade) {
        this.tccno = tccno;
        this.cno = cno;
        this.cname = cname;
        this.tname = tname;
        this.sno = sno;
        this.sname = sname;
        this.grade = grade;
    }

    public String getTccno() {
        return tccno;
    }

    public void setTccno(String tccno) {
        this.tccno = tccno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Override
    public String toString() {
        return "SCT{" +
                "tccno='" + tccno + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", tname='" + tname + '\'' +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", grade=" + grade +
                '}';
    }
}
