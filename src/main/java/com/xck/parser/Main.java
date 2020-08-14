package com.xck.parser;

public class Main {

    public static void main(String[] args) throws Exception{

//        String classPath = "/Users/xck/workDir/sourceCode/OpenJDK10/OpenJDK10/build/macosx-x86_64-normal-server-slowdebug/jdk/bin/YGCBeforeOrAfterCheckToFullGC.class";
        String classPath = "D:\\HelloWorld.class";
        String mainClass = "";
        String jdkLibPath = "C:\\Program Files\\Java\\jdk1.7.0_79\\jre\\lib";

        //1.装载jdk的jar
//        List<String> jdkJarLibList = FileUtil.getSubFilesInDir(jdkLibPath, "jar");

        ClassParser classParser = new ClassParser();
        classParser.parseClassFile(classPath);
    }
}
