package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Float_info {
 *  u1 tag;
 *  u4 bytes;
 * }
 */
public class FloatConstantInfoEntity extends ConstantPoolEntity {

    private float value;

    public FloatConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[4];
        is.read(b);
        this.value = BytesUtil.byteToFloat(b);
    }

    @Override
    public String toString() {
        return "FloatConstantInfoEntity{" +
                "value=" + value +
                '}';
    }
}
