package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Integer_info {
 *  u1 tag;
 *  u4 bytes;
 * }
 */
public class IntegerConstantInfoEntity extends ConstantPoolEntity {

    private int value;

    public IntegerConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[4];
        is.read(b);
        this.value = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "IntegerConstantInfoEntity{" +
                "value=" + value +
                '}';
    }
}
