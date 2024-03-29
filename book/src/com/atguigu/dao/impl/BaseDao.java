package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Hollay
 * @create 2020-10-29-18:29
 * @description
 */
public abstract class BaseDao {

/*
    //使用 DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    */
    /**
     * update() 方法用来执行：insert/update/delete语句
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return 若返回-1，说明执行失败；返回其他表示影响的行数
     *//*
    public int update(String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }
    */

    /**
     * 查询返回一个javaBean的 sql语句
     * @param type 返回的对象类型
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return
     *//*
    public <T> T queryForOne(Class<T> type, String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
    */

    /**
     * 查询返回多个javaBean的 sql语句
     * @param type 返回的对象类型
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return
     *//*
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
    */

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return
     *//*
    public Object queryForSingleValue(String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
*/

/* 以下代码为加上事务管理后的修改版  */

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行：insert/update/delete语句
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return 若返回-1，说明执行失败；返回其他表示影响的行数
     */
    public int update(String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 这里可以捕获，但异常一定要往外抛
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean的 sql语句
     * @param type 返回的对象类型
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 这里可以捕获，但异常一定要往外抛
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean的 sql语句
     * @param type 返回的对象类型
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 这里可以捕获，但异常一定要往外抛
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 这里可以捕获，但异常一定要往外抛
            throw new RuntimeException(e);
        }
    }

}
