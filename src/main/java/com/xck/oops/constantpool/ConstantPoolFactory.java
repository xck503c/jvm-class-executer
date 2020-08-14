package com.xck.oops.constantpool;

import java.io.IOException;
import java.io.InputStream;

public class ConstantPoolFactory {

    public ConstantPoolEntity createConStantPoolFactory(int tag
            , InputStream is) throws IOException {

        switch (tag){
            case 1: return new Utf8ConstantInfoEntity(is);
            case 3: return new IntegerConstantInfoEntity(is);
            case 4: return new FloatConstantInfoEntity(is);
            case 5: return new LongConstantInfoEntity(is);
            case 6: return new DoubleConstantInfoEntity(is);
            case 7: return new ClassConstantInfoEntity(is);
            case 8: return new StringConstantInfoEntity(is);
            case 9: return new FieldRefConstantInfoEntity(is);
            case 10: return new MethodRefConstantInfoEntity(is);
            case 11: return new IMethodRefConstantInfoEntity(is);
            case 12: return new NameAndTypeConstantInfoEntity(is);
            case 15: return new MethodHandleConstantInfoEntity(is);
            case 16: return new MethodTypeConstantInfoEntity(is);
            case 17: return new InvokeDynamicConstantInfoEntity(is);
            default:
                System.out.println("NOT FOUND TAG="+tag);
                throw new IllegalArgumentException();
        }
    }
}
