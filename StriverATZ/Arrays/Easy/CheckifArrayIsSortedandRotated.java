package StriverATZ.Arrays.Easy;

public class CheckifArrayIsSortedandRotated {
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
        CheckifArrayIsSortedandRotated ch = new CheckifArrayIsSortedandRotated();
        
        int[] nums = {3,4,5,1,2};
        boolean ans = ch.check(nums);
        System.out.println(ans);
        
        int[] num = {2,1,3,4};
        ans = ch.check(num);
        System.out.println(ans);
    }
}
