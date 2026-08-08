package StriverATZ.Arrays.Medium;

public class RearrangeArrayElementsbySign {
    public int[] rearrangeArray(int[] nums) {
        int idx1 = -2, idx2 = -1;
        int[] ar = new int[nums.length];

        for(int i : nums){
            if(i < 0) ar[idx2 = idx2 +2] = i;
            else ar[idx1 = idx1 + 2] = i;
        }
        return ar;
    }
}
