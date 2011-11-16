package com.sybase.jdbc2.jdbc;
import java.sql.ResultSetMetaData;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 */
public class ResultSetMetaDataWrapperTest extends ResultSetMetaDataTestCase {

    public ResultSetMetaDataWrapperTest() throws Exception {
        super(true);
    }


    @Test
    public void test_getColumnName() throws Exception {
        ResultSetMetaData data = getResultSetMetaDataForQuery(
              "select MY_NUMERIC as TOTO, MY_VARCHAR, TITI = MY_DATETIME from "
              + AbstractTestCase.TABLE_NAME);
        assertEquals("TOTO", data.getColumnName(1));
        assertEquals("MY_VARCHAR", data.getColumnName(2));
        assertEquals("TITI", data.getColumnName(3));
    }


    @Test
    public void test_getColumnName_tablaAlias() throws Exception {
        ResultSetMetaData data = getResultSetMetaDataForQuery(
              "select t.MY_NUMERIC as TOTO, t.MY_VARCHAR, TITI = t.MY_DATETIME from "
              + AbstractTestCase.TABLE_NAME + " t");
        assertEquals("TOTO", data.getColumnName(1));
        assertEquals("MY_VARCHAR", data.getColumnName(2));
        assertEquals("TITI", data.getColumnName(3));
    }
}
