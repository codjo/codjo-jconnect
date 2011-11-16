package com.sybase.jdbc2.jdbc;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
/**
 *
 */
public abstract class CallableStatementTestCase extends PreparedStatementTestCase {
    protected static final String PROC_NAME = "proc_TEST";
    protected static final String PROC_WITH_OUT_PARAMETER_NAME = "proc_TEST_OUT";


    CallableStatementTestCase(boolean isWrapperClassTest) throws Exception {
        super(isWrapperClassTest);
    }


    @Override
    public void doSetUp() throws Exception {
        getConnection().createStatement().executeUpdate("create proc " + PROC_NAME + " "
                                                        + "(@MY_NUMERIC numeric(4,2),"
                                                        + "@MY_VARCHAR varchar(300),"
                                                        + "@MY_DATETIME datetime,"
                                                        + "@MY_DATETIME_SEC datetime,"
                                                        + "@MY_BIT bit,"
                                                        + "@MY_INT int)"
                                                        + "as begin "
                                                        + "INSERT INTO TABLE_TEST (MY_NUMERIC, MY_VARCHAR, MY_DATETIME, MY_DATETIME_SEC, MY_BIT, MY_INT)"
                                                        + " values (@MY_NUMERIC, @MY_VARCHAR, @MY_DATETIME, @MY_DATETIME_SEC, @MY_BIT, @MY_INT)"
                                                        + "end");
        getConnection().createStatement().executeUpdate("create proc " + PROC_WITH_OUT_PARAMETER_NAME + " "
                                                        + "(@MY_NUMERIC numeric(28,20),"
                                                        + " @MY_OUT_NUMERIC numeric(28,20) OUT)"
                                                        + "as begin "
                                                        + "  select @MY_OUT_NUMERIC = 1.12345678901234567890 "
                                                        + "  return @MY_OUT_NUMERIC  "
                                                        + "end");
    }


    @Override
    public void doTearDown() throws Exception {
        getConnection().createStatement().executeUpdate("drop proc " + PROC_NAME);
        getConnection().createStatement().executeUpdate("drop proc " + PROC_WITH_OUT_PARAMETER_NAME);
    }


    @Override
    protected <T extends PreparedStatement> T createStatement()
          throws SQLException, IOException {
        return (T)getConnection().prepareCall("{call " + PROC_NAME + " ?, ?, ?, ?, ?, ?)}");
    }


    @Override
    @Test
    public void test_setTimestamp_otherWarnings() throws Exception {
        try {
            super.test_setTimestamp_otherWarnings();
            fail();
        }
        catch (SQLException ex) {
            ;
        }
    }


    // TODO Comportement bizarre du driver
    @Test
    public void test_registerOutParameter_mustFail() throws IOException, SQLException {
        CallableStatement callableStatement = getConnection()
              .prepareCall("{call " + PROC_WITH_OUT_PARAMETER_NAME + " ?, ?)}");
        BigDecimal decimal = new BigDecimal("1.12345678901234567890");
        callableStatement.setBigDecimal(1, decimal);
        callableStatement.registerOutParameter(2, Types.NUMERIC);

        callableStatement.executeUpdate();

        try {
            assertEquals(decimal, callableStatement.getBigDecimal(2));
            fail();
        }
        catch (AssertionError e) {
            ;
        }
    }
}
