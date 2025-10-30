class Solution {
    public void fill(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        // Mark boundary-connected 'O's
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 'O') dfs(grid, i, 0, n, m);
            if (grid[i][m - 1] == 'O') dfs(grid, i, m - 1, n, m);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 'O') dfs(grid, 0, j, n, m);
            if (grid[n - 1][j] == 'O') dfs(grid, n - 1, j, n, m);
        }
        
        // Flip remaining 'O' to 'X', and 'T' back to 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'O') grid[i][j] = 'X';
                if (grid[i][j] == 'T') grid[i][j] = 'O';
            }
        }
    }
    
    private void dfs(char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 'O') return;
        
        grid[i][j] = 'T'; // temporary mark
        
        dfs(grid, i + 1, j, n, m);
        dfs(grid, i - 1, j, n, m);
        dfs(grid, i, j + 1, n, m);
        dfs(grid, i, j - 1, n, m);
    }
}