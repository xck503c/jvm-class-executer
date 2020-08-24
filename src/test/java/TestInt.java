import com.xck.util.BytesUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 测试整型的二级制表示
 * -10 - 11111111111111111111111111110110
 */
public class TestInt {

    public static void main(String[] args) {
        floatToBits();
        s();
    }

    public static void byteToInt(){
        System.out.println(BytesUtil.byteToInt(new byte[]{0,44}));
    }

    /**
     * -128 - 10000000
     * -127 - 10000001
     */
    public static void byteToBits(byte a){
        for(byte i=0; i<8; i++){
            System.out.print(((0x80>>>i) & a) >>> (7-i));
        }
        System.out.println();
    }

    /**
     *
     * @param a
     */
    public static void intToBits(int a){
        for(byte i=0; i<32; i++){
            System.out.print(((0x80000000>>>i) & a) >>> (31-i));
        }
        System.out.println();
    }

    /**
     * -127.45678f - -1023481377 - 1 1000010111111101110100111011111
     */
    public static void floatToBits(){
//        float a = -127.45678f;
        float a = 17.625f;
        BytesUtil.floatToByte(a);
        int b = Float.floatToIntBits(a);
        System.out.println(b);
        intToBits(b);
    }

    public static void s(){
        String s = "00000010001011000100000";

        float sum = 0.0f;
        for(int i=0, j=1; i<s.length(); i++, j++){

            int y = Integer.parseInt(s.charAt(i)+"");
            if (y!=0) {
                float x = 1;
                for(int tmp=0; tmp<j; tmp++){
                    x = x/2.0f;
                }
                sum = sum+y*x;
                System.out.println(y*x);
            }
        }
        System.out.println(sum);
    }
}
