package com.xck.util;

public class NumUtil {

    public static int byteToInt(byte[] b){
        int result = b[0];
        for(int i=1; i<b.length; i++){
            result = (b[i] << 8) & result;
        }
        return result;
    }
}
