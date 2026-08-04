package StriverATZ.LearnTheBasics.KnowBasicMath;

public class ArmstrongChecker {

    public static boolean isArmstrong(int num) {
        int k = String.valueOf(num).length(); // Get number of digits
        int sum = 0;
        int n = num;

        while (n > 0) {
            int ld = n % 10;             // Last digit
            sum += Math.pow(ld, k);      // Add ld^k
            n /= 10;                     // Remove digit
        }

        return sum == num;
    }

    public static void main(String[] args) {
        int number = 153;

        if (isArmstrong(number))
            System.out.println(number + " is an Armstrong number.");
        else
            System.out.println(number + " is not an Armstrong number.");
    }
}
