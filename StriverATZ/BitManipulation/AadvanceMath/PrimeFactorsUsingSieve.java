package StriverATZ.BitManipulation.AadvanceMath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeFactorsUsingSieve {
    
    // Global array to store the Smallest Prime Factor (SPF)
    private static int[] spf;

    // Precomputes the Smallest Prime Factor for every number up to MAX_VAL
    public static void precomputeSPF(int maxVal) {
        spf = new int[maxVal + 1];
        
        // Initialize every number's SPF as itself
        for (int i = 0; i <= maxVal; i++) {
            spf[i] = i;
        }

        // Apply the Sieve of Eratosthenes
        for (int p = 2; p * p <= maxVal; p++) {
            // If spf[p] is still p, then p is a prime number
            if (spf[p] == p) {
                // Mark the SPF for all multiples of p
                for (int i = p * p; i <= maxVal; i += p) {
                    if (spf[i] == i) {
                        spf[i] = p;
                    }
                }
            }
        }
    }

    // Returns a list of prime factors for a given number n
    public static List<Integer> getPrimeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        
        // Repeatedly divide n by its smallest prime factor
        while (n > 1) {
            factors.add(spf[n]);
            n /= spf[n];
        }
        
        return factors;
    }

    public static void main(String[] args) {
        int maxLimit = 100000;
        
        // Step 1: Precompute SPF array once
        precomputeSPF(maxLimit);

        // Step 2: Query the prime factors of any number within the limit
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int number = sc.nextInt();
        System.out.println();
        sc.close();
        List<Integer> factors = getPrimeFactors(number);
        
        System.out.println("Prime factors of " + number + ": " + factors);
        // Output: Prime factors of 84: [2, 2, 3, 7]
    }
}
