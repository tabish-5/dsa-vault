package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        
        helper(nums, 0, current, result);
        
        return result;
    }
    
    private void helper(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == arr.length) {
            // List<Integer> temp = new ArrayList<>(current);
            result.add(new ArrayList<>(current));
            // current = new ArrayList<>();
            return;
        }

        helper(arr, index + 1, current, result);

        current.add(arr[index]);
        helper(arr, index + 1, current, result);

        current.remove(current.size() - 1);
    }
}
