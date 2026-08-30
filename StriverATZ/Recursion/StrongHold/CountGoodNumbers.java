package StriverATZ.Recursion.StrongHold;

public class CountGoodNumbers {
    static final long MOD = 1_000_000_007L;

    public int countGoodNumbers(long n) {

        long evenPositions = (n + 1) / 2;
        long oddPositions = n / 2;

        long evenWays = power(5, evenPositions);
        long oddWays = power(4, oddPositions);

        return (int) ((evenWays * oddWays) % MOD);
    }

    // Binary Exponentiation
    private long power(long base, long exponent) {

        long result = 1;

        while (exponent > 0) {

            // If exponent is odd
            if ((exponent & 1) == 1) {
                result = (result * base) % MOD;
            }

            // Square the base
            base = (base * base) % MOD;

            // Divide exponent by 2
            exponent >>= 1;
        }

        return result;
    }
}
