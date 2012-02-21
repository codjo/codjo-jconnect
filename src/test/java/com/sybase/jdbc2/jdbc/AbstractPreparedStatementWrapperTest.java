package com.sybase.jdbc2.jdbc;
import net.codjo.test.common.mock.CallableStatementMock;
import java.sql.ResultSet;
import static org.junit.Assert.assertNull;
import org.junit.Test;
/**
 *
 */
public class AbstractPreparedStatementWrapperTest {
    private static final String NOT_NULL_RESULTSET_ERROR_MESSAGE
          = "There is no ResultSet, the wrapper must return null";


    @Test
    public void test_executeQuery_mustReturnNull() throws Exception {
        AbstractPreparedStatementWrapper wrapper = createPrepareStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.executeQuery());
    }


    @Test
    public void test_executeQueryWithString_mustReturnNull() throws Exception {
        AbstractPreparedStatementWrapper wrapper = createPrepareStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.executeQuery("select * from AP_TOTO"));
    }


    @Test
    public void test_getResultSet_mustReturnNull() throws Exception {
        AbstractPreparedStatementWrapper wrapper = createPrepareStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.getResultSet());
    }


    @Test
    public void test_getGeneratedKeys_mustReturnNull() throws Exception {
        AbstractPreparedStatementWrapper wrapper = createPrepareStatement(null);

        assertNull(NOT_NULL_RESULTSET_ERROR_MESSAGE, wrapper.getGeneratedKeys());
    }


    private AbstractPreparedStatementWrapper createPrepareStatement(ResultSet resultSet) {
        CallableStatementMock mock = new CallableStatementMock();
        mock.mockResultSet(resultSet);
        return new AbstractPreparedStatementWrapper(mock.getStub());
    }
}
