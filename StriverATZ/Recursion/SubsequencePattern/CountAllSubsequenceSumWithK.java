package StriverATZ.Recursion.SubsequencePattern;

public class CountAllSubsequenceSumWithK {
    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        return heleper(nums, k, nums.length, 0);
    }

    private int heleper(int[] nums, int k, int n, int sum){
        if(n == 0){
            if(sum == k) return 1;
            return 0;
        }
        
        return heleper(nums,k, n-1, sum + nums[n-1]) + heleper(nums,k, n-1, sum);
    }
}
