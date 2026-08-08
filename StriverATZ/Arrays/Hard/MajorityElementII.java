package StriverATZ.Arrays.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i :nums){
            hm.put(i, hm.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer, Integer> i : hm.entrySet()){
            if(i.getValue() > (nums.length/3))
                ls.add(i.getKey());
        }

        return ls;
    }
}
