package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-22-16:05
 * @description
 */
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private String sbirthday;
    private int sage;
    private String sdno; //系别编号
    private String sdname; //系名


    public Student() {
    }

    public Student(String sno, String sname, String ssex, String sbirthday, int sage, String sdno, String sdname) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sbirthday = sbirthday;
        this.sage = sage;
        this.sdno = sdno;
        this.sdname = sdname;
    }

    public Student(String sno, String sname, String ssex, String sbirthday, String sdno, String sdname) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sbirthday = sbirthday;
        this.sdno = sdno;
        this.sdname = sdname;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSdno() {
        return sdno;
    }

    public void setSdno(String sdno) {
        this.sdno = sdno;
    }

    public String getSdname() {
        return sdname;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                ", sage='" + sage + '\'' +
                ", sdno='" + sdno + '\'' +
                ", sdname='" + sdname + '\'' +
                '}';
    }
}

