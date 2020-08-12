package com.xck.oops.constantpool;

import java.io.IOException;
import java.io.InputStream;

public class ConstantPoolFactory {

    public static ConstantPool createConStantPoolFactory(int tag
            , InputStream is) throws IOException {

        switch (tag){
            case 1: return new Utf8ConstantInfo(is);
            case 2: return new IntegerConstantInfo(is);
        }
        return null;
    }
}
