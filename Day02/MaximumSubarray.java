package Day02;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = 0, curr = 0;
        for (int i : nums) {
            curr += i;
            if (curr < 0) {
                curr = 0;
            }
            sum = Math.max(sum, curr);
        }
        return sum;
    }
}
