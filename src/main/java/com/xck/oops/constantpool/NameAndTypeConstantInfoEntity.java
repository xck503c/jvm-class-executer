package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_NameAndType_info {
 *  u1 tag;
 *  u2 name_index;
 *  u2 descriptor_index;
 * }
 * 用来表示字段或者方法
 */
public class NameAndTypeConstantInfoEntity extends ConstantPoolEntity {

    private int nameIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型
    private int descriptorIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型

    public NameAndTypeConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.nameIndex = BytesUtil.byteToInt(b);

        b = new byte[2];
        is.read(b);
        this.descriptorIndex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "NameAndTypeConstantInfoEntity{" +
                "nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                '}';
    }
}
