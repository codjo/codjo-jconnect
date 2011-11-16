package com.sybase.jdbc2.jdbc;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 */
public class ResultSetWrapperTest extends ResultSetTestCase {

    public ResultSetWrapperTest() throws Exception {
        super(true);
    }


    @Test
    public void test_getObject_Statement() throws Exception {
        createInsertStatement("10.22, 'a', '2009-01-01', '2009-01-01 15:23:24.233', 1, 32");
        ResultSet resultSet = connection.createStatement().executeQuery("select * from TABLE_TEST");

        assertTrue(resultSet.next());
        assertEquals(BigDecimal.valueOf(10.22), resultSet.getObject("MY_NUMERIC"));
        assertEquals("a", resultSet.getObject("MY_VARCHAR"));
        assertEquals(Timestamp.valueOf("2009-01-01 00:00:00.0"), resultSet.getObject("MY_DATETIME"));
        assertEquals(Timestamp.valueOf("2009-01-01 15:23:24.233"), resultSet.getObject("MY_DATETIME_SEC"));
        assertEquals(true, resultSet.getObject("MY_BIT"));
        assertEquals(32, resultSet.getObject("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_getObject() throws Exception {
        PreparedStatement pstmt = createInsertPreparedStatement();
        pstmt.setObject(1, myNumeric, Types.NUMERIC, 2);
        pstmt.setObject(2, myVarchar, Types.VARCHAR);
        pstmt.setObject(3, myDatetime, Types.DATE);
        pstmt.setObject(4, myDatetimeNoSec, Types.TIMESTAMP);
        pstmt.setObject(5, myBit, Types.BIT);
        pstmt.setObject(6, myInt, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = excuteSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(myNumeric, resultSet.getObject("MY_NUMERIC"));
        assertEquals(myVarchar, resultSet.getObject("MY_VARCHAR"));
        assertEquals(myDatetime, resultSet.getObject("MY_DATETIME"));
        assertEquals(myDatetimeNoSec, resultSet.getObject("MY_DATETIME_SEC"));
        assertEquals(myBit, resultSet.getObject("MY_BIT"));
        assertEquals(myInt, resultSet.getObject("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_getObjectForTimestamp() throws Exception {
        PreparedStatement pstmt = createInsertPreparedStatement();
        pstmt.setObject(1, myNumeric, Types.NUMERIC, 2);
        pstmt.setObject(2, myVarchar, Types.VARCHAR);
        pstmt.setObject(3, myDatetimeNoSec, Types.TIMESTAMP);
        pstmt.setObject(4, myDatetimeMillis, Types.TIMESTAMP);
        pstmt.setObject(5, myBit, Types.BIT);
        pstmt.setObject(6, myInt, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = excuteSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(myNumeric, resultSet.getObject("MY_NUMERIC"));
        assertEquals(myVarchar, resultSet.getObject("MY_VARCHAR"));
        assertEquals(myDatetimeNoSec, resultSet.getObject("MY_DATETIME"));
        assertEquals(myDatetimeMillis, resultSet.getObject("MY_DATETIME_SEC"));
        assertEquals(myBit, resultSet.getObject("MY_BIT"));
        assertEquals(myInt, resultSet.getObject("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_getObject_nanoSeconds() throws Exception {
        PreparedStatement pstmt = createInsertPreparedStatement();
        pstmt.setObject(1, myNumeric);
        pstmt.setObject(2, myVarchar);
        pstmt.setObject(3, myDatetimeMillis);
        pstmt.setObject(4, myDatetimeNanos);
        pstmt.setObject(5, myBit);
        pstmt.setObject(6, myInt);
        pstmt.executeUpdate();

        assertNull(pstmt.getWarnings());

        ResultSet resultSet = excuteSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(myNumeric, resultSet.getObject("MY_NUMERIC"));
        assertEquals(myVarchar, resultSet.getObject("MY_VARCHAR"));
        assertEquals(myDatetimeMillis, resultSet.getObject("MY_DATETIME"));
        assertEquals(myDatetimeMillis, resultSet.getObject("MY_DATETIME_SEC"));
        assertEquals(myBit, resultSet.getObject("MY_BIT"));
        assertEquals(myInt, resultSet.getObject("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_getTimestamp() throws Exception {
        PreparedStatement pstmt = createInsertPreparedStatement();
        pstmt.setObject(1, myNumeric, Types.NUMERIC);
        pstmt.setObject(2, myVarchar, Types.VARCHAR);
        pstmt.setObject(3, myDatetimeNoSec, Types.TIMESTAMP);
        pstmt.setObject(4, myDatetimeMillis, Types.TIMESTAMP);
        pstmt.setObject(5, myBit, Types.BIT);
        pstmt.setObject(6, myInt, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = excuteSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(myNumeric, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(myVarchar, resultSet.getString("MY_VARCHAR"));
        assertEquals(myDatetimeNoSec, resultSet.getTimestamp("MY_DATETIME"));
        assertEquals(myDatetimeMillis, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(myBit, resultSet.getBoolean("MY_BIT"));
        assertEquals(myInt.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_getObject_nullValue() throws Exception {
        PreparedStatement pstmt = createInsertPreparedStatement();
        pstmt.setObject(1, myNumeric, Types.NUMERIC);
        pstmt.setObject(2, null, Types.VARCHAR);
        pstmt.setObject(3, null, Types.TIMESTAMP);
        pstmt.setObject(4, null, Types.TIMESTAMP);
        pstmt.setObject(5, myBit, Types.BIT);
        pstmt.setObject(6, myInt, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = excuteSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(myNumeric, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(null, resultSet.getString("MY_VARCHAR"));
        assertEquals(null, resultSet.getDate("MY_DATETIME"));
        assertEquals(null, resultSet.getDate("MY_DATETIME_SEC"));
        assertEquals(myBit, resultSet.getBoolean("MY_BIT"));
        assertEquals(myInt.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }
}
