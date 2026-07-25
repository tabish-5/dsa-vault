package Day02;

import java.util.Arrays;
// import java.util.HashSet;
// import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // if(nums == null || nums.length == 0) {
        //     return 0;
        // }
        
        // int best = 0;
        // Set<Integer> values = new HashSet<>();
        
        // for(int value: nums) {
        //     values.add(value);
        // }
        
        // for(int value: values) {
        //     if(values.contains(value-1)) {
        //         continue;
        //     }
            
        //     int length = 1;
        //     while (values.contains(value + length)) {
        //         length++;
        //     }
            
        //     best = Math.max(best, length);
        // }
        
        // return best;


        if(nums.length <=1) return nums.length;

        Arrays.sort(nums);
        int idx = 0, count = 1, max = 1;
        while(idx< nums.length -1){
            if(nums[idx] +1 == nums[idx +1]){
                count++;

            }else if(nums[idx] == nums[idx +1]){

            }else{
                count = 1;
            }
            max = Math.max(max, count);
            idx++;
        }

        return max;

    }
}
