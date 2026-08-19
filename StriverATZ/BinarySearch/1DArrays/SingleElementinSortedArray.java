// package StriverATZ.BinarySearch.1DArrays;

public class SingleElementinSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] != nums[1])
            return nums[0];
        else if (nums[len - 1] != nums[len - 2])
            return nums[len - 1];
        int left = 0, right = len - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            mid = (mid % 2) == 0 ? mid : mid - 1;
            if (mid != 0 && nums[mid] == nums[mid - 1]) {
                right = mid - 2;
            } else if (mid != len - 1 && nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                return nums[mid];
            }
        }
        return left;
    }
}
