package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Double_info {
 *  u1 tag;
 *  u4 high_bytes;
 *  u4 low_bytes;
 * }
 */
public class DoubleConstantInfoEntity extends ConstantPoolEntity {

    private double value;

    public DoubleConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[8];
        is.read(b);
        this.value = BytesUtil.byteToDouble(b);
    }

    @Override
    public String toString() {
        return "DoubleConstantInfoEntity{" +
                "value=" + value +
                '}';
    }
}
