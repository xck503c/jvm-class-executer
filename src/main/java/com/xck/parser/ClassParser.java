package com.xck.parser;

import com.xck.oops.Klass;
import com.xck.oops.constantpool.ConstantPool;
import com.xck.oops.constantpool.ConstantPoolEntity;
import com.xck.oops.constantpool.ConstantPoolFactory;
import com.xck.util.BytesUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * class文件解析器
 */
public class ClassParser {
    //创建常量池项的工厂
    private ConstantPoolFactory factory = new ConstantPoolFactory();

    public void parseClassFile(String classPath) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(classPath));
        parseClassStream(inputStream);
    }

    private void parseClassStream(InputStream inputStream) throws IOException{
        Klass klass = new Klass();

        //读取魔数
        byte[] b = new byte[4];
        inputStream.read(b);
        klass.setMagicNumber(BytesUtil.bytesToHexString(b));
        System.out.println("魔数:" + klass.getMagicNumber());

        //读取版本号
        b = new byte[2];
        inputStream.read(b);
        klass.setMinorVersion(BytesUtil.byteToInt(b));
        System.out.println("次版本号:" + klass.getMinorVersion());

        b = new byte[2];
        inputStream.read(b);
        klass.setMajorVersion(BytesUtil.byteToInt(b));
        System.out.println("主版本号:" + klass.getMajorVersion());

        parseConstantPoolInClass(klass, inputStream);

        inputStream.close();
    }

    /**
     * 解析常量池信息
     * @param klass class对象
     * @param inputStream 数据流
     * @throws IOException
     */
    private void parseConstantPoolInClass(Klass klass, InputStream inputStream) throws IOException{
        byte[] b = new byte[2];
        inputStream.read(b);
        klass.setConstantPoolSize(BytesUtil.byteToInt(b));

        ConstantPool constantPool = new ConstantPool(klass.getConstantPoolSize());
        System.out.println("常量池的大小:" + constantPool.getConstantPoolSize());

        //从1开始解析，0位是保留的
        for(int i=1; i<constantPool.getConstantPoolSize(); i++){
            b = new byte[1];
            inputStream.read(b);
            int tag = BytesUtil.byteToInt(b);
            ConstantPoolEntity entity = factory.createConStantPoolFactory(BytesUtil.byteToInt(b), inputStream);
            entity.setTag(tag);
            constantPool.setPoolEntity(i, entity);
            System.out.println("第" + i + "个常量池项, 类型:" + tag + ", " + entity);
        }
        klass.setConstantPool(constantPool);
    }
}
