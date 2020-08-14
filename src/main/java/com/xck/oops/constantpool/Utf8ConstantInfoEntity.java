package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Utf8_info {
 *  u1 tag;
 *  u2 length;
 *  u1 bytes[length];
 * }
 */
public class Utf8ConstantInfoEntity extends ConstantPoolEntity {

    private int length;
    private String value;

    public Utf8ConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.length = BytesUtil.byteToInt(b);
        b = new byte[this.length];
        is.read(b);
        this.value = new String(b, "utf-8");
    }

    @Override
    public String toString() {
        return "Utf8ConstantInfoEntity{" +
                "length=" + length +
                ", value='" + value + '\'' +
                '}';
    }
}
