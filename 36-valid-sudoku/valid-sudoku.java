class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') continue;
                String row = num + " in row " + i;
                String col = num + " in col " + j;
                String box = num + " in box " + (i/3) + "-" + (j/3); 
                if (!set.add(row) || !set.add(col) || !set.add(box)) {
                    return false;
                }
            }
        }
        return true;
    }
}