// package StriverATZ.BinarySearch.1DArrays;

public class FindMinimuninSortedRotatedArray {
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums[0] < nums[nums.length - 1])
            return nums[0];
        int si = 0, ei = nums.length - 1, mi;
        while (si <= ei) {
            mi = si + (ei - si) / 2;
            if (nums[mi] > nums[mi + 1])
                return nums[mi + 1];
            if (nums[si] > nums[mi])
                ei = mi - 1;
            else
                si = mi + 1;
        }
        return -1;
    }
}
