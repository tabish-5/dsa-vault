package StriverATZ.Recursion.Hard;

public class WordSearch {
    public boolean exist(char[][] board, String word) {

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (helper(board, word, 0, x, y)) {
                    return true;
                }
            }
        }
    
        return false;    
    }

    boolean helper(char[][] board, String word, int index, int x, int y){
        if(word.length() == index) return true;
        if(board[0].length <= x  ||  x < 0 ) return false;
        if(board.length <= y  ||  y < 0 ) return false;

        if(word.charAt(index) != board[y][x]) return false;

        // Mark current cell as visited
        char temp = board[y][x];
        board[y][x] = '#';

        boolean c1 = helper(board, word, index +1, x +1, y);
        boolean c2 = helper(board, word, index +1, x -1, y);
        boolean c3 = helper(board, word, index +1, x, y +1);
        boolean c4 = helper(board, word, index +1, x, y -1);

        // Backtrack
        board[y][x] = temp;


        return c1 || c2 || c3 || c4;
    }
}
