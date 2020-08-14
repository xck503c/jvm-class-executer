package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 *CONSTANT_Long_info {
 *  u1 tag;
 *  u4 high_bytes;
 *  u4 low_bytes;
 * }
 */
public class LongConstantInfoEntity extends ConstantPoolEntity {

    private long value;

    public LongConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[8];
        is.read(b);
        this.value = BytesUtil.byteToLong(b);
    }

    @Override
    public String toString() {
        return "LongConstantInfoEntity{" +
                "value=" + value +
                '}';
    }
}
