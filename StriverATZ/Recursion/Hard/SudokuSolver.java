package StriverATZ.Recursion.Hard;

public class SudokuSolver {
    boolean[][] rowmap;
    boolean[][] colmap;
    boolean[][] boxmap;
    public void solveSudokuBetter(char[][] board) {
        rowmap=new boolean[9][9];
        colmap=new boolean[9][9];
        boxmap=new boolean[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.')continue;
                int val=board[i][j]-'0';
                int box=(i/3)*3+(j/3);
                rowmap[i][val-1]=true;
                colmap[j][val-1]=true;
                boxmap[box][val-1]=true;
            }
        }
        helper(board);
    }
    boolean helper(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.')continue;
                for(char k='1';k<='9';k++){
                    int idx=k-'1';
                    int box=(i/3)*3+(j/3);
                    if(!rowmap[i][idx] && !colmap[j][idx] && !boxmap[box][idx]){
                    board[i][j]=k;
                    rowmap[i][idx]=true;
                    colmap[j][idx]=true;
                    boxmap[box][idx]=true;
                    if(helper(board)){
                        return true;
                    } 
                    board[i][j]='.';
                    rowmap[i][idx]=false;
                    colmap[j][idx]=false;
                    boxmap[box][idx]=false;
                    }
                }
                return false;
            }
        }
        return true;
    }













    public void solveSudoku(char[][] board) {
        // print(board);
        helper(board, 0, 0);
    }

    boolean helper(char[][] board, int col, int row) {

        // Finished all rows
        if (row == 9) {
            // print(board);
            return true;
        }

        // Current cell is already filled
        if (board[row][col] != '.') {
            if (col == 8) {
                return helper(board, 0, row + 1);
            } else {
                return helper(board, col + 1, row);
            }
        }

        // Try 1-9
        for (int i = 1; i <= 9; i++) {

            if (!canPlace(board, i, col, row)) {
                continue;
            }

            board[row][col] = (char) ('0' + i);

            boolean check;

            if (col == 8) {
                check = helper(board, 0, row + 1);
            } else {
                check = helper(board, col + 1, row);
            }

            if (check) {
                return true;
            }

            // Backtrack
            board[row][col] = '.';
        }

        return false;
    }

    boolean canPlace(char[][] board, int i, int x, int y) {

        char ch = (char) ('0' + i);

        // Check row and column
        for (int a = 0; a < 9; a++) {
            if (board[y][a] == ch || board[a][x] == ch) {
                return false;
            }
        }

        // Check 3x3 box
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;

        for (int r = startY; r < startY + 3; r++) {
            for (int c = startX; c < startX + 3; c++) {
                if (board[r][c] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    
    void print(char[][] board){
        for(char[] ch: board){
            for(char c : ch){
                System.out.print(c+" ");
            }
            System.out.println();
        }
    }
}
