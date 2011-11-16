package com.sybase.jdbc2.jdbc;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 */
public class CallableStatementWrapperTest extends CallableStatementTestCase {
    private Date myDatetime = Date.valueOf("2009-01-01");


    public CallableStatementWrapperTest() throws Exception {
        super(true);
    }


    @Test
    public void test_callableStatementWithSqlTypeAndScale() throws Exception {
        CallableStatement cstmt = createStatement();

        cstmt.setObject(1, MY_NUMERIC, Types.NUMERIC, 2);
        cstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        cstmt.setObject(3, myDatetime, Types.DATE);
        cstmt.setObject(4, MY_DATETIME_MILLIS, Types.TIMESTAMP);
        cstmt.setObject(5, MY_BIT, Types.BIT);
        cstmt.setObject(6, MY_INT, Types.INTEGER);

        cstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(myDatetime, resultSet.getDate("MY_DATETIME"));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_callableStatementWithSqlType() throws Exception {
        CallableStatement cstmt = createStatement();

        cstmt.setObject(1, MY_NUMERIC, Types.NUMERIC);
        cstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        cstmt.setObject(3, myDatetime, Types.DATE);
        cstmt.setObject(4, MY_DATETIME_MILLIS, Types.TIMESTAMP);
        cstmt.setObject(5, MY_BIT, Types.BIT);
        cstmt.setObject(6, MY_INT, Types.INTEGER);

        cstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(myDatetime, resultSet.getDate("MY_DATETIME"));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_callableStatementWithoutSqlType() throws Exception {
        CallableStatement cstmt = createStatement();

        cstmt.setObject(1, MY_NUMERIC);
        cstmt.setObject(2, MY_VARCHAR);
        cstmt.setObject(3, myDatetime);
        cstmt.setObject(4, MY_DATETIME_MILLIS);
        cstmt.setObject(5, MY_BIT);
        cstmt.setObject(6, MY_INT);

        cstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(myDatetime, resultSet.getDate("MY_DATETIME"));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }
}
