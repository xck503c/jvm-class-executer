package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_MethodType_info {
 *  u1 tag;
 *  u2 descriptor_index;
 * }
 */
public class MethodTypeConstantInfoEntity extends ConstantPoolEntity {

    private int descIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型

    public MethodTypeConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.descIndex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "MethodTypeConstantInfoEntity{" +
                "descIndex=" + descIndex +
                '}';
    }
}
