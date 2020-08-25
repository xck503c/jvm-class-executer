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
     *
     * 需要可以动态扩展精度
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

        //先定位整数部分的阶
        int firstIndexOneInRound = roudSb.indexOf("1");
        // 101.xxx --- 1.01 --- 3-1=2
        int e = firstIndexOneInRound!=-1?roudSb.length()-1:0;

        //4.小数部分乘2取整
        StringBuilder decimalSb = new StringBuilder("");
        float tmpFloat = Float.parseFloat("0"+decimal);

        boolean isFirstOneInDecimal = false;
        int countZero = 0;
        if (tmpFloat > 0.0f) {
            while (true){
                tmpFloat = tmpFloat*2;
                int bit = tmpFloat >= 1 ? 1 : 0;

                if (e == 0) { //只有整数部分不存在，才可以进行精度再次扩展，否则，精度上限只能为1，也就是23+1
                    //0.0001 --- 相当于多了4个位的精度，感人
                    if (bit == 1) {
                        isFirstOneInDecimal = true;
                        countZero++;
                    }
                    if(!isFirstOneInDecimal && bit == 0) countZero++;
                }

                decimalSb.append(bit);
                if(tmpFloat >= 1.0f){
                    tmpFloat = tmpFloat - 1;
                }

                //动态增加精度
                if(tmpFloat == 0.0f || decimalSb.length() == 23-roudSb.length()+(e==0?0:1)+countZero){
                    break;
                }
            }
        }

        //再定位小数部分的阶
        if(e == 0){
            int firstIndexOneInDecimal = decimalSb.indexOf("1");
            //0.011 --- 1+1=2 --- 1.1xxx
            e = firstIndexOneInDecimal!=-1?-(firstIndexOneInDecimal+1):0;
        }
        e+=127;

        //5.完整拼接，移位去除最高位1
        roudSb.append(decimalSb.toString());
        if (e >= 1 && e <= 254) {
            //IEEE 754 1<=e<=254, E=e-126
            while (roudSb.length() > 23){ //因为最长存储只能23位，所以要删除前面多余的部分
                roudSb.deleteCharAt(0);
            }
        }else if(e == 255){
            //...
        } else {
            //IEEE 754 e=0, M!=0 E=-126
            e = -126;
        }

        //将字符转换为int类型
        int result = 0;
        for(int i=roudSb.length()-1; i>=0; i--){
            if(roudSb.charAt(i) == '1'){
                result = result | (1<<(roudSb.length()-i-1));
            }
        }

        System.out.println("S=" + sign);
        System.out.println("E=" + e);
        System.out.print("M=");
        intToBits(result);

        result = result | (e << 23);
        result = result | (sign << 31);


//        System.out.print("result=");
//        intToBits(result);
        return result;
    }

    public static float intToFloat(int floatInt){
        //1. 先分解
        int sign = floatInt >>> 31;
        //0111 1111 1000
        int e = (floatInt & (0x7f800000)) >>> 23;
        int m = floatInt & (0x007fffff);

//        System.out.println("S=" + sign);
//        System.out.println("E=" + e);
//        System.out.print("M=");
//        intToBits(m);

        if (e >= 1 && e <= 254) {
            e-=127;
            if(e < 0) { //说明无整数部分

            }else if(e == 1){ //说明存在整数部分

            }else{

            }
        }else if(e == 255){
            //...
        } else if(e == -126){
           e = 0;
        }


        return 0;
    }

    public static void intToBits(int a){
        for(byte i=0; i<32; i++){
            System.out.print(((0x80000000>>>i) & a) >>> (31-i));
        }
        System.out.println();
    }
}
