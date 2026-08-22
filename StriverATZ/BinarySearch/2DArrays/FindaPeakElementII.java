public class FindaPeakElementII {
    public int maxele(int[][] mat, int col ){
        int n = mat.length;
       
        int index = -1;
        int max = Integer.MIN_VALUE;
         for (int i = 0 ;i < n ; i++){
            if(mat[i][col] > max){
                max = mat[i][col];
                index = i;
            }
         }
         return index;
    }
    public int[] findPeakGrid(int[][] mat) {
        // int n= mat.length;
        int m = mat[0] .length;
        int low = 0;
        int high = m - 1;

        while(low <= high){
            int mid = (low + high ) / 2;
            int row = maxele(mat , mid);

            int left = mid- 1 >= 0 ? mat[row][mid  -1] : Integer.MIN_VALUE;
            int right = mid + 1 < m ? mat[row][mid + 1] : Integer.MIN_VALUE;

            if(left < mat[row][mid] && right < mat[row][mid] ){
                return new  int[]{row, mid};
            }
            else if(left > mat[row][mid]){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return new int[]{-1, -1}; 
        
    }
}
