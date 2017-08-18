package org.lanqiao.reflect;

import java.lang.reflect.Array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 反射操作数组
 * @author fanjiangpan
 *
 */
public class ArrayDemo {
    static final Logger LOGGER = LoggerFactory.getLogger(ArrayDemo.class);
    public static void main(String[] args) {
        try {
            Class claz = Class.forName("java.lang.String");
            Object arr = Array.newInstance(claz, 10);
            //设置下标为5的值
            Array.set(arr, 5, "Java");
            //获取下标为5的值
            String string = (String) Array.get(arr, 5);
            System.out.println(string);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
