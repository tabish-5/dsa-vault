package Day01;

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
}
