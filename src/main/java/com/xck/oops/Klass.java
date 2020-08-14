package com.xck.oops;

import com.xck.oops.constantpool.ConstantPool;

/**
 * Class对象
 */
public class Klass {
    private String magicNumber; //魔数
    private int minorVersion; //次版本号
    private int majorVersion; //主版本号
    private int constantPoolSize; //常量池的大小
    private ConstantPool constantPool; //常量池

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolSize() {
        return constantPoolSize;
    }

    public void setConstantPoolSize(int constantPoolSize) {
        this.constantPoolSize = constantPoolSize;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
}
