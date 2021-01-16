package com.lab.bean;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-22-16:08
 * @description
 */
public class Teacher {
    private String tno;
    private String tname;
    private String tsex;
    private String tbirthday;
    private int tage;
    private String teb; //学历
    private String tpt;  //职称
    private List<String> tcourse; //主讲课程

    public Teacher() {
    }

    public Teacher(String tno, String tname, String tsex, String tbirthday, int tage, String teb, String tpt, List<String> tcourse) {
        this.tno = tno;
        this.tname = tname;
        this.tsex = tsex;
        this.tbirthday = tbirthday;
        this.tage = tage;
        this.teb = teb;
        this.tpt = tpt;
        this.tcourse = tcourse;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTbirthday() {
        return tbirthday;
    }

    public void setTbirthday(String tbirthday) {
        this.tbirthday = tbirthday;
    }

    public String getTeb() {
        return teb;
    }

    public void setTeb(String teb) {
        this.teb = teb;
    }

    public String getTpt() {
        return tpt;
    }

    public void setTpt(String tpt) {
        this.tpt = tpt;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }

    public List<String> getTcourse() {
        return tcourse;
    }

    public void setTcourse(List<String> tcourse) {
        this.tcourse = tcourse;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex + '\'' +
                ", tbirthday='" + tbirthday + '\'' +
                ", tage=" + tage +
                ", teb='" + teb + '\'' +
                ", tpt='" + tpt + '\'' +
                ", tcourse=" + tcourse +
                '}';
    }
}
