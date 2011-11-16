package com.sybase.jdbc2.jdbc;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

public class SybDriver implements Driver {
    private static final String URL_DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
    private static Driver driver = null;


    static {
        try {
            DriverManager.registerDriver(new SybDriver());
            driver = (Driver)Class.forName(URL_DRIVER).newInstance();
            DriverManager.deregisterDriver(driver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SybDriver() {
    }


    public Connection connect(String url, Properties info) throws SQLException {
        if (acceptsURL(url)) {
            info.put("CHARSET_CONVERTER_CLASS", "com.sybase.jdbc2.jdbc.DefaultCharsetConverter");
            return new ConnectionWrapper(driver.connect(url, info));
        }
        return null;
    }


    public boolean acceptsURL(String s) throws SQLException {
        return driver.acceptsURL(s);
    }


    public int getMajorVersion() {
        return driver.getMajorVersion();
    }


    public int getMinorVersion() {
        return driver.getMinorVersion();
    }


    public DriverPropertyInfo[] getPropertyInfo(String s, Properties properties) throws SQLException {
        return driver.getPropertyInfo(s, properties);
    }


    public boolean jdbcCompliant() {
        return driver.jdbcCompliant();
    }
}
