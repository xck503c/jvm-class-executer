package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_Class_info {
 *  u1 tag;
 *  u2 name_index;
 * }
 * tag=7，用来表示class或者接口
 */
public class ClassConstantInfoEntity extends ConstantPoolEntity {

    private int nameIndex; //指向常量池的索引，指向的索引项是一个CONSTANT_Utf8_info类型

    private String className = null;

    public ClassConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.nameIndex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "ClassConstantInfoEntity{" +
                "nameIndex=" + nameIndex +
                ", className='" + className + '\'' +
                '}';
    }
}
