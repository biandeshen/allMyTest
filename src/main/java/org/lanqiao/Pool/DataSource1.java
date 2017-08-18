package org.lanqiao.Pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DataSource1 {
    private static int initCount = 10;
    private static int maxCount = 30;
    private int currentCount = 0;
    LinkedList<Connection> connectionPool = new LinkedList<Connection>();

    public DataSource1() {
        for (int i = 0; i < initCount; i++) {
            try {
                this.connectionPool.addLast(this.createConnection());
                this.currentCount++;
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public Connection getConnection() throws SQLException {
        synchronized (connectionPool) {
            if (connectionPool.size() > 0)
                return connectionPool.removeFirst();

            if (this.currentCount < maxCount) {
                this.currentCount++;
                return createConnection();
            }

            throw new SQLException("已没有链接");
        }
    }

    public void free(Connection conn) {
        this.connectionPool.addLast(conn);
    }

    private Connection createConnection() throws SQLException {
        Connection realconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanqiaotestfjp?serverTimezone=GMT%2B8", "root", "ascent");
        MyconnectionHandler proxy = new MyconnectionHandler(this);
        return proxy.bind(realconn);
    }

}

class MyconnectionHandler implements InvocationHandler {
    private Connection realConnection;
    private Connection warpedConnection;
    private DataSource1 dataSource;

    MyconnectionHandler(DataSource1 dataSource) {
        this.dataSource = dataSource;
    }

    Connection bind(Connection realconn) {
        this.realConnection = realconn;
        this.warpedConnection = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] { Connection.class }, this);
        return warpedConnection;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("close".equals(method.getName())) {
            this.dataSource.connectionPool.addLast(this.warpedConnection);
        }
        return method.invoke(this.realConnection, args);
    }
}