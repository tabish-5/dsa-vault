import java.util.*;

public class CountTheNumberofWithgivenxorK {


    public int maxLen(int[] A, int k) {
        Map<Integer, Integer> mpp = new HashMap<>();
        int count = 0;
        int sum = 0;

        for (int i = 0; i < A.length; i++) {
            sum ^= A[i];

            if (sum == k) {
                count++;
            }
            if (mpp.containsKey(k-sum)) {
                count += mpp.get(k-sum); 
            }
            mpp.put(sum, mpp.getOrDefault(sum, 0)+1);
        
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[]{5, 6, 7, 8, 9};
        int k = 5;
        int ans = new CountTheNumberofWithgivenxorK().maxLen(A, k);
        System.out.println(ans);
    }

}
