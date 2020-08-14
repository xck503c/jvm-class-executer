public class HelloWorld {
    private static A a = new A();

    public static void main(String[] args) {
        int a = 1;
        int b = a+1;
        System.out.println(a+b);
    }

    public static class A{
        int a;
        private double b;
        public String s = "sdfdf";
    }
}
