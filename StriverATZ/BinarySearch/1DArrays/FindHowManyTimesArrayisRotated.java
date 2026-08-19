// package StriverATZ.BinarySearch.1DArrays;

public class FindHowManyTimesArrayisRotated {
    public int findRotations(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
