package Day02;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int elem = -1;
        int freq = 0;
        for (int i : nums) {
            if (i == elem) {
                freq++;
            }
            else{
                if (freq >0) {
                    freq --;
                }
                else{
                    elem = i;
                    freq++;
                }
            }
        }
        return elem;
    }
}
