package com.xck.parser;

import com.xck.util.FileUtil;
import com.xck.util.NumUtil;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        String classPath = "/Users/xck/workDir/sourceCode/OpenJDK10/OpenJDK10/build/macosx-x86_64-normal-server-slowdebug/jdk/bin/YGCBeforeOrAfterCheckToFullGC.class";
        String mainClass = "";
        String jdkLibPath = "C:\\Program Files\\Java\\jdk1.7.0_79\\jre\\lib";

        //1.装载jdk的jar
//        List<String> jdkJarLibList = FileUtil.getSubFilesInDir(jdkLibPath, "jar");

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(classPath));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        //读取魔数
        byte[] b = new byte[4];
        inputStream.read(b);
        System.out.println("魔数:" + bytesToHexString(b));
        //读取版本号
        b = new byte[2];
        inputStream.read(b);
        System.out.println("次版本号:" + bytesToHexString(b));

        b = new byte[2];
        inputStream.read(b);
        System.out.println("主版本号:" + bytesToHexString(b));

        b = new byte[2];
        inputStream.read(b);
        System.out.println("常量池的大小:" + bytesToHexString(b));

        //
        b = new byte[1];
        inputStream.read(b);
        System.out.println("第一项常量标志:" + NumUtil.byteToInt(b)); //方法
        b = new byte[2];
        inputStream.read(b);
        System.out.println("第一项常量class索引:"+bytesToHexString(b));
        b = new byte[2];
        inputStream.read(b);
        System.out.println("第一项常量参数类型:" + bytesToHexString(b));

        inputStream.close();
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;

        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
        }

        return sb.toString();
    }
}
