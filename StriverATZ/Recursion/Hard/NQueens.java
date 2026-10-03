package StriverATZ.Recursion.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if(n <= 0) return result;
        List<String> ls = create(n);

        helper(n, ls, 0);

        

        return result;
    }


    void helper(int n, List<String> ls, int idx){
        if (idx == n) {
            result.add(new ArrayList<>(ls));
            return;
        }

        String s = ls.get(idx);
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < n; i++) {
            if(isSafe(i, idx, ls)){
                ls.remove(idx);
                sb.setCharAt(i, 'Q');
                ls.add(idx, sb.toString());
                helper(n, ls, idx +1);
                ls.remove(idx);
                ls.add(idx, s);

            }
        }
    }

    boolean isSafe(int x, int y, List<String> ls){
        // top
        for (int i = y -1; i >= 0; i--) {
            if(ls.get(i).charAt(x) == 'Q') return false;
        }

        //left diagonal
        for(int i = x -1, j = y-1; i >=0 && j >= 0; i--, j--){
            if(ls.get(j).charAt(i) == 'Q') return false;
        }
        
        //right diagonal
        for(int i = x +1, j = y-1; i < ls.size() && j >= 0; i++, j--){
            if(ls.get(j).charAt(i) == 'Q') return false;
        }

        return true;
    }


    List<String> create(int n){
        List<String> ls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            ls.add(sb.toString());   
        }
        return ls;
    }
    void print(List<String> ls){
        for (String s : ls) {
            System.out.println(s);
        }
        System.out.println();
    }






    List<List<String>> ans = new ArrayList<>();
    boolean[] cols;
    boolean[] diag1;
    boolean[] diag2;

    public List<List<String>> solveNQueensBetter(int n) {

        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(0, n, board);

        return ans;
    }

    void backtrack(int row, int n, char[][] board) {

        // All rows are filled
        if (row == n) {
            List<String> current = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                current.add(new String(board[i]));
            }

            ans.add(current);
            return;
        }

        // Try every column in this row
        for (int col = 0; col < n; col++) {

            int d1 = row + col;
            int d2 = row - col + n - 1;

            // Position is not safe
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Go to next row
            backtrack(row + 1, n, board);

            // Backtrack: remove queen
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }



}
