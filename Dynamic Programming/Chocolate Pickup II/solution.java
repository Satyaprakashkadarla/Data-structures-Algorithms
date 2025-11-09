class Solution {
    public int chocolatePickup(int[][] mat) {
        int n = mat.length;
        int[][][] dp = new int[2 * n][n][n];
        
        // Initialize with -1 meaning unreachable
        for (int k = 0; k < 2 * n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[k][i][j] = -1;
                }
            }
        }
        
        // Start state
        if (mat[0][0] != -1) {
            dp[0][0][0] = mat[0][0];
        } else {
            return 0;
        }
        
        for (int k = 1; k < 2 * n - 1; k++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    int j1 = k - i1;
                    int j2 = k - i2;
                    
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n) continue;
                    
                    // If any cell is blocked, skip
                    if (mat[i1][j1] == -1 || mat[i2][j2] == -1) {
                        dp[k][i1][i2] = -1;
                        continue;
                    }
                    
                    int best = -1;
                    
                    // Possible previous states:
                    // (i1-1, j1) & (i2-1, j2)
                    if (i1 - 1 >= 0 && i2 - 1 >= 0 && dp[k-1][i1-1][i2-1] != -1)
                        best = Math.max(best, dp[k-1][i1-1][i2-1]);
                    
                    // (i1-1, j1) & (i2, j2-1)
                    if (i1 - 1 >= 0 && j2 - 1 >= 0 && dp[k-1][i1-1][i2] != -1)
                        best = Math.max(best, dp[k-1][i1-1][i2]);
                    
                    // (i1, j1-1) & (i2-1, j2)
                    if (j1 - 1 >= 0 && i2 - 1 >= 0 && dp[k-1][i1][i2-1] != -1)
                        best = Math.max(best, dp[k-1][i1][i2-1]);
                    
                    // (i1, j1-1) & (i2, j2-1)
                    if (j1 - 1 >= 0 && j2 - 1 >= 0 && dp[k-1][i1][i2] != -1)
                        best = Math.max(best, dp[k-1][i1][i2]);
                    
                    if (best == -1) {
                        dp[k][i1][i2] = -1;
                    } else {
                        // Add chocolates
                        if (i1 == i2 && j1 == j2) {
                            // Same cell
                            dp[k][i1][i2] = best + mat[i1][j1];
                        } else {
                            dp[k][i1][i2] = best + mat[i1][j1] + mat[i2][j2];
                        }
                    }
                }
            }
        }
        
        int result = dp[2*n-2][n-1][n-1];
        return result == -1 ? 0 : Math.max(result, 0);
    }
}