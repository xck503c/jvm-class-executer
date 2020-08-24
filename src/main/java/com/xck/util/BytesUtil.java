package com.xck.util;

public class BytesUtil {

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

    /**
     * 将字节数组转换为int类型，因为int是4字节
     * 例如：16进制是:00 2c - 字节数组是:0 44
     * @param b
     * @return
     */
    public static int byteToInt(byte[] b){
        if(b.length ==0 || b.length>4){
            throw new IllegalArgumentException("b.length ==0 || b.length>4");
        }

        //先记录二进制做低位的字节
        int result = b[b.length-1];
        for(int i=1; i<b.length-1; i++){
            //先左移动，左移后右侧是补0，所以是或操作，将补位的0置为1，最后与操作进行叠加
            result = (b[i] << 8*(b.length-i)) | (0x0000000F << 8*(b.length-i)) & result;
        }
        return result;
    }

    public static long byteToLong(byte[] b){
        if(b.length == 0 || b.length > 8){
            throw new IllegalArgumentException("b.length ==0 || b.length>4");
        }

        long result = b[b.length-1];
        for(int i=1; i<b.length-1; i++){
            result = (b[i] << 8*(b.length-i)) | (0x0000000F << 8*(b.length-i)) & result;
        }
        return result;
    }

    /**
     * 将字节数组转换为float类型，因为float是4字节
     * @param b
     * @return
     */
    public static float byteToFloat(byte[] b){
        if(b.length ==0 || b.length>4){
            throw new IllegalArgumentException("b.length ==0 || b.length>4");
        }

        return Float.intBitsToFloat(byteToInt(b)); //只能用这个来表示了
    }

    public static double byteToDouble(byte[] b){
        if(b.length ==0 || b.length>4){
            throw new IllegalArgumentException("b.length ==0 || b.length>4");
        }

        return Double.longBitsToDouble(byteToLong(b)); //只能用这个来表示了
    }

    /**
     * 这里采用IEEE 754标准进行分解，用于验证class文件中存储的值
     *
     * 包含数符，尾数，阶码，尾数用补数，阶码用移码
     * @param f
     * @return
     */
    public static int floatToBit(float f){

        //1.确定数符
        int sign = 0; //数符
        if(f < 0) {
            f = -f;
            sign = 1;
        }

        String floatValue = (f+"");
        int index = floatValue.indexOf(".");

        //2.字符串分割，获取整数部分和小数部分
        String roudNumber = floatValue.substring(0, index);
        String decimal = floatValue.substring(index);

        //3.整数部分除2取余
        int tmpInt = Integer.parseInt(roudNumber);
        StringBuilder roudSb = new StringBuilder("");
        if (tmpInt > 0) {
            while (true){
                int bit = tmpInt%2;
                tmpInt = tmpInt/2;
                roudSb.append(bit);
                if(tmpInt == 0 || roudSb.length() == 23){
                    break;
                }
            }
            roudSb.reverse();
        }
        int e = roudSb.length()+127;

        //4.小数部分乘2取整
        StringBuilder decimalSb = new StringBuilder("");
        float tmpFloat = Float.parseFloat("0"+decimal);
        while (true){
            tmpFloat = tmpFloat*2;
            int bit = tmpFloat >= 1 ? 1 : 0;
            decimalSb.append(bit);
            if(tmpFloat >= 1.0f){
                tmpFloat = tmpFloat - 1;
            }
            if(decimalSb.length() == 23-roudSb.length()){
                break;
            }
        }

        //5.完整拼接，移位去除最高位1
        roudSb.append(decimalSb.toString());
        if (e >= 1 && e <= 254) {
            //IEEE 754 1<=e<=254, E=e-126
            for(int i=0; i<roudSb.length(); i++){
                e--;
                if(roudSb.charAt(i) == '0'){
                    roudSb.deleteCharAt(i);
                    roudSb.append('0');
                }else {
                    //
                    roudSb.deleteCharAt(i);
                    roudSb.append('1');
                    break;
                }
            }
        }else if(e == 255){
            //...
        } else {
            //IEEE 754 e=0, M!=0 E=-126
            e = -126;
        }

        int result = 0;
        for(int i=roudSb.length()-1; i>=0; i--){
            if(roudSb.charAt(i) == '1'){
                result = result | (1<<(roudSb.length()-i-1));
            }
        }

//        System.out.println("S=" + sign);
//        System.out.print("M=");
//        intToBits(result);
//        System.out.println("E=" + e);

        result = result | (e << 23);
        result = result | (sign << 31);


//        System.out.print("result=");
//        intToBits(result);
        return result;
    }

    public static void intToBits(int a){
        for(byte i=0; i<32; i++){
            System.out.print(((0x80000000>>>i) & a) >>> (31-i));
        }
        System.out.println();
    }
}
