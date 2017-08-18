package org.lanqiao.jdbc_utils_2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.jdbc.PreparedStatement;
import com.sun.rowset.*;

public enum JDBCFacadeImpl implements JDBCFacade {
    me;

    static final Logger LOGGER = LoggerFactory.getLogger(JDBCFacadeImpl.class);

    final LinkedList<Connection> connList = new LinkedList<Connection>();

    /**
     * @initCount初始连接数量
     */
    private int initCount = 10;
    private int maxCount = 30;
    private int currentCount = 0;

    private JDBCFacadeImpl() {

        this.init();
    }

    public ResultSet excuteQuery(String sql) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return cacheRs(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ExceptionInInitializerError();
        } finally {
            freeConnection(conn);
        }
    }


    public int excuteUpdate(String sql) {
        Connection conn = null;
        try {
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ExceptionInInitializerError();
        }
    }

    private Connection getConnection() throws SQLException {
        // 是否需要一个锁？
        if (connList.size() > 0)
            return connList.removeFirst();

        if (currentCount < maxCount) {
            return this.createConn();
        }
        throw new SQLException("已没有链接");
    }

    /**
     * init初始化连接池
     */
    private void init() {
        for (int i = 0; i < initCount; i++) {
            try {
                this.connList.add(this.createConn());
                currentCount++;
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    /**
     * createConn 创建一个连接
     * @return 一个连接
     * @throws SQLException
     */
    private Connection createConn() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/lanqiaotestfjp?serverTimezone=GMT%2B8", "root", "ascent");
        return connect;

    }

    /**
     * cacheRs 传入一个结果集，并缓存这个结果集
     * @param rs结果集
     * @return  ResultSet结果集
     * @throws SQLException
     */
    private ResultSet cacheRs(ResultSet rs) throws SQLException {

        CachedRowSetImpl rowset = new CachedRowSetImpl();
        rowset.populate(rs);
        return rowset;
    }

    private void freeConnection(Connection conn) {
        try {
            if (null != conn && !conn.isClosed()) {
                this.connList.addLast(conn);
            }
        } catch (SQLException e) {
            // ignore 异常
        }
    }
}
