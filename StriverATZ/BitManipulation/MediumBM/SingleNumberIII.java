package StriverATZ.BitManipulation.MediumBM;

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int e : nums){
            xor ^= e;
        }

        int ele = (xor & (xor-1)) ^ xor;
        int first = 0, second = 0;
        for (int e : nums) {
            if ((e & ele) == ele) {
                first ^= e;
            }else{
                second ^= e;
            }
        }


        return new int[]{first, second};
    }
}
