package com.sybase.jdbc2.jdbc;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
/**
 *
 */
public class DefaultCharsetConverterTest {
    private DefaultCharsetConverter converter;
    private static final String EURO = "€";
    private static final String WINDOWS_ENCODING = "windows-1252";
    private static final String BAD_ENCODING = "ISO-8859-11";


    @Before
    public void setUp() {
        converter = new DefaultCharsetConverter();
    }


    @Test
    public void test_encode() throws Exception {
        assertEquals(EURO, converter.toUnicode(EURO.getBytes(WINDOWS_ENCODING)));
    }


    @Test
    public void test_encode_KO() throws Exception {
        assertFalse(EURO.equals(converter.toUnicode(EURO.getBytes(BAD_ENCODING))));
    }


    @Test
    public void test_decode() throws Exception {
        assertEquals(EURO, new String(converter.fromUnicode(EURO), WINDOWS_ENCODING));
    }


    @Test
    public void test_decode_KO() throws Exception {
        assertFalse(EURO.equals(new String(converter.fromUnicode(EURO), BAD_ENCODING)));
    }
}
