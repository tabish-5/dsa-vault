package StriverATZ.LearnTheBasics.KnowBasicMath;

public class GCDofTwoNumbers {
    public static int findGcd(int a, int b) {
        while(a > 0 && b > 0) {
            if(a > b) 
                a = a % b;
            else 
                b = b % a;
        }
        if(a == 0) return b;
        return a;
    }

    public static void main(String[] args) {
        int x = findGcd(20, 15);
        System.out.println(x);
    }
}
