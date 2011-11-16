package com.sybase.jdbc2.jdbc;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 */
public class AbstractResultSetMetaDataWrapper implements ResultSetMetaData {
    private ResultSetMetaData metaData;


    AbstractResultSetMetaDataWrapper(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }


    public String getCatalogName(int column) throws SQLException {
        return metaData.getCatalogName(column);
    }


    public String getColumnClassName(int column) throws SQLException {
        return metaData.getColumnClassName(column);
    }


    public int getColumnCount() throws SQLException {
        return metaData.getColumnCount();
    }


    public int getColumnDisplaySize(int column) throws SQLException {
        return metaData.getColumnDisplaySize(column);
    }


    public String getColumnLabel(int column) throws SQLException {
        return metaData.getColumnLabel(column);
    }


    public String getColumnName(int column) throws SQLException {
        return metaData.getColumnName(column);
    }


    public int getColumnType(int column) throws SQLException {
        return metaData.getColumnType(column);
    }


    public String getColumnTypeName(int column) throws SQLException {
        return metaData.getColumnTypeName(column);
    }


    public int getPrecision(int column) throws SQLException {
        return metaData.getPrecision(column);
    }


    public int getScale(int column) throws SQLException {
        return metaData.getScale(column);
    }


    public String getSchemaName(int column) throws SQLException {
        return metaData.getSchemaName(column);
    }


    public String getTableName(int column) throws SQLException {
        return metaData.getTableName(column);
    }


    public boolean isAutoIncrement(int column) throws SQLException {
        return metaData.isAutoIncrement(column);
    }


    public boolean isCaseSensitive(int column) throws SQLException {
        return metaData.isCaseSensitive(column);
    }


    public boolean isCurrency(int column) throws SQLException {
        return metaData.isCurrency(column);
    }


    public boolean isDefinitelyWritable(int column) throws SQLException {
        return metaData.isDefinitelyWritable(column);
    }


    public int isNullable(int column) throws SQLException {
        return metaData.isNullable(column);
    }


    public boolean isReadOnly(int column) throws SQLException {
        return metaData.isReadOnly(column);
    }


    public boolean isSearchable(int column) throws SQLException {
        return metaData.isSearchable(column);
    }


    public boolean isSigned(int column) throws SQLException {
        return metaData.isSigned(column);
    }


    public boolean isWritable(int column) throws SQLException {
        return metaData.isWritable(column);
    }
}
