// package StriverATZ.BinarySearch.1DArrays;

public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        int si = 0, ei = nums.length - 1, mi;
        while (si <= ei) {
            mi = si + (ei - si) / 2;
            if (nums[mi] == target)
                return mi;
            if (nums[si] <= nums[mi]) {
                if (nums[si] <= target && nums[mi] >= target)
                    ei = mi - 1;
                else
                    si = mi + 1;
            } else {
                if (nums[ei] >= target && nums[mi] <= target)
                    si = mi + 1;
                else
                    ei = mi - 1;
            }
        }
        return -1;
    }
}
