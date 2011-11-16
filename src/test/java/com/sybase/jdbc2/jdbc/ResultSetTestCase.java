package com.sybase.jdbc2.jdbc;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.Before;
/**
 *
 */
public abstract class ResultSetTestCase extends AbstractTestCase {
    protected Timestamp myDatetimeMillis = Timestamp.valueOf("2009-01-01 15:23:24.233");
    protected Timestamp myDatetimeNanos = Timestamp.valueOf("2009-01-01 15:23:24.2335");
    protected Timestamp myDatetimeNoSec = Timestamp.valueOf("2009-01-01 15:23:24");
    protected Date myDatetime = Date.valueOf("2009-01-01");
    protected BigDecimal myNumeric = new BigDecimal("11.11");
    protected String myVarchar
          = "a€aÈÉÊa!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    protected Boolean myBit = Boolean.valueOf("true");
    protected Integer myInt = Integer.valueOf("32");
    protected Connection connection;


    ResultSetTestCase(boolean isWrapperClassTest) throws Exception {
        super(isWrapperClassTest);
    }


    @Before
    public void setUp() throws Exception {
        connection = getConnection();
        AbstractTestCase.createTestTable(connection);
    }


    @After
    public void tearDown() throws SQLException {
        AbstractTestCase.dropTestTable(connection);
    }


    protected ResultSet excuteSelectAllQuery() throws SQLException {
        return connection.createStatement().executeQuery("select * from " + AbstractTestCase.TABLE_NAME);
    }


    protected PreparedStatement createInsertPreparedStatement() throws SQLException {
        String insertQuery =
              "INSERT INTO " + AbstractTestCase.TABLE_NAME
              + " (MY_NUMERIC, MY_VARCHAR, MY_DATETIME, MY_DATETIME_SEC, MY_BIT, MY_INT)"
              + " values (?, ?, ?, ?, ?, ?)";
        return connection.prepareStatement(insertQuery);
    }


    protected void createInsertStatement(String values) throws SQLException {
        String insertQuery =
              "INSERT INTO " + AbstractTestCase.TABLE_NAME +
              " (MY_NUMERIC, MY_VARCHAR, MY_DATETIME, MY_DATETIME_SEC, MY_BIT, MY_INT)"
              + " values (" + values + ")";
        connection.createStatement().executeUpdate(insertQuery);
    }
}