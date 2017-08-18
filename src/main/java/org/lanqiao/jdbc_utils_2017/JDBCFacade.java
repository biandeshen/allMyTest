package org.lanqiao.jdbc_utils_2017;

import java.sql.ResultSet;

/**
 * 对JDBC的简单封装，避免繁琐的连接过程，为用户提供简捷的操作。
 * 
 * @author fanjiangpan
 *
 */
public interface JDBCFacade {

    /**
     * 
     * @param sql
     *            查询语句
     * @return 结果集
     */
    ResultSet excuteQuery(String sql);

    /**
     * 执行增删改
     * 
     * @param sql
     * @return 受影响行数
     */
    int excuteUpdate(String sql);
}
