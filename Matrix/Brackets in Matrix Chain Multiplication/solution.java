class Solution {
    public String matrixChainOrder(int arr[]) {
        int n = arr.length;
        int m = n - 1; // number of matrices
        if (m == 1) {
            return "A"; // only one matrix
        }
        
        int[][] dp = new int[m][m];
        int[][] split = new int[m][m];
        
        // dp[i][i] = 0 already by default
        
        for (int len = 2; len <= m; len++) {
            for (int i = 0; i <= m - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + arr[i] * arr[k+1] * arr[j+1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k;
                    }
                }
            }
        }
        
        // Build the string recursively
        return buildString(0, m-1, split);
    }
    
    private String buildString(int i, int j, int[][] split) {
        if (i == j) {
            return String.valueOf((char)('A' + i));
        }
        int k = split[i][j];
        String left = buildString(i, k, split);
        String right = buildString(k+1, j, split);
        return "(" + left + right + ")";
    }
}