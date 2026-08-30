package StriverATZ.Recursion.StrongHold;

public class Power {
    
    public double myPow(double x, int n) {
        if(x == 1) return 1;
        if(x == -1) return (n %2 ==0 )? 1:-1;
        if (n < 0) {
            long y = n;
            return 1.0 / power(x, -y);
        }
        return power(x, n);
    }
    
    private double power(double x, long n) {
        if (n == 0) return 1.0;
        
        if (n == 1) return x;
        
        if (n % 2 == 0) {
            return power(x * x, n / 2);
        }
        
        return x * power(x, n - 1);
    }

    public static void main(String[] args) {
        Power sol = new Power();
        
        double x = 2.0;
        int n = 10;
        double result = sol.myPow(x, n);
        System.out.println(x + "^" + n + " = " + result);
    }

}
