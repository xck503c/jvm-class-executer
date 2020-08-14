import com.xck.util.BytesUtil;

/**
 * 测试整型的二级制表示
 * -10 - 11111111111111111111111111110110
 */
public class TestInt {

    public static void main(String[] args) {
        intToBits();
    }

    public static void intToBits(){
        System.out.println(BytesUtil.byteToInt(new byte[]{0,44}));
    }

    /**
     * -128 - 10000000
     * -127 - 10000001
     */
    public static void byteToBits(){
        byte a = -127;
        for(byte i=0; i<8; i++){
            System.out.print(((0x80>>>i) & a) >>> (7-i));
        }
        System.out.println();
    }
}
