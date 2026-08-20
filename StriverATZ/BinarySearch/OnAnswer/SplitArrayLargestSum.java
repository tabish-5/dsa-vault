package StriverATZ.BinarySearch.OnAnswer;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        if (k > nums.length)
            return -1;

        int low = 0;
        int high = 0;
        for(int e : nums){
            low = Math.max(e,low);
            high += e;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (count(nums, mid, k)) {
                high = mid - 1; 
            } else {
                low = mid + 1;  
            }
        }
        return low;
    }

    boolean count(int[] arr, int pages, int k) {
        int students = 1; 
        long pagesStudent = 0;
        for (int i : arr) {
            if (pagesStudent + i <= pages) {
                pagesStudent += i;
            } else {
                students++;
                pagesStudent = i;
            }
        }
        return k >= students;
    }
}
