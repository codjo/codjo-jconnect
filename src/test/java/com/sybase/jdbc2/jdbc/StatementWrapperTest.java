package com.sybase.jdbc2.jdbc;
import net.codjo.test.common.mock.CallableStatementMock;
import net.codjo.test.common.mock.StatementMock;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
/**
 *
 */
public class StatementWrapperTest {
    private static final String NOT_NULL_RESULTSET_ERROR_MESSAGE
          = "There is no ResultSet, the wrapper must return null";


    @Test
    public void test_statementWrapper() throws Exception {
        StatementMock mock = new StatementMock();
        StatementWrapper wrapper = new StatementWrapper(mock);

        wrapper.execute("request");

        assertEquals("execute(request)", mock.callList());
    }


    @Test
    public void test_getResultSet_mustReturnNull() throws Exception {
        Statement wrapper = createStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.getResultSet());
    }


    @Test
    public void test_executeQueryWithString_mustReturnNull() throws Exception {
        Statement wrapper = createStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.executeQuery("select * from AP_TOTO"));
    }


    @Test
    public void test_getGeneratedKeys_mustReturnNull() throws Exception {
        Statement wrapper = createStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.getGeneratedKeys());
    }


    private StatementWrapper createStatement(ResultSet resultSet) {
        CallableStatementMock mock = new CallableStatementMock();
        mock.mockResultSet(resultSet);
        return new StatementWrapper(mock);
    }
}
