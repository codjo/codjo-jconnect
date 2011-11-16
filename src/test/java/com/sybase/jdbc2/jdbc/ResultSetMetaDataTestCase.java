package com.sybase.jdbc2.jdbc;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
 *
 */
public abstract class ResultSetMetaDataTestCase extends AbstractTestCase {
    protected Connection connection;


    ResultSetMetaDataTestCase(boolean isWrapperClassTest) throws Exception {
        super(isWrapperClassTest);
    }

    //    @BeforeClass
//    public static void removeDriver() throws Exception {
//        while (DriverManager.getDrivers().hasMoreElements()) {
//            Driver driver = DriverManager.getDrivers().nextElement();
//            DriverManager.deregisterDriver(driver);
//        }
//    }


    @Before
    public void setUp() throws Exception {
//        while (DriverManager.getDrivers().hasMoreElements()) {
//            Driver driver = DriverManager.getDrivers().nextElement();
//            System.out.println("driver.getClass() = " + driver.getClass());
//        }
        connection = getConnection();
        AbstractTestCase.createTestTable(connection);
    }


    @After
    public void tearDown() throws SQLException {
        AbstractTestCase.dropTestTable(connection);
//        while (DriverManager.getDrivers().hasMoreElements()) {
//            Driver driver = DriverManager.getDrivers().nextElement();
//            DriverManager.deregisterDriver(driver);
//        }
    }


    @Test
    public void test_getColumnLabel() throws Exception {
        ResultSetMetaData data = getResultSetMetaDataForQuery(
              "select MY_NUMERIC as TOTO, MY_VARCHAR, TITI = MY_DATETIME from "
              + AbstractTestCase.TABLE_NAME);
        assertEquals("TOTO", data.getColumnLabel(1));
        assertEquals("MY_VARCHAR", data.getColumnLabel(2));
        assertEquals("TITI", data.getColumnLabel(3));
    }


    @Test
    public void test_getColumnLabel_tablaAlias() throws Exception {
        ResultSetMetaData data = getResultSetMetaDataForQuery(
              "select t.MY_NUMERIC as TOTO, t.MY_VARCHAR, TITI = t.MY_DATETIME from "
              + AbstractTestCase.TABLE_NAME
              + " t");
        assertEquals("TOTO", data.getColumnLabel(1));
        assertEquals("MY_VARCHAR", data.getColumnLabel(2));
        assertEquals("TITI", data.getColumnLabel(3));
    }


    protected ResultSetMetaData getResultSetMetaDataForQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query).getMetaData();
    }
}
