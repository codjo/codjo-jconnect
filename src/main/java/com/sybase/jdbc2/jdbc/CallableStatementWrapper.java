package com.sybase.jdbc2.jdbc;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.sql.Types;
/**
 *
 */
public class CallableStatementWrapper extends AbstractCallableStatementWrapper {
    private static final String ROUND_OR_TRUNCATE_WARNING_MESSAGE
          = "01S07: Adaptive Server may round or truncate nanosecond values";


    public CallableStatementWrapper(CallableStatement callableStatement) {
        super(callableStatement);
    }


    @Override
    public SQLWarning getWarnings() throws SQLException {
        return getFilteredWarnings(super.getWarnings());
    }


    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        if (null == x) {
            super.setObject(parameterIndex, "1970-01-01 00:00:00.0", Types.TIMESTAMP);
        }
        else {
            super.setTimestamp(parameterIndex, x);
        }
    }


    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        if (null == x) {
            super.setObject(parameterIndex, "1970-01-01 00:00:00.0", Types.DATE);
        }
        else {
            super.setDate(parameterIndex, x);
        }
    }


    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        if (targetSqlType == Types.NUMERIC && x != null) {
            if (x instanceof String) {
                super.setObject(parameterIndex, Double.parseDouble((String)x));
            }
            else {
                super.setObject(parameterIndex, x);
            }
        }
        else {
            super.setObject(parameterIndex, x, targetSqlType);
        }
    }


    private SQLWarning getFilteredWarnings(SQLWarning warning) {
        if (warning == null) {
            return null;
        }
        else {
            SQLWarning nextWarning = warning.getNextWarning();

            if (ROUND_OR_TRUNCATE_WARNING_MESSAGE.equals(warning.getMessage())) {
                return getFilteredWarnings(nextWarning);
            }
            else {
                SQLWarning newWarning = new SQLWarning(warning.getMessage(),
                                                       warning.getSQLState(),
                                                       warning.getErrorCode());
                if (nextWarning != null) {
                    newWarning.setNextWarning(getFilteredWarnings(nextWarning));
                }
                return newWarning;
            }
        }
    }
}
