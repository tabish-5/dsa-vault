package Day02;

public class SortColors {
    public void sortColors(int[] nums) {
        int strt = 0, end = nums.length -1, ptr = 0;
        while (ptr <= end) {
            if(nums[ptr] == 0){
                nums[ptr] = nums[strt];
                nums[strt] = 0;
                strt++;
            }else if(nums[ptr] == 2){
                nums[ptr] = nums[end];
                nums[end] = 2;
                end--;
                ptr--;
            }
            ptr++;
        } 
    }
}
