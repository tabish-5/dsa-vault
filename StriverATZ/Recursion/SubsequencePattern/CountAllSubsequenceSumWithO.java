package StriverATZ.Recursion.SubsequencePattern;

public class CountAllSubsequenceSumWithO {
    public int countSubsequenceWithZeroSum(int[] nums) {
        return heleper(nums, nums.length, 0);
    }

    private int heleper(int[] nums, int n, int sum){
        if(n == 0){
            if(sum == 0) return 1;
            return 0;
        }
        
        return heleper(nums, n-1, sum + nums[n-1]) + heleper(nums, n-1, sum);
    }
}
