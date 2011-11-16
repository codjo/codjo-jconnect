package com.sybase.jdbc2.jdbc;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
/**
 *
 */
public class SybDataSource extends SybDriver implements DataSource {
    private static final String URL_DATA_SOURCE = "com.sybase.jdbc3.jdbc.SybDataSource";
    private static DataSource dataSource;


    static {
        try {
            dataSource = (DataSource)Class.forName(URL_DATA_SOURCE).newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SybDataSource() {
    }


    public Connection getConnection() throws SQLException {
        return new ConnectionWrapper(dataSource.getConnection());
    }


    public Connection getConnection(String username, String password) throws SQLException {
        return new ConnectionWrapper(dataSource.getConnection(username, password));
    }


    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }


    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }


    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }


    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }
}
