package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_InvokeDynamic_info {
 *  u1 tag;
 *  u2 bootstrap_method_attr_index;
 *  u2 name_and_type_index;
 * }
 */
public class InvokeDynamicConstantInfoEntity extends ConstantPoolEntity {

    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIdex;

    public InvokeDynamicConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.bootstrapMethodAttrIndex = BytesUtil.byteToInt(b);

        b = new byte[2];
        is.read(b);
        this.nameAndTypeIdex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "InvokeDynamicConstantInfoEntity{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIdex=" + nameAndTypeIdex +
                '}';
    }
}
