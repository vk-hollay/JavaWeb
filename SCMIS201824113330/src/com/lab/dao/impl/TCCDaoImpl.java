package com.lab.dao.impl;

import com.lab.bean.TCC;
import com.lab.dao.TCCDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-15:59
 * @description
 */
public class TCCDaoImpl extends BaseDao implements TCCDao {
    @Override
    public List<TCC> queryAllTeachCourseClass() {
        String sql = "select a.tccno,a.cno,b.cname,a.tno,c.tname,a.limited,a.selected,a.classroom,a.classtime " +
                "from tcc a,course b,teacher c where a.cno=b.cno and a.tno=c.tno order by a.tccno";
        return queryForList(TCC.class, sql);
    }

    @Override
    public int addTeachCourseClass(TCC tcc) {
        String sql = "insert into tcc(tccno,cno,tno,limited,selected,classroom,classtime) values(?,?,?,?,?,?,?)";
        return update(sql, tcc.getTccno(), tcc.getCno(), tcc.getTno(), tcc.getLimited(), tcc.getSelected(), tcc.getClassroom(), tcc.getClasstime());
    }

    @Override
    public int deleteTeachCourseClass(String tccno) {
        String sql = "delete from tcc where tccno=?";
        return update(sql, tccno);
    }

    @Override
    public int modifyTeachCourseClass(TCC tcc) {
        String sql = "update tcc set cno=?,tno=?,limited=?,selected=?,classroom=?,classtime=? where tccno=?";
        return update(sql, tcc.getCno(), tcc.getTno(), tcc.getLimited(), tcc.getSelected(), tcc.getClassroom(), tcc.getClasstime(), tcc.getTccno());
    }

    @Override
    public TCC queryByTccno(String tccno) {
        String sql = "select a.tccno,a.cno,b.cname,a.tno,c.tname,a.limited,a.selected,a.classroom," +
                "a.classtime from tcc a,course b,teacher c where a.cno=b.cno and a.tno=c.tno and tccno=?";
        return queryForOne(TCC.class, sql, tccno);
    }

    @Override
    public List<TCC> queryByMultipleConditions(TCC tcc) {
        String sql = "select a.tccno,a.cno,b.cname,a.tno,c.tname,a.limited,a.selected,a.classroom,a.classtime " +
                "from tcc a,course b,teacher c where a.cno=b.cno and a.tno=c.tno and (a.tccno like ? or ? is null) and " +
                "(b.cname like ? or ? is null) and (c.tname like ? or ? is null) and (a.classroom like ? or ? is null) and  (a.classtime like ? or ? is null)";
        return queryForList(TCC.class, sql, "%"+tcc.getTccno()+"%", tcc.getTccno(), "%"+tcc.getCname()+"%", tcc.getCname(), "%"+tcc.getTname()+"%", tcc.getTname(),
                "%"+tcc.getClassroom()+"%", tcc.getClassroom(), "%"+tcc.getClasstime()+"%", tcc.getClasstime());
    }
}
