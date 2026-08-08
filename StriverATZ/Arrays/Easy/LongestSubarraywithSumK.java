package StriverATZ.Arrays.Easy;

class MaxConsecutiveOnes {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;

        int maxLen = 0;

        int left = 0, right = 0;

        int sum = nums[0];

        while (right < n) {

            while (left <= right && sum > k) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if (right < n) {
                sum += nums[right];
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;

        MaxConsecutiveOnes obj = new MaxConsecutiveOnes();

        int ans = obj.longestSubarray(nums, k);

        System.out.println("The length of longest subarray having sum k is: " + ans);
    }
}
