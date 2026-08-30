package StriverATZ.Recursion.SubsequencePattern;

import java.util.*;
public class PrintSubsequence {

    public List<String> getSubsequences(String s) {
        // Length of input string
        int n = s.length();

        // Total subsequences = 2^n
        int total = 1 << n;

        List<String> subsequences = new ArrayList<>();

        // Iterate over all bit masks from 0 to 2^n - 1
        for (int mask = 0; mask < total; mask++) {
            StringBuilder subseq = new StringBuilder();

            // Check each bit position in mask
            for (int i = 0; i < n; i++) {
                // If i-th bit of mask is set, include s.charAt(i)
                if ((mask & (1 << i)) != 0) {
                    subseq.append(s.charAt(i));
                }
            }

            subsequences.add(subseq.toString());
        }

        return subsequences;
    }


    
    public List<String> getSubsequencesBetter(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        
        helper(s, 0, current, result);
        
        return result;
    }
    
        private void helper(String s, int index, StringBuilder current, List<String> result) {
            if (index == s.length()) {
                result.add(current.toString());
                return;
            }
    
            helper(s, index + 1, current, result);
    
            current.append(s.charAt(index));
            helper(s, index + 1, current, result);
    
            current.deleteCharAt(current.length() - 1);
        }


    public static void main(String[] args) {
        String s = "abc";

        PrintSubsequence sol = new PrintSubsequence();
        List<String> subsequences = sol.getSubsequences(s);

        // Print all subsequences
        for (String subseq : subsequences) {
            System.out.println("\"" + subseq + "\"");
        }

        // Get all subsequences
        List<String> subsequence = sol.getSubsequencesBetter(s);

        // Print all subsequences
        for (String subseq : subsequence) {
            System.out.println("\"" + subseq + "\"");
        }
    }
}
