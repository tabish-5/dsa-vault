package StriverATZ.BinarySearch.OnAnswer;

public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        int left = 1;
        int right = 1;
        
        for (int wght : weights) {
            left = Math.max(wght, left);
            right += wght;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canTransport(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canTransport(int[] weights, int days, int k) {
        long daysReq = 0;
        int sum = 0;
        for (int wght : weights) {
            if (sum + wght > k) {
                sum = 0;
                daysReq++;
            }
            sum += wght;
        }   
        daysReq++;
        
        return daysReq <= days;
    }
}
