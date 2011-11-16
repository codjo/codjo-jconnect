package com.sybase.jdbc2.jdbc;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
/**
 *
 */
public class AbstractPreparedStatementWrapper implements PreparedStatement {
    protected PreparedStatement preparedStatement;


    public AbstractPreparedStatementWrapper(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }


    public ResultSet executeQuery() throws SQLException {
        return createResultSetWrapper(preparedStatement.executeQuery());
    }


    public int executeUpdate() throws SQLException {
        return preparedStatement.executeUpdate();
    }


    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        preparedStatement.setNull(parameterIndex, sqlType);
    }


    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        preparedStatement.setBoolean(parameterIndex, x);
    }


    public void setByte(int parameterIndex, byte x) throws SQLException {
        preparedStatement.setByte(parameterIndex, x);
    }


    public void setShort(int parameterIndex, short x) throws SQLException {
        preparedStatement.setShort(parameterIndex, x);
    }


    public void setInt(int parameterIndex, int x) throws SQLException {
        preparedStatement.setInt(parameterIndex, x);
    }


    public void setLong(int parameterIndex, long x) throws SQLException {
        preparedStatement.setLong(parameterIndex, x);
    }


    public void setFloat(int parameterIndex, float x) throws SQLException {
        preparedStatement.setFloat(parameterIndex, x);
    }


    public void setDouble(int parameterIndex, double x) throws SQLException {
        preparedStatement.setDouble(parameterIndex, x);
    }


    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        preparedStatement.setBigDecimal(parameterIndex, x);
    }


    public void setString(int parameterIndex, String x) throws SQLException {
        preparedStatement.setString(parameterIndex, x);
    }


    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        preparedStatement.setBytes(parameterIndex, x);
    }


    public void setDate(int parameterIndex, Date x) throws SQLException {
        preparedStatement.setDate(parameterIndex, x);
    }


    public void setTime(int parameterIndex, Time x) throws SQLException {
        preparedStatement.setTime(parameterIndex, x);
    }


    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        preparedStatement.setTimestamp(parameterIndex, x);
    }


    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        preparedStatement.setAsciiStream(parameterIndex, x, length);
    }


    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        //noinspection deprecation
        preparedStatement.setUnicodeStream(parameterIndex, x, length);
    }


    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        preparedStatement.setBinaryStream(parameterIndex, x, length);
    }


    public void clearParameters() throws SQLException {
        preparedStatement.clearParameters();
    }


    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        preparedStatement.setObject(parameterIndex, x, targetSqlType, scale);
    }


    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        preparedStatement.setObject(parameterIndex, x, targetSqlType);
    }


    public void setObject(int parameterIndex, Object x) throws SQLException {
        preparedStatement.setObject(parameterIndex, x);
    }


    public boolean execute() throws SQLException {
        return preparedStatement.execute();
    }


    public void addBatch() throws SQLException {
        preparedStatement.addBatch();
    }


    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        preparedStatement.setCharacterStream(parameterIndex, reader, length);
    }


    public void setRef(int i, Ref x) throws SQLException {
        preparedStatement.setRef(i, x);
    }


    public void setBlob(int i, Blob x) throws SQLException {
        preparedStatement.setBlob(i, x);
    }


    public void setClob(int i, Clob x) throws SQLException {
        preparedStatement.setClob(i, x);
    }


    public void setArray(int i, Array x) throws SQLException {
        preparedStatement.setArray(i, x);
    }


    public ResultSetMetaData getMetaData() throws SQLException {
        return preparedStatement.getMetaData();
    }


    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        preparedStatement.setDate(parameterIndex, x, cal);
    }


    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        preparedStatement.setTime(parameterIndex, x, cal);
    }


    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        preparedStatement.setTimestamp(parameterIndex, x, cal);
    }


    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        preparedStatement.setNull(paramIndex, sqlType, typeName);
    }


    public void setURL(int parameterIndex, URL x) throws SQLException {
        preparedStatement.setURL(parameterIndex, x);
    }


    public ParameterMetaData getParameterMetaData() throws SQLException {
        return preparedStatement.getParameterMetaData();
    }


    public ResultSet executeQuery(String sql) throws SQLException {
        return createResultSetWrapper(preparedStatement.executeQuery(sql));
    }


    public int executeUpdate(String sql) throws SQLException {
        return preparedStatement.executeUpdate(sql);
    }


    public void close() throws SQLException {
        preparedStatement.close();
    }


    public int getMaxFieldSize() throws SQLException {
        return preparedStatement.getMaxFieldSize();
    }


    public void setMaxFieldSize(int max) throws SQLException {
        preparedStatement.setMaxFieldSize(max);
    }


    public int getMaxRows() throws SQLException {
        return preparedStatement.getMaxRows();
    }


    public void setMaxRows(int max) throws SQLException {
        preparedStatement.setMaxRows(max);
    }


    public void setEscapeProcessing(boolean enable) throws SQLException {
        preparedStatement.setEscapeProcessing(enable);
    }


    public int getQueryTimeout() throws SQLException {
        return preparedStatement.getQueryTimeout();
    }


    public void setQueryTimeout(int seconds) throws SQLException {
        preparedStatement.setQueryTimeout(seconds);
    }


    public void cancel() throws SQLException {
        preparedStatement.cancel();
    }


    public SQLWarning getWarnings() throws SQLException {
        return preparedStatement.getWarnings();
    }


    public void clearWarnings() throws SQLException {
        preparedStatement.clearWarnings();
    }


    public void setCursorName(String name) throws SQLException {
        preparedStatement.setCursorName(name);
    }


    public boolean execute(String sql) throws SQLException {
        return preparedStatement.execute(sql);
    }


    public ResultSet getResultSet() throws SQLException {
        return createResultSetWrapper(preparedStatement.getResultSet());
    }


    public int getUpdateCount() throws SQLException {
        return preparedStatement.getUpdateCount();
    }


    public boolean getMoreResults() throws SQLException {
        return preparedStatement.getMoreResults();
    }


    public void setFetchDirection(int direction) throws SQLException {
        preparedStatement.setFetchDirection(direction);
    }


    public int getFetchDirection() throws SQLException {
        return preparedStatement.getFetchDirection();
    }


    public void setFetchSize(int rows) throws SQLException {
        preparedStatement.setFetchSize(rows);
    }


    public int getFetchSize() throws SQLException {
        return preparedStatement.getFetchSize();
    }


    public int getResultSetConcurrency() throws SQLException {
        return preparedStatement.getResultSetConcurrency();
    }


    public int getResultSetType() throws SQLException {
        return preparedStatement.getResultSetType();
    }


    public void addBatch(String sql) throws SQLException {
        preparedStatement.addBatch(sql);
    }


    public void clearBatch() throws SQLException {
        preparedStatement.clearBatch();
    }


    public int[] executeBatch() throws SQLException {
        return preparedStatement.executeBatch();
    }


    public Connection getConnection() throws SQLException {
        return preparedStatement.getConnection();
    }


    public boolean getMoreResults(int current) throws SQLException {
        return preparedStatement.getMoreResults(current);
    }


    public ResultSet getGeneratedKeys() throws SQLException {
        return createResultSetWrapper(preparedStatement.getGeneratedKeys());
    }


    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return preparedStatement.executeUpdate(sql, autoGeneratedKeys);
    }


    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return preparedStatement.executeUpdate(sql, columnIndexes);
    }


    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return preparedStatement.executeUpdate(sql, columnNames);
    }


    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return preparedStatement.execute(sql, autoGeneratedKeys);
    }


    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return preparedStatement.execute(sql, columnIndexes);
    }


    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return preparedStatement.execute(sql, columnNames);
    }


    public int getResultSetHoldability() throws SQLException {
        return preparedStatement.getResultSetHoldability();
    }


    private ResultSet createResultSetWrapper(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return new ResultSetWrapper(resultSet);
    }
}
