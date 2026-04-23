import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[2 * n];
        boolean[] anti = new boolean[2 * n];
        backtrack(0, n, board, result, col, diag, anti);
        return result;
    }
    private void backtrack(int row, int n, char[][] board,
     List<List<String>> result,
     boolean[] col, boolean[] diag, boolean[] anti) {
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board) {
                temp.add(new String(r));
            }
            result.add(temp);
            return;
        }
        for (int c = 0; c < n; c++) { 
            if (col[c] || diag[row + c] || anti[n - 1 + row - c]) continue;
            board[row][c] = 'Q';
            col[c] = diag[row + c] = anti[n - 1 + row - c] = true;
            backtrack(row + 1, n, board, result, col, diag, anti);
            board[row][c] = '.';
            col[c] = diag[row + c] = anti[n - 1 + row - c] = false;
        }
    }
}