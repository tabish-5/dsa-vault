package StriverATZ.Arrays.Medium;

import java.util.Arrays;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int rowl = matrix.length;
        int coll = matrix[0].length;
        int[] row = new int[rowl];
        int[] col = new int[coll];
        Arrays.fill(row, 1);
        Arrays.fill(col, 1);
        for (int i = 0; i < rowl; i++) {
            for (int j = 0; j < coll; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 0;
                    col[j] = 0;
                }
            }
        }
        for (int i = 0; i < rowl; i++) {
            for (int j = 0; j < coll; j++) {
                if (row[i] == 0 || col[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        return;
    }
}
