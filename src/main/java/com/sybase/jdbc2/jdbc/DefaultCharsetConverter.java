package com.sybase.jdbc2.jdbc;
import com.sybase.jdbcx.CharsetConverter;
import java.io.CharConversionException;
import java.io.UnsupportedEncodingException;
/**
 *
 */
public class DefaultCharsetConverter implements CharsetConverter {
    public void setEncoding(String s) throws UnsupportedEncodingException {

    }


    public byte[] fromUnicode(String s) throws CharConversionException {
        return s.getBytes();
    }


    public String toUnicode(byte[] bytes) throws CharConversionException {
        return new String(bytes);
    }
}
