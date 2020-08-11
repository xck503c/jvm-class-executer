package com.xck.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> getSubFilesInDir(String dirPath, String key){
        File dir = new File(dirPath);

        if(key == null){
            throw new IllegalArgumentException("搜索关键词为空");
        }
        if(!dir.isDirectory()){
            throw new IllegalArgumentException("搜索路径参数非法，不是目录");
        }

        List<String> filePathList = new ArrayList<String>();

        File[] fileArr = dir.listFiles();
        for(int i=0; i<fileArr.length; i++){
            if(fileArr[i].isFile() && fileArr[i].getName().contains(key)){
                filePathList.add(fileArr[i].getAbsolutePath());
            }else {
                getSubFilesInDir(fileArr[i], filePathList, key);
            }
        }
        return filePathList;
    }

    private static void getSubFilesInDir(File dir, List<String> filePathList, String key){
        File[] fileArr = dir.listFiles();
        if(fileArr == null) return;
        for(int i=0; i<fileArr.length; i++){
            if(fileArr[i].isFile() && fileArr[i].getName().contains(key)){
                filePathList.add(fileArr[i].getAbsolutePath());
            }else {
                getSubFilesInDir(fileArr[i], filePathList, key);
            }
        }
    }
}
