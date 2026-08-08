package StriverATZ.Arrays.Easy;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0 , max = 0;
        for(int e : nums){
            if(e == 1)
                curr++;
            else{
                max = Math.max(max,curr);
                curr = 0;
            }
        }
        return Math.max(max,curr);
    }
}
