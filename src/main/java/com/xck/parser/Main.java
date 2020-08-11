package com.xck.parser;

import com.xck.util.FileUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        String classPath = "";
        String mainClass = "";
        String jdkLibPath = "C:\\Program Files\\Java\\jdk1.7.0_79\\jre\\lib";

        //1.装载jdk的jar
        List<String> jdkJarLibList = FileUtil.getSubFilesInDir(jdkLibPath, "jar");


    }
}
