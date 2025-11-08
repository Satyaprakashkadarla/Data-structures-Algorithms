class Solution {
    public int numberOfPath(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        // dp[i][j][sum] = ways to reach (i,j) with exactly 'sum' coins
        int[][][] dp = new int[n][m][k + 1];
        
        // Initialize starting cell
        if (mat[0][0] <= k) {
            dp[0][0][mat[0][0]] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int sum = 0; sum <= k; sum++) {
                    if (dp[i][j][sum] == 0) continue;
                    
                    // Move right
                    if (j + 1 < m) {
                        int newSum = sum + mat[i][j + 1];
                        if (newSum <= k) {
                            dp[i][j + 1][newSum] += dp[i][j][sum];
                        }
                    }
                    
                    // Move down
                    if (i + 1 < n) {
                        int newSum = sum + mat[i + 1][j];
                        if (newSum <= k) {
                            dp[i + 1][j][newSum] += dp[i][j][sum];
                        }
                    }
                }
            }
        }
        
        return dp[n - 1][m - 1][k];
    }
}