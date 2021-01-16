package com.lab.service.impl;

import com.lab.bean.SCT;
import com.lab.bean.TCC;
import com.lab.bean.User;
import com.lab.dao.TCCDao;
import com.lab.dao.impl.SCTDaoImpl;
import com.lab.dao.impl.TCCDaoImpl;
import com.lab.service.TCCService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-27-17:15
 * @description
 */
public class TCCServiceImpl implements TCCService {

    private TCCDao tccDao = new TCCDaoImpl();

    @Override
    public List<TCC> queryAllTeachCourseClass(User user) {
        List<TCC> tccs = tccDao.queryAllTeachCourseClass();
        if ("学生".equals(user.getUserType())) {
            List<String> tccnos = new SCTDaoImpl().queryTccnoBySno(user.getId());
            if (tccnos.size() == 0) {
                return tccs;
            }
            int num = 0;
            for (TCC tcc : tccs) {
                if (tcc.getTccno().equals(tccnos.get(num))) {
                    tcc.setFlag(true);
                    num++;
                }
                if (num >= tccnos.size()) {
                    break;
                }
            }
        }
        return tccs;
    }

    @Override
    public int addTeachCourseClass(TCC tcc) {
        return tccDao.addTeachCourseClass(tcc);
    }

    @Override
    public int deleteTeachCourseClass(String tccno) {
        return tccDao.deleteTeachCourseClass(tccno);
    }

    @Override
    public int modifyTeachCourseClass(TCC tcc) {
        return tccDao.modifyTeachCourseClass(tcc);
    }

    @Override
    public TCC selectCourse(String tccno, String sno, String k) {
        SCTDaoImpl sctDao = new SCTDaoImpl();
        TCC tcc = tccDao.queryByTccno(tccno);
        if ("1".equals(k)) {
            int num = tcc.getSelected() + 1;
            if (num <= tcc.getLimited()) {
                tcc.setSelected(num);
                tccDao.modifyTeachCourseClass(tcc);
                SCT sct = new SCT();
                sct.setTccno(tccno);
                sct.setSno(sno);
                sctDao.addSelectedCourse(sct); // 向选课信息表添加该记录
                return tcc;
            } else {
                return null;
            }
        } else {
            tcc.setSelected(tcc.getSelected() - 1);
            tccDao.modifyTeachCourseClass(tcc);
            sctDao.deleteSelectedCourse(tccno, sno); // 删除选课信息表该记录
            return tcc;
        }
    }

    @Override
    public List<TCC> queryByMultipleConditions(TCC tcc, User user) {
        List<TCC> tccs = tccDao.queryByMultipleConditions(tcc);
        if ("学生".equals(user.getUserType())) {
            List<String> tccnos = new SCTDaoImpl().queryTccnoBySno(user.getId());
            if (tccnos.size() == 0) {
                return tccs;
            }
            int num = 0;
            for (TCC tcc1 : tccs) {
                if (tcc1.getTccno().equals(tccnos.get(num))) {
                    tcc1.setFlag(true);
                    num++;
                }
                if (num >= tccnos.size()) {
                    break;
                }
            }
        }
        return tccs;
    }
}
