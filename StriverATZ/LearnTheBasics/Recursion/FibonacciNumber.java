package StriverATZ.LearnTheBasics.Recursion;

public class FibonacciNumber {
    public int fib(int n) {
        if (n < 0)
            return -1;
        if (n == 0 || n == 1)
            return n;
        int fnm1 = 1, fnm2 = 0;
        while (n > 1) {
            fnm1 = fnm1 + fnm2;
            fnm2 = fnm1 - fnm2;
            n--;
        }
        return fnm1;
    }

    public static void main(String[] args) {
        FibonacciNumber fn = new FibonacciNumber();
        System.out.println(fn.fib(5));
    }
}
