// package StriverATZ.BinarySearch.1DArrays;

public class UpperBound {
    public int upperBound(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;  // Default to length if not found

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > x) {
                ans = mid;        
                high = mid - 1;   
            } else {
                low = mid + 1;    
            }
        }
        return ans;  // Return final answer
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 9, 15, 19};  // Sorted array
        int x = 9;

        UpperBound finder = new UpperBound();
        int ind = finder.upperBound(arr, x);  // Call method

        System.out.println("The upper bound is the index: " + ind);
    }


}
