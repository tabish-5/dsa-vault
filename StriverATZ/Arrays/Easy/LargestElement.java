package StriverATZ.Arrays.Easy;

public class LargestElement {

    // Function to find the largest element in the array
    public static int findLargestElement(int[] arr, int n) {
        int max = arr[0];  // Initialize max with the first element in the array

        // Iterate through the array to find the maximum element
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {  // If the current element is greater than max, update max
                max = arr[i];
            }
        }

        return max;  // Return the largest element found
    }

    public static int findLargestElementbyme(int[]arr){
        int max = arr[0];
        for(int i : arr){
            max = max > i? max: i;
        }

        return max;
    }



    public static void main(String[] args) {
        // Array 1
        int[] arr1 = {2, 5, 1, 3, 0};
        int n = arr1.length;  // Size of the array
        int max = findLargestElement(arr1, n);  // Call the function to find the largest element
        System.out.println("The largest element in the array is: " + max);  // Output the result

        // Array 2
        int[] arr2 = {8, 10, 5, 7, 9};
        n = arr2.length;  // Size of the array
        max = findLargestElementbyme(arr2);  // Call the function to find the largest element
        System.out.println("The largest element in the array is: " + max);  // Output the result
    }

}
