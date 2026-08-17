public class ReversePairs {
    public int merge(int[] arr, int low, int mid, int high) {

        int cnt = 0;

        // Step 1: Count reverse pairs
        int j = mid + 1;

        for (int i = low; i <= mid; i++) {

            while (j <= high && (long) arr[i] > 2L * arr[j]) {
                j++;
            }

            cnt += j - (mid + 1);
        }

        // Step 2: Normal merge
        int[] temp = new int[high - low + 1];

        int left = low;
        int right = mid + 1;
        int k = 0;

        while (left <= mid && right <= high) {

            if (arr[left] <= arr[right]) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[k++] = arr[left++];
        }

        while (right <= high) {
            temp[k++] = arr[right++];
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }

        return cnt;
    }

    public int mergeSort(int[] arr, int low, int high) {

        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;

        int cnt = 0;

        cnt += mergeSort(arr, low, mid);
        cnt += mergeSort(arr, mid + 1, high);
        cnt += merge(arr, low, mid, high);

        return cnt;
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
}
