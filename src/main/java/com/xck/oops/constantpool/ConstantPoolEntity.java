package com.xck.oops.constantpool;

/**
 * 抽象的常量池项
 */
public abstract class ConstantPoolEntity {
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
