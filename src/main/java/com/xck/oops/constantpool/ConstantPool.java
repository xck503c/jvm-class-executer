package com.xck.oops.constantpool;

import com.xck.util.BytesUtil;

/**
 * 常量池
 */
public class ConstantPool {
    private ConstantPoolEntity[] pool;

    public ConstantPool(int poolSize) {
        pool = new ConstantPoolEntity[poolSize];
    }

    public void setPoolEntity(int index, ConstantPoolEntity entity){
        pool[index] = entity;
    }

    public int getConstantPoolSize(){
        return pool.length;
    }
}
