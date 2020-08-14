package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * CONSTANT_MethodHandle_info {
 *  u1 tag;
 *  u1 reference_kind;
 *  u2 reference_index;
 * }
 */
public class MethodHandleConstantInfoEntity extends ConstantPoolEntity {

    /**
     * 1 - REF_getField
     * 2 - REF_getStatic
     * 3 - REF_putField
     * 4 - REF_putStatic
     * 5 - REF_invokeVirtual
     * 6 - REF_invokeStatic
     * 7 - REF_invokeSpecial
     * 8 - REF_newInvokeSpecial ==> 构造方法
     * 9 - REF_invokeInterface
     */
    private int refKind;
    /**
     * 1~4 - 指向CONSTANT_Fieldref_info
     * 5~8 - 指向 CONSTANT_Methodref_info
     * 9 - 指向CONSTANT_InterfaceMethodref_info
     */
    private int refIndex;

    public MethodHandleConstantInfoEntity(InputStream is) throws IOException{
        byte[] b = new byte[1];
        is.read(b);
        this.refKind = BytesUtil.byteToInt(b);

        b = new byte[2];
        is.read(b);
        this.refIndex = BytesUtil.byteToInt(b);
    }

    @Override
    public String toString() {
        return "MethodHandleConstantInfoEntity{" +
                "refKind=" + refKind +
                ", refIndex=" + refIndex +
                '}';
    }
}
