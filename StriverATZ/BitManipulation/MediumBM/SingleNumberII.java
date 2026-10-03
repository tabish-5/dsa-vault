package StriverATZ.BitManipulation.MediumBM;

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;

        for (final int num : nums) {
            // "ones" tracks bits appearing 1st time (modulo 3)
            ones ^= (num & ~twos);
            // "twos" tracks bits appearing 2nd time (modulo 3)
            twos ^= (num & ~ones);
        }

        return ones;
    }
}
