package com.lab.dao.impl;

import com.lab.bean.SCT;
import com.lab.dao.SCTDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-15:58
 * @description
 */
public class SCTDaoImpl extends BaseDao implements SCTDao {

    @Override
    public List<SCT> queryAllSelectedCourses() {
        String sql = "SELECT sc.tccno,sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc," +
                "student st,tcc tc,course c,teacher te WHERE sc.sno=st.sno AND tc.cno=c.cno " +
                "AND tc.tno=te.tno AND sc.tccno=tc.tccno order by sc.sno";
                return queryForList(SCT.class, sql);
    }

    @Override
    public int addSelectedCourse(SCT sct) {
        String sql = "insert into sct(tccno,sno,grade) values(?,?,?)";
        return update(sql, sct.getTccno(), sct.getSno(), sct.getGrade());
    }

    @Override
    public int deleteSelectedCourse(String tccno, String sno) {
        String sql = "delete from sct where tccno=? and sno=?";
        return update(sql, tccno, sno);
    }

    @Override
    public int modifyGrade(Integer grade, String sno, String tccno) {
        String sql = "update sct set grade=? where sno=? and tccno=?";
        return update(sql, grade, sno, tccno);
    }

    @Override
    public List<String> queryTccnoBySno(String sno) {
        String sql = "select tccno from sct where sno=?";
        return (List<String>) (List)queryForOneColumnList(sql, "tccno",sno);
    }

    @Override
    public List<SCT> querySelectedCoursesBySno(String sno) {
        String sql = "SELECT sc.tccno,sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc," +
                "student st,tcc tc,course c,teacher te WHERE sc.sno=st.sno AND tc.cno=c.cno AND tc.tno=te.tno " +
                "AND sc.tccno=tc.tccno AND sc.sno=?";
        return queryForList(SCT.class, sql, sno);
    }

    @Override
    public List<SCT> queryByMultipleConditions(SCT sct) {
        String sql = "SELECT sc.tccno,sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc,student st,tcc tc,course c,teacher te " +
                "WHERE sc.sno=st.sno AND tc.cno=c.cno AND tc.tno=te.tno AND sc.tccno=tc.tccno AND (sc.sno like ? or ? is null) and " +
                "(st.sname like ? or ? is null) and (c.cno like ? or ? is null) and (c.cname like ? or ? is null) and  (te.tname like ? or ? is null)";
        return queryForList(SCT.class, sql, "%"+sct.getSno()+"%", sct.getSno(), "%"+sct.getSname()+"%", sct.getSname(), "%"+sct.getCno()+"%", sct.getCno(),
                "%"+sct.getCname()+"%", sct.getCname(), "%"+sct.getTname()+"%", sct.getTname());
    }
}
