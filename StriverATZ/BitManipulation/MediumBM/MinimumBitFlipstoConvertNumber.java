package StriverATZ.BitManipulation.MediumBM;

public class MinimumBitFlipstoConvertNumber {
    public int minBitFlips(int start, int goal) {
        int count =0, n = start ^ goal;

        while (n >0) {
            n = n & (n-1);
            count++;
        }
        return count;
    }
}
