package com.xck.oops.constantpool;

import com.xck.util.NumUtil;

import java.io.IOException;
import java.io.InputStream;

public class IntegerConstantInfo extends ConstantPool{

    private int value;

    public IntegerConstantInfo(InputStream is) throws IOException{
        byte[] b = new byte[4];
        is.read(b);
        this.value = NumUtil.byteToInt(b);
    }

    @Override
    public String toString(){
        return value+"";
    }
}
