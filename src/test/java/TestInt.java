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
     * 17.625 = 10001.10
     */
    public static void floatToBits(){
        float a = -0.45678f;
//        float a = 17.625f;
        System.out.println(BytesUtil.floatToBit(a));
        int b = Float.floatToIntBits(a);
        System.out.println(b);
        intToBits(b);

        BytesUtil.intToFloat(b);
    }
}
