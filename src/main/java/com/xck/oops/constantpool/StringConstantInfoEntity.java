package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_String_info {
 *  u1 tag;
 *  u2 string_index;
 * }
 */
public class StringConstantInfoEntity extends ConstantPoolEntity {

    private int stringIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型

    public StringConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.stringIndex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "StringConstantInfoEntity{" +
                "stringIndex=" + stringIndex +
                '}';
    }
}
