// import java.util.*;
public class FindTheMissingAndRepeatingNumber {


    public int[] findMissingRepeatingNumbers(int[] nums) {
        // Size of the array
        int n = nums.length; 

        // XOR of all elements and numbers from 1 to n
        int xr = 0;
        for (int i = 0; i < n; i++) {
            xr = xr ^ nums[i];     // XOR with array element
            xr = xr ^ (i + 1);     // XOR with natural number
        }

        // Get the rightmost set bit in xr
        int number = (xr & ~(xr - 1));

        // Two groups based on this bit
        int zero = 0, one = 0;

        // Divide nums into groups and XOR within each group
        for (int i = 0; i < n; i++) {
            if ((nums[i] & number) != 0) {
                one ^= nums[i];
            } else {
                zero ^= nums[i];
            }
        }

        // Divide natural numbers 1 to n into groups and XOR
        for (int i = 1; i <= n; i++) {
            if ((i & number) != 0) {
                one ^= i;
            } else {
                zero ^= i;
            }
        }

        // Check which is repeating and which is missing
        int cnt = 0;
        for (int val : nums) {
            if (val == zero) cnt++;
        }

        if (cnt == 2) {
            return new int[]{zero, one}; // zero is repeating
        }
        return new int[]{one, zero}; // one is repeating
    }




    public int[] findMissingRepeatingNumbersII(int[] nums) {
        
        // Size of the array
        long n = nums.length;

        // Sum of first n natural numbers
        long SN = (n * (n + 1)) / 2;

        // Sum of squares of first n natural numbers
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        // Calculate actual sum (S) and sum of squares (S2) of array elements
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += nums[i];
            S2 += (long) nums[i] * (long) nums[i];
        }

        // Compute the difference values
        long val1 = S - SN; // X - Y

        // S2 - S2n = X^2 - Y^2
        long val2 = S2 - S2N;

        // Calculate X + Y
        val2 = val2 / val1;

        // Calculate X and Y
        long x = (val1 + val2) / 2; // repeating
        long y = x - val1;          // missing

        return new int[]{(int) x, (int) y};
    }

   
}
