package com.sybase.jdbc2.jdbc;
import net.codjo.test.common.mock.ConnectionMock;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ConnectionWrapperTest {

    @Test
    public void test_connectionWrapper() throws Exception {
        ConnectionMock mock = new ConnectionMock();
        ConnectionWrapper wrapper = new ConnectionWrapper(mock);
        assertSame(mock, wrapper.getConnection());

        mock.commit();
        assertEquals("commit()", mock.callList());
    }


    @Test
    public void test_createStatement() throws Exception {
        ConnectionMock mock = new ConnectionMock();
        ConnectionWrapper wrapper = new ConnectionWrapper(mock);
        assertSame(mock, wrapper.getConnection());

        Statement statement = wrapper.createStatement();
        assertTrue(statement instanceof StatementWrapper);
    }
}
