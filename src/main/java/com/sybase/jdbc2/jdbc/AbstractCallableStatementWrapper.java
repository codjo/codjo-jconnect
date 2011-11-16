package com.sybase.jdbc2.jdbc;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
/**
 *
 */
@SuppressWarnings({"OverlyComplexClass"})
public class AbstractCallableStatementWrapper implements CallableStatement {
    private CallableStatement callableStatement;


    public AbstractCallableStatementWrapper(CallableStatement callableStatement) {
        this.callableStatement = callableStatement;
    }


    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        callableStatement.registerOutParameter(parameterIndex, sqlType);
    }


    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        callableStatement.registerOutParameter(parameterIndex, sqlType, scale);
    }


    public boolean wasNull() throws SQLException {
        return callableStatement.wasNull();
    }


    public String getString(int parameterIndex) throws SQLException {
        return callableStatement.getString(parameterIndex);
    }


    public boolean getBoolean(int parameterIndex) throws SQLException {
        return callableStatement.getBoolean(parameterIndex);
    }


    public byte getByte(int parameterIndex) throws SQLException {
        return callableStatement.getByte(parameterIndex);
    }


    public short getShort(int parameterIndex) throws SQLException {
        return callableStatement.getShort(parameterIndex);
    }


    public int getInt(int parameterIndex) throws SQLException {
        return callableStatement.getInt(parameterIndex);
    }


    public long getLong(int parameterIndex) throws SQLException {
        return callableStatement.getLong(parameterIndex);
    }


    public float getFloat(int parameterIndex) throws SQLException {
        return callableStatement.getFloat(parameterIndex);
    }


    public double getDouble(int parameterIndex) throws SQLException {
        return callableStatement.getDouble(parameterIndex);
    }


    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        //noinspection deprecation
        return callableStatement.getBigDecimal(parameterIndex, scale);
    }


    public byte[] getBytes(int parameterIndex) throws SQLException {
        return callableStatement.getBytes(parameterIndex);
    }


    public Date getDate(int parameterIndex) throws SQLException {
        return callableStatement.getDate(parameterIndex);
    }


    public Time getTime(int parameterIndex) throws SQLException {
        return callableStatement.getTime(parameterIndex);
    }


    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return callableStatement.getTimestamp(parameterIndex);
    }


    public Object getObject(int parameterIndex) throws SQLException {
        return callableStatement.getObject(parameterIndex);
    }


    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return callableStatement.getBigDecimal(parameterIndex);
    }


    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        return callableStatement.getObject(i, map);
    }


    public Ref getRef(int i) throws SQLException {
        return callableStatement.getRef(i);
    }


    public Blob getBlob(int i) throws SQLException {
        return callableStatement.getBlob(i);
    }


    public Clob getClob(int i) throws SQLException {
        return callableStatement.getClob(i);
    }


    public Array getArray(int i) throws SQLException {
        return callableStatement.getArray(i);
    }


    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return callableStatement.getDate(parameterIndex, cal);
    }


    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return callableStatement.getTime(parameterIndex, cal);
    }


    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return callableStatement.getTimestamp(parameterIndex, cal);
    }


    public void registerOutParameter(int paramIndex, int sqlType, String typeName) throws SQLException {
        callableStatement.registerOutParameter(paramIndex, sqlType, typeName);
    }


    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        callableStatement.registerOutParameter(parameterName, sqlType);
    }


    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        callableStatement.registerOutParameter(parameterName, sqlType, scale);
    }


    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        callableStatement.registerOutParameter(parameterName, sqlType, typeName);
    }


    public URL getURL(int parameterIndex) throws SQLException {
        return callableStatement.getURL(parameterIndex);
    }


    public void setURL(String parameterName, URL val) throws SQLException {
        callableStatement.setURL(parameterName, val);
    }


    public void setNull(String parameterName, int sqlType) throws SQLException {
        callableStatement.setNull(parameterName, sqlType);
    }


    public void setBoolean(String parameterName, boolean x) throws SQLException {
        callableStatement.setBoolean(parameterName, x);
    }


    public void setByte(String parameterName, byte x) throws SQLException {
        callableStatement.setByte(parameterName, x);
    }


    public void setShort(String parameterName, short x) throws SQLException {
        callableStatement.setShort(parameterName, x);
    }


    public void setInt(String parameterName, int x) throws SQLException {
        callableStatement.setInt(parameterName, x);
    }


    public void setLong(String parameterName, long x) throws SQLException {
        callableStatement.setLong(parameterName, x);
    }


    public void setFloat(String parameterName, float x) throws SQLException {
        callableStatement.setFloat(parameterName, x);
    }


    public void setDouble(String parameterName, double x) throws SQLException {
        callableStatement.setDouble(parameterName, x);
    }


    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        callableStatement.setBigDecimal(parameterName, x);
    }


    public void setString(String parameterName, String x) throws SQLException {
        callableStatement.setString(parameterName, x);
    }


    public void setBytes(String parameterName, byte[] x) throws SQLException {
        callableStatement.setBytes(parameterName, x);
    }


    public void setDate(String parameterName, Date x) throws SQLException {
        callableStatement.setDate(parameterName, x);
    }


    public void setTime(String parameterName, Time x) throws SQLException {
        callableStatement.setTime(parameterName, x);
    }


    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        callableStatement.setTimestamp(parameterName, x);
    }


    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        callableStatement.setAsciiStream(parameterName, x, length);
    }


    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        callableStatement.setBinaryStream(parameterName, x, length);
    }


    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        callableStatement.setObject(parameterName, x, targetSqlType, scale);
    }


    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        callableStatement.setObject(parameterName, x, targetSqlType);
    }


    public void setObject(String parameterName, Object x) throws SQLException {
        callableStatement.setObject(parameterName, x);
    }


    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        callableStatement.setCharacterStream(parameterName, reader, length);
    }


    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        callableStatement.setDate(parameterName, x, cal);
    }


    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        callableStatement.setTime(parameterName, x, cal);
    }


    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        callableStatement.setTimestamp(parameterName, x, cal);
    }


    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        callableStatement.setNull(parameterName, sqlType, typeName);
    }


    public String getString(String parameterName) throws SQLException {
        return callableStatement.getString(parameterName);
    }


    public boolean getBoolean(String parameterName) throws SQLException {
        return callableStatement.getBoolean(parameterName);
    }


    public byte getByte(String parameterName) throws SQLException {
        return callableStatement.getByte(parameterName);
    }


    public short getShort(String parameterName) throws SQLException {
        return callableStatement.getShort(parameterName);
    }


    public int getInt(String parameterName) throws SQLException {
        return callableStatement.getInt(parameterName);
    }


    public long getLong(String parameterName) throws SQLException {
        return callableStatement.getLong(parameterName);
    }


    public float getFloat(String parameterName) throws SQLException {
        return callableStatement.getFloat(parameterName);
    }


    public double getDouble(String parameterName) throws SQLException {
        return callableStatement.getDouble(parameterName);
    }


    public byte[] getBytes(String parameterName) throws SQLException {
        return callableStatement.getBytes(parameterName);
    }


    public Date getDate(String parameterName) throws SQLException {
        return callableStatement.getDate(parameterName);
    }


    public Time getTime(String parameterName) throws SQLException {
        return callableStatement.getTime(parameterName);
    }


    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return callableStatement.getTimestamp(parameterName);
    }


    public Object getObject(String parameterName) throws SQLException {
        return callableStatement.getObject(parameterName);
    }


    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return callableStatement.getBigDecimal(parameterName);
    }


    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return callableStatement.getObject(parameterName, map);
    }


    public Ref getRef(String parameterName) throws SQLException {
        return callableStatement.getRef(parameterName);
    }


    public Blob getBlob(String parameterName) throws SQLException {
        return callableStatement.getBlob(parameterName);
    }


    public Clob getClob(String parameterName) throws SQLException {
        return callableStatement.getClob(parameterName);
    }


    public Array getArray(String parameterName) throws SQLException {
        return callableStatement.getArray(parameterName);
    }


    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return callableStatement.getDate(parameterName, cal);
    }


    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return callableStatement.getTime(parameterName, cal);
    }


    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return callableStatement.getTimestamp(parameterName, cal);
    }


    public URL getURL(String parameterName) throws SQLException {
        return callableStatement.getURL(parameterName);
    }


    public ResultSet executeQuery() throws SQLException {
        return createResultSetWrapper(callableStatement.executeQuery());
    }


    public int executeUpdate() throws SQLException {
        return callableStatement.executeUpdate();
    }


    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        callableStatement.setNull(parameterIndex, sqlType);
    }


    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        callableStatement.setBoolean(parameterIndex, x);
    }


    public void setByte(int parameterIndex, byte x) throws SQLException {
        callableStatement.setByte(parameterIndex, x);
    }


    public void setShort(int parameterIndex, short x) throws SQLException {
        callableStatement.setShort(parameterIndex, x);
    }


    public void setInt(int parameterIndex, int x) throws SQLException {
        callableStatement.setInt(parameterIndex, x);
    }


    public void setLong(int parameterIndex, long x) throws SQLException {
        callableStatement.setLong(parameterIndex, x);
    }


    public void setFloat(int parameterIndex, float x) throws SQLException {
        callableStatement.setFloat(parameterIndex, x);
    }


    public void setDouble(int parameterIndex, double x) throws SQLException {
        callableStatement.setDouble(parameterIndex, x);
    }


    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        callableStatement.setBigDecimal(parameterIndex, x);
    }


    public void setString(int parameterIndex, String x) throws SQLException {
        callableStatement.setString(parameterIndex, x);
    }


    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        callableStatement.setBytes(parameterIndex, x);
    }


    public void setDate(int parameterIndex, Date x) throws SQLException {
        callableStatement.setDate(parameterIndex, x);
    }


    public void setTime(int parameterIndex, Time x) throws SQLException {
        callableStatement.setTime(parameterIndex, x);
    }


    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        callableStatement.setTimestamp(parameterIndex, x);
    }


    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        callableStatement.setAsciiStream(parameterIndex, x, length);
    }


    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        //noinspection deprecation
        callableStatement.setUnicodeStream(parameterIndex, x, length);
    }


    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        callableStatement.setBinaryStream(parameterIndex, x, length);
    }


    public void clearParameters() throws SQLException {
        callableStatement.clearParameters();
    }


    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        callableStatement.setObject(parameterIndex, x, targetSqlType, scale);
    }


    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        callableStatement.setObject(parameterIndex, x, targetSqlType);
    }


    public void setObject(int parameterIndex, Object x) throws SQLException {
        callableStatement.setObject(parameterIndex, x);
    }


    public boolean execute() throws SQLException {
        return callableStatement.execute();
    }


    public void addBatch() throws SQLException {
        callableStatement.addBatch();
    }


    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        callableStatement.setCharacterStream(parameterIndex, reader, length);
    }


    public void setRef(int i, Ref x) throws SQLException {
        callableStatement.setRef(i, x);
    }


    public void setBlob(int i, Blob x) throws SQLException {
        callableStatement.setBlob(i, x);
    }


    public void setClob(int i, Clob x) throws SQLException {
        callableStatement.setClob(i, x);
    }


    public void setArray(int i, Array x) throws SQLException {
        callableStatement.setArray(i, x);
    }


    public ResultSetMetaData getMetaData() throws SQLException {
        return callableStatement.getMetaData();
    }


    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        callableStatement.setDate(parameterIndex, x, cal);
    }


    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        callableStatement.setTime(parameterIndex, x, cal);
    }


    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        callableStatement.setTimestamp(parameterIndex, x, cal);
    }


    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        callableStatement.setNull(paramIndex, sqlType, typeName);
    }


    public void setURL(int parameterIndex, URL x) throws SQLException {
        callableStatement.setURL(parameterIndex, x);
    }


    public ParameterMetaData getParameterMetaData() throws SQLException {
        return callableStatement.getParameterMetaData();
    }


    public ResultSet executeQuery(String sql) throws SQLException {
        return createResultSetWrapper(callableStatement.executeQuery(sql));
    }


    public int executeUpdate(String sql) throws SQLException {
        return callableStatement.executeUpdate(sql);
    }


    public void close() throws SQLException {
        callableStatement.close();
    }


    public int getMaxFieldSize() throws SQLException {
        return callableStatement.getMaxFieldSize();
    }


    public void setMaxFieldSize(int max) throws SQLException {
        callableStatement.setMaxFieldSize(max);
    }


    public int getMaxRows() throws SQLException {
        return callableStatement.getMaxRows();
    }


    public void setMaxRows(int max) throws SQLException {
        callableStatement.setMaxRows(max);
    }


    public void setEscapeProcessing(boolean enable) throws SQLException {
        callableStatement.setEscapeProcessing(enable);
    }


    public int getQueryTimeout() throws SQLException {
        return callableStatement.getQueryTimeout();
    }


    public void setQueryTimeout(int seconds) throws SQLException {
        callableStatement.setQueryTimeout(seconds);
    }


    public void cancel() throws SQLException {
        callableStatement.cancel();
    }


    public SQLWarning getWarnings() throws SQLException {
        return callableStatement.getWarnings();
    }


    public void clearWarnings() throws SQLException {
        callableStatement.clearWarnings();
    }


    public void setCursorName(String name) throws SQLException {
        callableStatement.setCursorName(name);
    }


    public boolean execute(String sql) throws SQLException {
        return callableStatement.execute(sql);
    }


    public ResultSet getResultSet() throws SQLException {
        return createResultSetWrapper(callableStatement.getResultSet());
    }


    public int getUpdateCount() throws SQLException {
        return callableStatement.getUpdateCount();
    }


    public boolean getMoreResults() throws SQLException {
        return callableStatement.getMoreResults();
    }


    public void setFetchDirection(int direction) throws SQLException {
        callableStatement.setFetchDirection(direction);
    }


    public int getFetchDirection() throws SQLException {
        return callableStatement.getFetchDirection();
    }


    public void setFetchSize(int rows) throws SQLException {
        callableStatement.setFetchSize(rows);
    }


    public int getFetchSize() throws SQLException {
        return callableStatement.getFetchSize();
    }


    public int getResultSetConcurrency() throws SQLException {
        return callableStatement.getResultSetConcurrency();
    }


    public int getResultSetType() throws SQLException {
        return callableStatement.getResultSetType();
    }


    public void addBatch(String sql) throws SQLException {
        callableStatement.addBatch(sql);
    }


    public void clearBatch() throws SQLException {
        callableStatement.clearBatch();
    }


    public int[] executeBatch() throws SQLException {
        return callableStatement.executeBatch();
    }


    public Connection getConnection() throws SQLException {
        return callableStatement.getConnection();
    }


    public boolean getMoreResults(int current) throws SQLException {
        return callableStatement.getMoreResults(current);
    }


    public ResultSet getGeneratedKeys() throws SQLException {
        return createResultSetWrapper(callableStatement.getGeneratedKeys());
    }


    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return callableStatement.executeUpdate(sql, autoGeneratedKeys);
    }


    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return callableStatement.executeUpdate(sql, columnIndexes);
    }


    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return callableStatement.executeUpdate(sql, columnNames);
    }


    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return callableStatement.execute(sql, autoGeneratedKeys);
    }


    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return callableStatement.execute(sql, columnIndexes);
    }


    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return callableStatement.execute(sql, columnNames);
    }


    public int getResultSetHoldability() throws SQLException {
        return callableStatement.getResultSetHoldability();
    }


    private ResultSet createResultSetWrapper(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return new ResultSetWrapper(resultSet);
    }
}
