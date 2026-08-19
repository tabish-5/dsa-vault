// package StriverATZ.BinarySearch.1DArrays;
// import java.util.*;
public class SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int si = 0, ei = nums.length - 1, mi;
        while (si <= ei) {
            mi = si + (ei - si) / 2;
            if (nums[mi] == target)
                return true;
            if (nums[mi] == nums[si]) {
                si++;
                continue;
            }
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
        return false;
            
    }
}
