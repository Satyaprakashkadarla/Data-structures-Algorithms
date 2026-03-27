class Solution {
    public int maxChocolate(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int[][][] dp = new int[n][m][m];
        
        // Initialize with large negative
        for (int i = 0; i < n; i++)
            for (int c1 = 0; c1 < m; c1++)
                for (int c2 = 0; c2 < m; c2++)
                    dp[i][c1][c2] = -1_000_000;
        
        // First row: both at start positions
        dp[0][0][m - 1] = grid[0][0] + grid[0][m - 1];
        
        for (int i = 1; i < n; i++) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int c2 = 0; c2 < m; c2++) {
                    int maxPrev = -1_000_000;
                    for (int pc1 = Math.max(0, c1 - 1); pc1 <= Math.min(m - 1, c1 + 1); pc1++) {
                        for (int pc2 = Math.max(0, c2 - 1); pc2 <= Math.min(m - 1, c2 + 1); pc2++) {
                            maxPrev = Math.max(maxPrev, dp[i - 1][pc1][pc2]);
                        }
                    }
                    if (maxPrev > -1_000_000) {
                        int collect = grid[i][c1] + (c1 == c2 ? 0 : grid[i][c2]);
                        dp[i][c1][c2] = maxPrev + collect;
                    }
                }
            }
        }
        
        int ans = 0;
        for (int c1 = 0; c1 < m; c1++)
            for (int c2 = 0; c2 < m; c2++)
                ans = Math.max(ans, dp[n - 1][c1][c2]);
        return ans;
    }
}