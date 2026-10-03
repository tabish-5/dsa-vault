package StriverATZ.BitManipulation.MediumBM;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < (1 << nums.length); i++) {
            current = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if((i & (1 << j)) != 0){
                    current.add(nums[j]);
                }
            }
            result.add(new ArrayList<>(current));
        }
                
        return result;
    }
}
