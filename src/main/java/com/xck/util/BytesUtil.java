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
    public static byte[] floatToByte(float f){

        //1.确定数符
        int sign = 0; //数符
        if(f < 0) {
            f = -f;
            sign = 1;
        }

        String floatValue = (f+"");
        int index = floatValue.indexOf(".");

        String roudNumber = floatValue.substring(0, index);
        String decimal = floatValue.substring(index+1);

        StringBuilder roudSb = new StringBuilder("");
        int tmpInt = Integer.parseInt(roudNumber);
        while (true){
            int bit = tmpInt%2;
            tmpInt = tmpInt/2;
            roudSb.append(bit ^ 1);
            if(tmpInt == 0 || roudSb.length() == 23){
                break;
            }
        }
        roudSb.reverse();

        StringBuilder decimalSb = new StringBuilder("");
        float tmpFloat = Float.parseFloat(decimal);
        while (true){
            tmpFloat = tmpFloat*2;
            int bit = tmpFloat > 1 ? 1 : 0;
            if(tmpFloat > 1.0f){
                bit = 1;
                decimalSb.append(bit ^ 1);
                tmpFloat = tmpFloat - 1;
            }else if(tmpFloat == 1.0f){
                decimalSb.append(bit ^ 1);
                break;
            }else {
                decimalSb.append(bit ^ 1);
            }
            if(decimalSb.length() == 23){
                break;
            }
        }

        System.out.println(roudSb.toString());
        return null;
    }
}
