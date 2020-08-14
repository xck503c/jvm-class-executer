package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Methodref_info {
 *  u1 tag;
 *  u2 class_index;
 *  u2 name_and_type_index;
 * }
 */
public class MethodRefConstantInfoEntity extends ConstantPoolEntity {

    private int classIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型
    private int nameAndTypeIdex;

    public MethodRefConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.classIndex = BytesUtil.byteToInt(b);

        b = new byte[2];
        is.read(b);
        this.nameAndTypeIdex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "MethodRefConstantInfoEntity{" +
                "classIndex=" + classIndex +
                ", nameAndTypeIdex=" + nameAndTypeIdex +
                '}';
    }
}
