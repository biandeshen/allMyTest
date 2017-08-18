package org.lanqiao.jdbc_utils_2017;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JDBCFacadeImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testExcuteQuery() throws SQLException {
//        String sql = "select * from employee";
//        ResultSet rs = JDBCFacadeImpl.me.excuteQuery(sql);
//        assertTrue(rs.next());
//        System.out.println(JDBCFacadeImpl.me.connList.size());
//    }

    @Test
    public void testExcuteUpdate() {
        String sql = "UPDATE employee SET empName='cjj' where empID=8 or empID=17";
        int rs = JDBCFacadeImpl.me.excuteUpdate(sql);
        assertTrue(rs>0);
        System.out.println(rs);
    }
}
