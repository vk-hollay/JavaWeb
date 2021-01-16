package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-27-15:16
 * @description 开课班 teach-course-class
 */
public class TCC {
    private String tccno; //开课编号
    private String cno; //课程号
    private String cname; //课程号
    private String tno; //教师号
    private String tname; //教师号
    private int limited; //限选人数
    private int selected; //已选人数
    private String classroom; //教室
    private String classtime; //上课时间
    private boolean flag = false; //用于学生端登录后显示该开课班是否已选，true已选，false未选


    public TCC() {
    }

    public TCC(String tccno, String cno, String cname, String tno, String tname, int limited, int selected, String classroom, String classtime) {
        this.tccno = tccno;
        this.cno = cno;
        this.cname = cname;
        this.tno = tno;
        this.tname = tname;
        this.limited = limited;
        this.selected = selected;
        this.classroom = classroom;
        this.classtime = classtime;
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

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getTccno() {
        return tccno;
    }

    public void setTccno(String tccno) {
        this.tccno = tccno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "TCC{" +
                "tccno='" + tccno + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", limited=" + limited +
                ", selected=" + selected +
                ", classroom='" + classroom + '\'' +
                ", classtime='" + classtime + '\'' +
                ", flag=" + flag +
                '}';
    }
}
