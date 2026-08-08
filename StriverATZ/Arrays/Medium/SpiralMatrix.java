package StriverATZ.Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0, right = matrix[0].length-1, top = 0, bottom = matrix.length-1, i = 0;
        List<Integer> ans = new ArrayList<>();

        while (top <= bottom && left <= right){
            //top 
            while(i <= right){
                ans.add(matrix[top][i]);
                i++;
            }
            top++;
            i=top;
            
            //right
            while(i <= bottom){
                ans.add(matrix[i][right]);
                i++;
            }
            right--;
            i= right;
            if(top > bottom || left > right) break;

            //bottom
            while(i >= left){
                ans.add(matrix[bottom][i]);
                i--;
            }
            bottom--;
            i = bottom;

            //left
            while(i >= top){
                ans.add(matrix[i][left]);
                i--;
            }
            left++;
            i = left;
        }
        
        return ans;
    }
}
