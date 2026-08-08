package StriverATZ.Arrays.Medium;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        HashMap<Long, Integer> mp = new HashMap<>();
        long sum = 0, req;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // prefix array ka sasta jugaad

            if (sum == k){
                // mp.put(sum, mp.getOrDefault(sum,0)+1);
                count++;
            } 

            req = sum - k;

            if (mp.containsKey(req)) count += mp.get(req);
            mp.put(sum, mp.getOrDefault(sum,0)+1);
        }

        return count;
    }
}
