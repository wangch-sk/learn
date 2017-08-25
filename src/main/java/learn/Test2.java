package learn;

public class Test2 {
    private String a = "aa";
    
    private static class SubC1{
        private String b = "b";
        public void setB(String b){
            this.b = b;
        }
    }
    
    public static void main(String[] args) {
        final SubC1 s = new SubC1();
        System.out.println(s.b);
        s.setB("a");
        System.out.println(s.b);
    }
}
