public class MatrixMedian {

    // Function to count elements less than or equal to mid in a row
    int countLessEqual(int[] row, int mid) {
        // Binary search in row
        int low = 0, high = row.length;
        while (low < high) {
            int m = (low + high) / 2;
            if (row[m] <= mid) low = m + 1;
            else high = m;
        }
        return low;
    }

    // Function to find the median
    int findMedian(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Initialize low and high values
        int low = matrix[0][0];
        int high = matrix[0][cols - 1];
        for (int i = 1; i < rows; i++) {
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][cols - 1]);
        }

        // Binary search in value range
        while (low < high) {
            int mid = (low + high) / 2;
            int count = 0;

            // Count number of elements ≤ mid
            for (int i = 0; i < rows; i++) {
                count += countLessEqual(matrix[i], mid);
            }

            if (count < (rows * cols + 1) / 2)
                low = mid + 1;
            else
                high = mid;
        }

        // Return the median
        return low;
    }

    public static void main(String[] args) {
        MatrixMedian obj = new MatrixMedian();
        int[][] matrix = {
            {1, 3, 5},
            {2, 6, 9},
            {3, 6, 9}
        };
        System.out.println("Median: " + obj.findMedian(matrix));
    }

}
