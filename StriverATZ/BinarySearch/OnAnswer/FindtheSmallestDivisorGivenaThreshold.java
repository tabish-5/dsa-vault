package StriverATZ.BinarySearch.OnAnswer;

public class FindtheSmallestDivisorGivenaThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = 1;
        
        for (int pile : nums) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (checkthreshold(nums, threshold, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean checkthreshold(int[] nums, int threshold, int k) {
        long value = 0;
        
        for (int n : nums) {
            value += (n + k - 1) / k;
            if(value > threshold) return false; // can be used or not
        }
        
        return value <= threshold;
    }
}
