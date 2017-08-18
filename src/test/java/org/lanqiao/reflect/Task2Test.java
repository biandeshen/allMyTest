package org.lanqiao.reflect;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Task2Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMap2Bean() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", "18");
        map.put("id", "001");
        map.put("tel", "110");
        Teacher teacher = Task2.map2Bean(map, Teacher.class);
        assertTrue(teacher.getName().equals("张三"));    
        
        
    }

}
