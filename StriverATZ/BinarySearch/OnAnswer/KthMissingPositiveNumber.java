package StriverATZ.BinarySearch.OnAnswer;

public class KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Missing numbers before arr[mid]
            int missing = arr[mid] - (mid + 1);

            if (missing < k)
                left = mid + 1;
            else
                right = mid;
        }

        return left + k;
    }
}
