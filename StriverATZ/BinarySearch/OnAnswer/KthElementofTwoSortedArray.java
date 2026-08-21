package StriverATZ.BinarySearch.OnAnswer;

public class KthElementofTwoSortedArray {

    public int kthElement(int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;
        
        if (m > n) {
            return kthElement(b, a, k);
        }

        int left = k; 
        int low = Math.max(0, k - n), high = Math.min(k, m);

        while (low <= high) {
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;

            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < m) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n) ? b[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1; 
            } else {
                low = mid1 + 1; 
            }
        }

        return -1; 
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 9};
        int[] b = {1, 4, 8, 10};
        int k = 5;

        KthElementofTwoSortedArray obj = new KthElementofTwoSortedArray();
        System.out.println("The " + k + "-th element of two sorted arrays is: " + obj.kthElement(a, b, k));
    }

}
