package com.xck.oops.constantpool;

import com.xck.util.NumUtil;

import java.io.IOException;
import java.io.InputStream;

public class Utf8ConstantInfo extends ConstantPool{

    private int length;
    private String str;

    public Utf8ConstantInfo(InputStream is) throws IOException{
        byte[] b = new byte[2];
        is.read(b);
        this.length = NumUtil.byteToInt(b);
        b = new byte[this.length];
        this.str = new String(b);
    }

    @Override
    public String toString(){
        return length + " " + str;
    }
}
