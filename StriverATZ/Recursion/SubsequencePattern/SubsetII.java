package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SubsetII {

    List<List<Integer>>ans;
    
    public void solve(int start , int[] nums , List<Integer>curr){
        ans.add(new ArrayList<>(curr));

        for(int i=start; i<nums.length; i++){
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            curr.add(nums[i]);
            solve(i+1 , nums , curr);
            curr.remove(curr.size() -1);
        }
    }

    public List<List<Integer>> subsetsWithDupBetter(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<>();
        solve(0 , nums , new ArrayList<>());
        return ans;
    }







    HashSet<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
       List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, current, result);
        
        return result;
    }
    
    private void helper(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == arr.length) {
            // List<Integer> temp = new ArrayList<>(current);
            // System.out.println(contains(current));
            if(contains(current)) return;
            set.add(new ArrayList<>(current));
            result.add(new ArrayList<>(current));
            // current = new ArrayList<>();
            return;
        }

        helper(arr, index + 1, current, result);    

        current.add(arr[index]);
        helper(arr, index + 1, current, result);

        current.remove(current.size() - 1);
    }

    private boolean contains(List<Integer> current){
        boolean check = true;
        for(List<Integer> ls : set){
            if (ls.size() == current.size()) {
                
                check = true;
                for(int i = 0; i < ls.size(); i++){
                    if (ls.get(i) != current.get(i)) {
                        check = false;
                    }
                }
                if (check == true) return true;
            }
        }


        return false;
    }

}
