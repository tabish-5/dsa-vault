package StriverATZ.Arrays.Medium;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int temp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                temp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] i : matrix) {
            reverse(i);
        }
    }

    public void reverse(int[] arr){
        int si = 0,k;
        int ei = arr.length-1;
        while (si<ei) {
            k = arr[si];
            arr[si]=arr[ei];
            arr[ei]=k;
            si++;
            ei--;
        }
    }
}
