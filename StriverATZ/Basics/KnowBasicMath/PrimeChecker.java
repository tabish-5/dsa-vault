package StriverATZ.Basics.KnowBasicMath;


public class PrimeChecker {
    public boolean checkPrime(int n) {
        for (int i = 2; i *i <= n; i++) {
            if (n % i == 0) {
                return false;  
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8; 
        PrimeChecker obj = new PrimeChecker();
        boolean isPrime = obj.checkPrime(n);  // Function call to check if the number is prime

        if (isPrime) {
            System.out.println(n + " is a prime number.");
        } else {
            System.out.println(n + " is not a prime number.");
        }
    }
}
