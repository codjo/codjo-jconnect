package com.sybase.jdbc2.jdbc;
import java.sql.Connection;
import java.sql.Statement;
import net.codjo.test.common.mock.ConnectionMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ConnectionWrapperTest {
    private ConnectionMock connectionMock;
    private Connection stub;
    private ConnectionWrapper wrapper;


    @Test
    public void test_connectionWrapper() throws Exception {
        stub.commit();
        assertEquals("commit()", connectionMock.callList());
    }


    @Test
    public void test_createStatement() throws Exception {
        Statement statement = wrapper.createStatement();
        assertTrue(statement instanceof StatementWrapper);
    }


    @Before
    public void setup() {
        connectionMock = new ConnectionMock();
        stub = connectionMock.getStub();
        wrapper = new ConnectionWrapper(stub);
        assertSame(stub, wrapper.getConnection());
    }
}
