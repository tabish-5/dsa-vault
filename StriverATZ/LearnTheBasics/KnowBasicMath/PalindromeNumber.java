package StriverATZ.LearnTheBasics.KnowBasicMath;
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // Negative numbers and numbers ending with 0 (except 0) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even length: x == reversedHalf
        // For odd length: x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        int n = 12345;
        PalindromeNumber pn = new PalindromeNumber();
        pn.isPalindrome(n);
    }
}
