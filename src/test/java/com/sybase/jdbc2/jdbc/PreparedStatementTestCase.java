package com.sybase.jdbc2.jdbc;
import com.sybase.jdbc3.tds.SybTimestamp;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.sql.Types;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
/**
 *
 */
@SuppressWarnings({"OverlyComplexClass"})
public abstract class PreparedStatementTestCase extends AbstractTestCase {
    protected static final Timestamp MY_DATETIME_MILLIS = Timestamp.valueOf("2009-01-01 15:23:24.233");
    protected static final Timestamp MY_DATETIME_NANOS = Timestamp.valueOf("2009-01-01 15:23:24.2335");
    protected static final Timestamp MY_DATETIME_NO_SEC = Timestamp.valueOf("2009-01-01 15:23:24");
    protected static final BigDecimal MY_NUMERIC = new BigDecimal("11.11");
    protected static final String MY_VARCHAR
          = "a€aÈÉÊa!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    protected static final Boolean MY_BIT = Boolean.TRUE;
    protected static final Integer MY_INT = 32;
    protected Connection connection;
    protected static final String MY_NUMERIC_COLUMN_NAME = "MY_NUMERIC";
    protected static final int MY_NUMERIC_COLUMN_INDEX = 1;
    protected static final String MY_VARCHAR_COLUMN_NAME = "MY_VARCHAR";
    protected static final int MY_VARCHAR_COLUMN_INDEX = 2;
    protected static final String MY_DATETIME_COLUMN_NAME = "MY_DATETIME";
    protected static final int MY_DATETIME_COLUMN_INDEX = 3;
    protected static final String MY_DATETIME_SEC_COLUMN_NAME = "MY_DATETIME_SEC";
    protected static final int MY_DATETIME_SEC_COLUMN_INDEX = 4;
    protected static final String MY_BIT_COLUMN_NAME = "MY_BIT";
    protected static final int MY_BIT_COLUMN_INDEX = 5;
    protected static final String MY_INT_COLUMN_NAME = "MY_INT";
    protected static final int MY_INT_COLUMN_INDEX = 6;


    PreparedStatementTestCase(boolean isWrapperClassTest) throws Exception {
        super(isWrapperClassTest);
    }


    @Before
    public void setUp() throws Exception {
        connection = getConnection();
        AbstractTestCase.createTestTable(connection);
        doSetUp();
    }


    public void doSetUp() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
        doTearDown();
        AbstractTestCase.dropTestTable(connection);
    }


    public void doTearDown() throws Exception {
    }


    @Test
    public void test_setObject_nullValue_WithSqlType() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(1, null, Types.NUMERIC);
        pstmt.setObject(2, null, Types.VARCHAR);
        pstmt.setObject(3, null, Types.TIMESTAMP);
        pstmt.setObject(4, null, Types.TIMESTAMP);
        pstmt.setObject(5, MY_BIT, Types.BIT);
        pstmt.setObject(6, null, Types.INTEGER);
        pstmt.executeUpdate();

        assertWarningDoesntExist(connection.getWarnings());

        ResultSet resultSet = executeSelectAllQuery();
        assertTrue(resultSet.next());
        assertEquals(null, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(null, resultSet.getString("MY_VARCHAR"));
        assertEquals(null, resultSet.getTimestamp("MY_DATETIME"));
        assertEquals(null, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(0, resultSet.getInt("MY_INT"));
        org.junit.Assert.assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_Numeric_WithSqlType_FullScale() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(1, MY_NUMERIC, Types.NUMERIC, 2);
        pstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(3, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(4, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(5, MY_BIT, Types.BIT);
        pstmt.setObject(6, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        ResultSet resultSet = executeSelectAllQuery();
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_Numeric_WithSqlType_NoFullScale() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(1, MY_NUMERIC, Types.NUMERIC, 1);
        pstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(3, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(4, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(5, MY_BIT, Types.BIT);
        pstmt.setObject(6, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        ResultSet resultSet = executeSelectAllQuery();
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(new BigDecimal("11.10"), resultSet.getBigDecimal("MY_NUMERIC"));
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_Numeric_WithSqlType_NoScale() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(1, MY_NUMERIC, Types.NUMERIC);
        pstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(3, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(4, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(5, MY_BIT, Types.BIT);
        pstmt.setObject(6, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        ResultSet resultSet = executeSelectAllQuery();
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        if (isWrapperTestClass()) {
            wrapperAssert_setObject_Numeric_WithSqlType_NoScale(resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_Numeric_WithSqlType_NoScale(resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
        assertEquals(MY_VARCHAR, resultSet.getString("MY_VARCHAR"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME"));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp("MY_DATETIME_SEC"));
        assertEquals(MY_BIT, resultSet.getBoolean("MY_BIT"));
        assertEquals(MY_INT.intValue(), resultSet.getInt("MY_INT"));
        assertFalse(resultSet.next());
    }


    protected void wrapperAssert_setObject_Numeric_WithSqlType_NoScale(ResultSet resultSet)
          throws SQLException {
        assertEquals("11.11", resultSet.getBigDecimal("MY_NUMERIC").toString());
    }


    protected void sybaseAssert_setObject_Numeric_WithSqlType_NoScale(ResultSet resultSet)
          throws SQLException {
        assertEquals("11.00", resultSet.getBigDecimal("MY_NUMERIC").toString());
    }


    @Test
    public void test_setObject_numeric_objectOfStringType() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(1, "11.11", Types.NUMERIC);
        pstmt.setObject(2, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(3, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(4, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(5, MY_BIT, Types.BIT);
        pstmt.setObject(6, "32", Types.NUMERIC);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        assertTrue(resultSet.next());
        if (isWrapperTestClass()) {
            wrapperAssert_setObject_numeric_objectOfStringType(resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_numeric_objectOfStringType(resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    protected void wrapperAssert_setObject_numeric_objectOfStringType(ResultSet resultSet)
          throws SQLException {
        assertEquals("11.11", resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME).toString());
        assertEquals("32", resultSet.getObject(MY_INT_COLUMN_NAME).toString());
    }


    protected void sybaseAssert_setObject_numeric_objectOfStringType(ResultSet resultSet)
          throws SQLException {
        assertEquals("11.00", resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME).toString());
        assertEquals("32", resultSet.getObject(MY_INT_COLUMN_NAME).toString());
    }


    @Test
    public void test_setObject_nonTypedValue_noSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC, Types.NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT, Types.BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();
        ResultSet resultSet = executeSelectAllQuery();
        assertTrue(resultSet.next());

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_nonTypedValue_noSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_nonTypedValue_noSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_nonTypedValue_noSeconds(PreparedStatement pstmt,
                                                                 ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_nonTypedValue_noSeconds(PreparedStatement pstmt,
                                                                ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertEquals("11.00", resultSet.getBigDecimal(1).toString());
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(3));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(4));
        assertEquals(true, resultSet.getBoolean(5));
        assertEquals(32, resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_nonTypedValue_milliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC, Types.NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_MILLIS, Types.TIMESTAMP);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT, Types.BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();
        assertTrue(resultSet.next());

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_nonTypedValue_milliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_nonTypedValue_milliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    protected void wrapperAssert_setObject_nonTypedValue_milliSeconds(PreparedStatement pstmt,
                                                                      ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    protected void sybaseAssert_setObject_nonTypedValue_milliSeconds(PreparedStatement pstmt,
                                                                     ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, "2009-01-01 15:23:24.233", Types.TIMESTAMP); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertEquals("11.00", resultSet.getBigDecimal(1).toString());
        assertEquals(MY_VARCHAR, resultSet.getString(2));

        // todo : à mettre dans test resulset
        Timestamp resultTimestamp = resultSet.getTimestamp(3);
        assertTrue(resultTimestamp instanceof SybTimestamp);
        // todo
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultTimestamp.getTime()));
        assertEquals(MY_DATETIME_MILLIS,
                     new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(true, resultSet.getBoolean(5));
        assertEquals(32, resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_nonTypedValue_nanoSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC, Types.NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC, Types.TIMESTAMP);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS, Types.TIMESTAMP);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT, Types.BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_nonTypedValue_nanoSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_nonTypedValue_nanoSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_nonTypedValue_nanoSeconds(PreparedStatement pstmt,
                                                                   ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
//        Todo est-ce normal de faire new Timestamp
        assertEquals(MY_DATETIME_NO_SEC,
                     new Timestamp(resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME).getTime()));
        assertEquals(MY_DATETIME_MILLIS,
                     new Timestamp(resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_nonTypedValue_nanoSeconds(PreparedStatement pstmt,
                                                                  ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, "2009-01-01 15:23:24.2333", Types.TIMESTAMP); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals("11.00", resultSet.getBigDecimal(1).toString());
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC,
                     new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(Timestamp.valueOf("2009-01-01 15:23:24.233"),
                     new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(true, resultSet.getBoolean(5));
        assertEquals(32, resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_nonTypedValue_nanoAndMilliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC, Types.NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR, Types.VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_MILLIS, Types.TIMESTAMP);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS, Types.TIMESTAMP);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT, Types.BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT, Types.INTEGER);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_nonTypedValue_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_nonTypedValue_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_nonTypedValue_nanoAndMilliSeconds(PreparedStatement pstmt,
                                                                           ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_nonTypedValue_nanoAndMilliSeconds(PreparedStatement pstmt,
                                                                          ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, "2009-01-01 15:23:24.2333", Types.TIMESTAMP);
        // et pstmt.setObject(3, "2009-01-01 15:23:24.233", Types.TIMESTAMP); déclenchent un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals("11.00", resultSet.getBigDecimal(1).toString());
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_MILLIS,
                     new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS,
                     new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(true, resultSet.getBoolean(5));
        assertEquals(32, resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_WithoutSqlType_noSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_WithoutSqlType_noSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_WithoutSqlType_noSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_WithoutSqlType_noSeconds(PreparedStatement pstmt,
                                                                  ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_WithoutSqlType_noSeconds(PreparedStatement pstmt,
                                                                 ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_WithoutSqlType_milliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_MILLIS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_WithoutSqlType_milliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_WithoutSqlType_milliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_WithoutSqlType_milliSeconds(PreparedStatement pstmt,
                                                                     ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_WithoutSqlType_milliSeconds(PreparedStatement pstmt,
                                                                    ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, MY_DATETIME_MILLIS); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_WithoutSqlType_nanoSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_WithoutSqlType_nanoSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_WithoutSqlType_nanoSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_WithoutSqlType_nanoSeconds(PreparedStatement pstmt,
                                                                    ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_WithoutSqlType_nanoSeconds(PreparedStatement pstmt,
                                                                   ResultSet resultSet) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, MY_DATETIME_NANOS); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setObject_WithoutSqlType_nanoAndMilliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setObject(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_MILLIS);
        pstmt.setObject(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.executeUpdate();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setObject_WithoutSqlType_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setObject_WithoutSqlType_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setObject_WithoutSqlType_nanoAndMilliSeconds(PreparedStatement pstmt,
                                                                            ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setObject_WithoutSqlType_nanoAndMilliSeconds(PreparedStatement pstmt,
                                                                           ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setObject(4, MY_DATETIME_NANOS); et pstmt.setObject(3, MY_DATETIME_MILLIS); déclenchent un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_nullValue() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, null);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, null);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_nullValue(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_nullValue(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_nullValue(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(Timestamp.valueOf("1970-01-01 00:00:00"),
                     resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(Timestamp.valueOf("1970-01-01 00:00:00"),
                     resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setTimestamp_nullValue(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(null, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(null, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setDate_nullValue() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setDate(MY_DATETIME_COLUMN_INDEX, null);
        pstmt.setDate(MY_DATETIME_SEC_COLUMN_INDEX, null);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setDate_nullValue(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setDate_nullValue(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setDate_nullValue(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals("1970-01-01 00:00:00.0",
                     resultSet.getObject(MY_DATETIME_COLUMN_NAME).toString());
        assertEquals("1970-01-01 00:00:00.0",
                     resultSet.getObject(MY_DATETIME_SEC_COLUMN_NAME).toString());
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setDate_nullValue(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertNull(resultSet.getObject(MY_DATETIME_COLUMN_NAME));
        assertNull(resultSet.getObject(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_noSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_noSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_noSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_noSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setTimestamp_noSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_milliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_MILLIS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_milliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_milliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_milliSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setTimestamp_milliSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setTimestamp(4, MY_DATETIME_MILLIS); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_nanoSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_NO_SEC);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_nanoSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_nanoSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_nanoSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_NO_SEC, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setTimestamp_nanoSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setTimestamp(4, MY_DATETIME_NANOS); déclenche un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_NO_SEC, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_nanoAndMilliSeconds() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, MY_NUMERIC);
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_MILLIS);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        ResultSet resultSet = executeSelectAllQuery();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_nanoAndMilliSeconds(pstmt, resultSet);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_nanoAndMilliSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        assertWarningDoesntExist(pstmt.getWarnings());
        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(MY_NUMERIC_COLUMN_NAME));
        assertEquals(MY_VARCHAR, resultSet.getString(MY_VARCHAR_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_COLUMN_NAME));
        assertEquals(MY_DATETIME_MILLIS, resultSet.getTimestamp(MY_DATETIME_SEC_COLUMN_NAME));
        assertEquals(MY_BIT, resultSet.getBoolean(MY_BIT_COLUMN_NAME));
        assertEquals(MY_INT.intValue(), resultSet.getInt(MY_INT_COLUMN_NAME));
        assertFalse(resultSet.next());
    }


    private void sybaseAssert_setTimestamp_nanoAndMilliSeconds(PreparedStatement pstmt, ResultSet resultSet)
          throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());
        // pstmt.setTimestamp(3, MY_DATETIME_MILLIS); et
        // pstmt.setTimestamp(4, MY_DATETIME_NANOS); déclenchent un warning
        assertOnlyTruncateWarningExists(pstmt.getWarnings());

        assertWarningDoesntExist(resultSet.getWarnings());

        assertTrue(resultSet.next());
        assertEquals(MY_NUMERIC, resultSet.getBigDecimal(1));
        assertEquals(MY_VARCHAR, resultSet.getString(2));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(3).getTime()));
        assertEquals(MY_DATETIME_MILLIS, new Timestamp(resultSet.getTimestamp(4).getTime()));
        assertEquals(MY_BIT, resultSet.getBoolean(5));
        assertEquals(MY_INT.intValue(), resultSet.getInt(6));
        assertFalse(resultSet.next());
    }


    @Test
    public void test_setTimestamp_otherWarnings() throws Exception {
        PreparedStatement pstmt = createStatement();
        pstmt.setObject(MY_NUMERIC_COLUMN_INDEX, new BigDecimal("45.233"));
        pstmt.setObject(MY_VARCHAR_COLUMN_INDEX, MY_VARCHAR);
        pstmt.setTimestamp(MY_DATETIME_COLUMN_INDEX, MY_DATETIME_MILLIS);
        pstmt.setTimestamp(MY_DATETIME_SEC_COLUMN_INDEX, MY_DATETIME_NANOS);
        pstmt.setObject(MY_BIT_COLUMN_INDEX, MY_BIT);
        pstmt.setObject(MY_INT_COLUMN_INDEX, MY_INT);
        pstmt.execute();

        if (isWrapperTestClass()) {
            wrapperAssert_setTimestamp_otherWarnings(pstmt);
        }
        else if (isSybaseTestClass()) {
            sybaseAssert_setTimestamp_otherWarnings(pstmt);
        }
        else {
            throw new RuntimeException("isWrapperTestClass() or isSybaseTestClass() must be overriden.");
        }
    }


    private void wrapperAssert_setTimestamp_otherWarnings(PreparedStatement pstmt) throws SQLException {
        assertWarningDoesntExist(connection.getWarnings());

        SQLWarning warning = pstmt.getWarnings();
        assertNotNull("Pas de warning", warning);
        if (!"Une erreur de truncature s'est produite.".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        }

        warning = warning.getNextWarning();
        assertNotNull("Pas de 2ième warning", warning);
        assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        if (!"La commande a été abandonnée.".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        }
    }


    private void sybaseAssert_setTimestamp_otherWarnings(PreparedStatement pstmt)
          throws SQLException {

        assertWarningDoesntExist(connection.getWarnings());

        SQLWarning warning = pstmt.getWarnings();
        assertNotNull("Pas de warning", warning);
        if (!"01S07: Adaptive Server may round or truncate nanosecond values".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01S07", warning.getSQLState());
        }

        warning = warning.getNextWarning();
        assertNotNull("Pas de 2ième warning", warning);
        if (!"01S07: Adaptive Server may round or truncate nanosecond values".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01S07", warning.getSQLState());
        }

        warning = warning.getNextWarning();
        assertNotNull("Pas de 3ième warning", warning);
        if (!"Une erreur de truncature s'est produite.".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        }

        warning = warning.getNextWarning();
        assertNotNull("Pas de 4ième warning", warning);
        assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        if (!"La commande a été abandonnée.".equals(warning.getMessage())) {
            assertEquals("Mauvais code erreur pour le warning", "01ZZZ", warning.getSQLState());
        }
    }


    protected <T extends PreparedStatement> T createStatement() throws SQLException, IOException {
        String insertQuery =
              "INSERT INTO " + AbstractTestCase.TABLE_NAME
              + " (MY_NUMERIC, MY_VARCHAR, MY_DATETIME, MY_DATETIME_SEC, MY_BIT, MY_INT)"
              + " values (?, ?, ?, ?, ?, ?)";
        return (T)connection.prepareStatement(insertQuery);
    }


    protected ResultSet executeSelectAllQuery() throws SQLException {
        return connection.createStatement().executeQuery("select * from " + AbstractTestCase.TABLE_NAME);
    }


    protected void assertOnlyTruncateWarningExists(SQLWarning warning) {
        assertTrue("Pas de warning", warning != null);
        while (warning != null) {
            if (!"01S07: Adaptive Server may round or truncate nanosecond values".equals(warning.getMessage())) {
                assertEquals("Mauvais code erreur pour le warning", "01S07", warning.getSQLState());
            }
            warning = warning.getNextWarning();
        }
    }


    protected void assertNoTruncateNanosecondWarningExists(SQLWarning warning) {
        while (warning != null) {
            if ("01S07: Adaptive Server may round or truncate nanosecond values".equals(warning.getMessage())) {
                fail("Truncate nanosecond warning exists");
            }
            warning = warning.getNextWarning();
        }
    }


    protected void assertWarningDoesntExist(SQLWarning warning) {
        assertNull("Il existe un warning : " + (warning == null ? "" : warning.getMessage()), warning);
    }
}