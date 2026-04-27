class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dirs = {
            {},
            {{0,-1},{0,1}},     
            {{-1,0},{1,0}},     
            {{0,-1},{1,0}},     
            {{0,1},{1,0}},      
            {{0,-1},{-1,0}},    
            {{0,1},{-1,0}}      
        };
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, grid, visited, dirs);
    }

    private boolean dfs(int i, int j, int[][] grid, boolean[][] visited, int[][][] dirs) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) return true;
        visited[i][j] = true;
        for (int[] d : dirs[grid[i][j]]) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni < 0 || nj < 0 || ni >= m || nj >= n || visited[ni][nj])
                continue;
            for (int[] back : dirs[grid[ni][nj]]) {
                if (ni + back[0] == i && nj + back[1] == j) {
                    if (dfs(ni, nj, grid, visited, dirs))
                        return true;
                }
            }
        }
        return false;
    }
}