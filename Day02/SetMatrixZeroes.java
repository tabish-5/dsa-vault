package Day02;

// import java.util.ArrayList;
import java.util.Arrays;

public class SetMatrixZeroes {
    // public void setZeroes(int[][] matrix) {
    //     ArrayList<Integer[]> al = new ArrayList<>();
    //     for(int i = 0; i < matrix.length; i++){
    //         for(int j = 0; j < matrix[0].length; j++){
    //             al.add(new Integer[]{i,j});
    //         }
    //     }
    //     Integer[] temp;
    //     while(al.size() > 0){
    //         temp = al.remove(0);
    //         helper(matrix,temp[0],temp[1]);
    //     }
    // }

    // private void helper(int[][] matrix, int r, int c){
    //     int i = 0;
    //     while(i < matrix.length){
    //         matrix[i++][c] = 0;
    //     }
    //     i=0;
    //     while(i < matrix[0].length){
    //         matrix[r][i++] = 0;
    //     }
    // }

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
