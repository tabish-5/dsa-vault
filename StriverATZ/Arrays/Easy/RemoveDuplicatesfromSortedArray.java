package StriverATZ.Arrays.Easy;

public class RemoveDuplicatesfromSortedArray {
    public boolean check(int[] nums) {
        int n = nums.length;
        int breaks = 0;
        for (int i = 0 ; i < n ; i ++ ){
            if (nums[i] > nums[(i + 1)%n]){
                breaks ++;
                if (breaks > 1) return false;
            } 

        }
        return true;
        
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray ch = new RemoveDuplicatesfromSortedArray();
        
        int[] nums = {1,1,2};
        boolean ans = ch.check(nums);
        System.out.println(ans);
        
        int[] num = {0,0,1,1,1,2,2,3,3,4};
        ans = ch.check(num);
        System.out.println(ans);
    }
}
