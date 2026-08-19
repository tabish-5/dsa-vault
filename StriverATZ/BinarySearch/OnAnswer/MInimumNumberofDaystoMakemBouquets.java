package StriverATZ.BinarySearch.OnAnswer;

public class MInimumNumberofDaystoMakemBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if(bloomDay.length < m * k) return -1;
        int high = 0, low = 0;
        for(int e : bloomDay){
            high = Math.max(e, high);
        }
        int mid;
        while (low < high) {
            mid = low + (high - low)/2;

            if(canBloom(bloomDay, m , k, mid)){
                high = mid;
            }else{
                low = mid +1;
            }
        }
        return low;
    }

    private boolean canBloom(int[] bloomDay, int m, int k, int key){
        int ans = 0, count = 0;
        for(int e : bloomDay){
            if (e <= key) {
                count++;
            }else{
                ans += count /k;
                count = 0;
            }
        }
        ans += count /k;

        return ans >= m;
    }
}
