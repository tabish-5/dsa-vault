package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> al = new ArrayList<>();
        helper(candidates, target, 0, 0, al);
        return ans;
    }

    private void helper(int[] arr, int trgt, int idx, int sum, List<Integer> al){
            // print(al);
            // System.out.println("sum "+sum);

        if(sum == trgt){
            ans.add(new ArrayList<>(al));
            al.remove(al.size() -1);
            // System.out.println("ans");
            return;
        }
        if(sum > trgt){
            al.remove(al.size() -1);
            // System.out.println("removed");
            return;
        }

        for(int i = idx; i< arr.length; i++){
            if (arr[i] > trgt) {
                continue;
            }
            al.add(arr[i]);
            // System.out.println("added "+arr[i]);
            helper(arr, trgt, i, sum + arr[i], al);
        }
        if(al.size() >0) al.remove(al.size() -1);
    }


    // private void print(List<Integer> al){
    //     for(int e : al){
    //         System.out.print(e+" ");
    //     }
    //     System.out.println();
    // }
}
