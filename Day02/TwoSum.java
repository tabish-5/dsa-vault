package Day02;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i < nums.length;i++){
        int current = nums[i];
        int need=target-current;
        if(map.containsKey(need)){
           return new int[]{map.get(need),i};
        }
        map.put(current,i);   
        }

        return new int[]{};
    }
}
