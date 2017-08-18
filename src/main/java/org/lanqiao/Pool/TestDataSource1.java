package org.lanqiao.Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDataSource1 {

    public static void main(String[] args) {
        DataSource1 d1 = new DataSource1();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = d1.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            ResultSetMetaData rmd = rs.getMetaData();
            int i = rmd.getColumnCount();
            
            for(int j = 1;j < i; j++){
            System.out.print(rmd.getColumnName(j)+"\t"/*+rmd.getColumnTypeName(j)*/);
            }
            System.out.println();
            
            while(rs.next()){
                for(int j = 1;j < i; j++){
                System.out.print(rs.getString(j));
                System.out.print("\t  ");
                }
                System.out.println();
            }
            System.out.println("\n\n\n当前连接池连接数为："+d1.connectionPool.size());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println("当前连接池连接数为："+d1.connectionPool.size());
        }
    }

}
