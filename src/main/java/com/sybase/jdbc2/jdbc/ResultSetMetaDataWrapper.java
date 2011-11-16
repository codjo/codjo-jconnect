package com.sybase.jdbc2.jdbc;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 */
public class ResultSetMetaDataWrapper extends AbstractResultSetMetaDataWrapper {
    public ResultSetMetaDataWrapper(ResultSetMetaData metaData) {
        super(metaData);
    }


    @Override
    public String getColumnName(int column) throws SQLException {
        return getColumnLabel(column);
    }
}
