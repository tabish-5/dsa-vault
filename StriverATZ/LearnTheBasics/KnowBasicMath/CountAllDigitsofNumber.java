package StriverATZ.LearnTheBasics.KnowBasicMath;

public class CountAllDigitsofNumber {
    public static void main(String[] args) {
        int n = 12345;
        int cnt = (int) (Math.log10(n) + 1);
        System.out.println(cnt);
        cnt = String.valueOf(n).length();
        System.out.println(cnt);
    }
}
