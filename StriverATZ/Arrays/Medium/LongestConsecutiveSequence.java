package StriverATZ.Arrays.Medium;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums.length <=1) return nums.length;

        Arrays.sort(nums);
        int idx = 0, count = 1, max = 1;
        while(idx< nums.length -1){
            if(nums[idx] +1 == nums[idx +1]){
                count++;
            }else if(nums[idx] != nums[idx +1]){
                count = 1;
            }
            max = Math.max(max, count);
            idx++;
        }

        return max;

    }
}
