package StriverATZ.BinarySearch.OnAnswer;

import java.util.*;
public class KokoEatingBananas {

    private int minEatingSpeediptimized(int[] piles, int h) {
        int left = 1;
        int right = 1;
        
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canFinish(int[] piles, int h, int k) {
        long totalHours = 0;
        
        for (int pile : piles) {
            totalHours += (pile + k - 1) / k;
        }
        
        return totalHours <= h;
    }


    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public int calculateTotalHours(int[] piles, int speed) {
        int totalH = 0;
        for (int bananas : piles) {
            totalH += (int)Math.ceil((double)bananas / speed);
        }
        return totalH;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = Arrays.stream(piles).max().getAsInt();

        int low = 1, high = maxPile;
        int ans = maxPile;

        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = calculateTotalHours(piles, mid);

            if (totalH <= h) {
                ans = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        KokoEatingBananas obj = new KokoEatingBananas();
        System.out.println(obj.minEatingSpeed(piles, h));
        System.out.println(obj.minEatingSpeediptimized(piles, h));
    }
}
