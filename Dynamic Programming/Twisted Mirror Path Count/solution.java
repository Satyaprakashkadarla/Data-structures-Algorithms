class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;
        
        // dp[c + 1] stores the number of paths reaching column c 
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int oldDown = dp[c + 1][1];
                int oldLeft = dp[c][0];
                
                if (grid[r][c] == 1) {
                    // Mirror cell reflection logic
                    dp[c + 1][0] = oldDown;
                    dp[c + 1][1] = oldLeft;
                } else {
                    // Empty cell: standard path accumulation from top and left
                    int val = (oldDown + oldLeft) % MOD;
                    dp[c + 1][0] = val;
                    dp[c + 1][1] = val;
                }
            }
        }
        
        return dp[n][0];
    }
}