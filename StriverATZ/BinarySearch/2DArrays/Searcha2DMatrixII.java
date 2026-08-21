// package StriverATZ.BinarySearch.2DArrays;

public class Searcha2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length-1;
        int c = 0;

        // System.out.println(r);
        // System.out.println(c);

        while(r >= 0 && c < matrix[0].length){
            // System.out.println(matrix[r][c]);
            
            if(matrix[r][c] == target){
                return true;
            }else if(matrix[r][c] > target){
                r--;
            }else{
                c++;
            }
        }

        return false;
    }
}
