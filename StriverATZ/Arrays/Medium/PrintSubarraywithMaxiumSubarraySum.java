package StriverATZ.Arrays.Medium;

public class PrintSubarraywithMaxiumSubarraySum {
    public int maxSubArray(int[] nums) {
        long maxi = Long.MIN_VALUE; 
        long sum = 0; 
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; 
            if (sum > maxi) {
                maxi = sum; 
            }
            if (sum < 0) {
                sum = 0; 
            }
        }
        
        return (int) maxi;
    }

    public static void main(String[] args) {
        PrintSubarraywithMaxiumSubarraySum obj = new PrintSubarraywithMaxiumSubarraySum();
        
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int maxSum = obj.maxSubArray(arr);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}


