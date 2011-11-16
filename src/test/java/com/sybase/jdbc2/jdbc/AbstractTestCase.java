package com.sybase.jdbc2.jdbc;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import junit.framework.Assert;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 */
public abstract class AbstractTestCase {
    private static final String PROPERTIES_FILE_NAME = AbstractTestCase.class.getSimpleName() + ".properties";
    private static final String URL = "jconnect.url";
    private static final String LOGIN = "jconnect.login";
    private static final String PASSWORD = "jconnect.password";
    private static final String CATALOG = "jconnect.catalog";
    protected static final String TABLE_NAME = "TABLE_TEST";
    private static final Properties connectionProperties = new Properties();
    private Connection connection;
    private boolean wrapperClassTest;


    AbstractTestCase(boolean isWrapperClassTest) throws Exception {
        wrapperClassTest = isWrapperClassTest;

        if (wrapperClassTest) {
            setupWrapperDriver();
        }
        else {
            setupSybaseDriver();
        }
    }


    @Test
    public void test_language_default() throws Exception {
        Statement statement = createConnection(new Properties()).createStatement();
        try {
            statement.executeQuery("select * from");
            Assert.fail();
        }
        catch (Exception e) {
            Assert.assertEquals("Syntaxe incorrecte près de 'from'.", e.getMessage());
        }
    }


    @Test
    public void test_language_english() throws Exception {
        Properties properties = new Properties();
        properties.put("LANGUAGE", "english");
        Statement statement = createConnection(properties).createStatement();
        try {
            statement.executeQuery("select * from");
            Assert.fail();
        }
        catch (Exception e) {
            Assert.assertEquals("Incorrect syntax near 'from'.\n", e.getMessage());
        }
    }


    public boolean isWrapperTestClass() {
        return wrapperClassTest;
    }


    public boolean isSybaseTestClass() {
        return !wrapperClassTest;
    }


    @BeforeClass
    public static void loadConnectionProperties() throws IOException {
        InputStream inputStream = AbstractTestCase.class.getResourceAsStream(PROPERTIES_FILE_NAME);
        try {
            connectionProperties.load(inputStream);
        }
        finally {
            inputStream.close();
        }
    }


    @After
    public void closeConnection() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                ;
            }
            finally {
                connection = null;
            }
        }
    }


    public void setupSybaseDriver()
          throws IllegalAccessException, InstantiationException, ClassNotFoundException,
                 NoSuchFieldException {
        resetDrivers();

        Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
    }


    public void setupWrapperDriver()
          throws IllegalAccessException, SQLException, NoSuchFieldException, InstantiationException {
        resetDrivers();

        DriverManager.registerDriver(new SybDriver());
    }


    public Connection getConnection() throws SQLException, IOException {
        if (connection == null) {
            connection = createConnection(new Properties());
        }
        return connection;
    }


    private Connection createConnection(Properties properties) throws SQLException {
        Connection newConnection;

        properties.put("user", connectionProperties.getProperty(LOGIN));
        properties.put("password", connectionProperties.getProperty(PASSWORD));

        if (!isWrapperTestClass()) {
            properties.put("CHARSET_CONVERTER_CLASS", "com.sybase.jdbc2.jdbc.DefaultCharsetConverter");
        }
        newConnection = DriverManager.getConnection(connectionProperties.getProperty(URL), properties);
        newConnection.setCatalog(connectionProperties.getProperty(CATALOG));

        return newConnection;
    }


    protected static void createTestTable(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate("create table "
                                                   + TABLE_NAME
                                                   + " (MY_NUMERIC numeric(4,2) null,"
                                                   + "MY_VARCHAR varchar(300) null,"
                                                   + "MY_DATETIME datetime null,"
                                                   + "MY_DATETIME_SEC datetime null,"
                                                   + "MY_BIT bit default 0 not null,"
                                                   + "MY_INT int null)");
    }


    protected static void dropTestTable(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate("drop table " + TABLE_NAME);
    }


    private void resetDrivers() throws NoSuchFieldException, IllegalAccessException {
        Field drivers = DriverManager.class.getDeclaredField("drivers");
        drivers.setAccessible(true);
        //noinspection UseOfObsoleteCollectionType
        drivers.set(DriverManager.class, new Vector());
        drivers.setAccessible(false);
    }
}
