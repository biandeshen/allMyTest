package org.lanqiao.sql.ijdbc_utils_2017;

import java.io.IOException;
import java.util.Properties;


public class Config {
    private static String driver;
    private static String url;
    private static String name;
    private static String pwd;
    private static int poolMaxCount;
    
    static{
        try {
            Properties properties = new Properties();
            properties.load(SimpleDataSource.class.getResourceAsStream("jdbc.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            pwd = properties.getProperty("pwd");
            poolMaxCount =Integer.parseInt(properties.getProperty("poolMaxCount"));
            if (poolMaxCount <= 0) {
                poolMaxCount = 10;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getName() {
        return name;
    }

    public static String getPwd() {
        return pwd;
    }

    public static int getPoolMaxCount() {
        return poolMaxCount;
    }
    
}
