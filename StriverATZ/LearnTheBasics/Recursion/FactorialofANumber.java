package StriverATZ.LearnTheBasics.Recursion;

public class FactorialofANumber {

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int n = 3;
        int ans = factorial(n);
        System.out.println(ans);
    }

}
