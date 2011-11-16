package com.sybase.jdbc2.jdbc;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SybDriverTest {

    @Test
    public void test_getMajorVersion() throws Exception {
        SybDriver sybDriver = new SybDriver();
        assertEquals(6, sybDriver.getMajorVersion());
    }


    @Test
    public void test_getMinorVersion() throws Exception {
        SybDriver sybDriver = new SybDriver();
        assertEquals(0, sybDriver.getMinorVersion());
    }
}
