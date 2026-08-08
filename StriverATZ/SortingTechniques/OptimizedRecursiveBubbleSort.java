package StriverATZ.SortingTechniques;

public class OptimizedRecursiveBubbleSort {
    static void bubbleSort(int[] arr, int n) {
        // Base case
        if (n == 1) return;

        boolean didSwap = false; 

        for (int j = 0; j <= n - 2; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                didSwap = true;
            }
        }

        if (!didSwap) return;

        bubbleSort(arr, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        int n = arr.length;

        System.out.println("Before Using Bubble Sort:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();

        bubbleSort(arr, n);

        System.out.println("After Using Bubble Sort:");
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();
    }
}
