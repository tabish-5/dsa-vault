package StriverATZ.BitManipulation.LearnBM;

public class DivideTwoInteger {
    public int divide(int dividend, int divisor) {
        // Overflow case: -2147483648 / -1 = 2147483648
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Use long to safely handle Integer.MIN_VALUE
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // Try subtracting the largest possible multiples
        for (int i = 31; i >= 0; i--) {
            if ((b << i) <= a) { //b << i = b × 2^i
                a -= (b << i);
                result += (1 << i);
            }
        }

        // Determine the sign
        if ((dividend < 0) ^ (divisor < 0)) {
            result = -result;
        }

        return result;
    }
}
