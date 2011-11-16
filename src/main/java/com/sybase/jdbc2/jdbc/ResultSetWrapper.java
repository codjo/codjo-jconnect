package com.sybase.jdbc2.jdbc;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultSetWrapper extends AbstractResultSetWrapper {
    public ResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }


    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new ResultSetMetaDataWrapper(super.getMetaData());
    }


    @Override
    public Object getObject(String columnName) throws SQLException {
        return getObjectType(super.getObject(columnName));
    }


    @Override
    public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
        return getObjectType(super.getObject(colName, map));
    }


    @Override
    public Object getObject(int columnIndex) throws SQLException {
        return getObjectType(super.getObject(columnIndex));
    }


    @Override
    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        return getObjectType(super.getObject(i, map));
    }


    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return getSqlTimestamp(super.getTimestamp(columnIndex));
    }


    @Override
    public Timestamp getTimestamp(String columnName) throws SQLException {
        return getSqlTimestamp(super.getTimestamp(columnName));
    }


    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return getSqlTimestamp(super.getTimestamp(columnIndex, cal));
    }


    @Override
    public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
        return getSqlTimestamp(super.getTimestamp(columnName, cal));
    }


    private Object getObjectType(Object object) {
        if (object instanceof com.sybase.jdbc3.tds.SybTimestamp) {
            return getSqlTimestamp(((Timestamp)object));
        }
        return object;
    }


    private Timestamp getSqlTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Timestamp(timestamp.getTime());
    }
}
