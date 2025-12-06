class Solution {
    public int maximumAmount(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        
        // Fill for length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }
        
        // Fill for length 2
        for (int i = 0; i < n - 1; i++) {
            dp[i][i+1] = Math.max(arr[i], arr[i+1]);
        }
        
        // Fill for lengths > 2
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Pick i
                int pickLeft = arr[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
                // Pick j
                int pickRight = arr[j] + Math.min(dp[i+1][j-1], dp[i][j-2]);
                dp[i][j] = Math.max(pickLeft, pickRight);
            }
        }
        
        return dp[0][n-1];
    }
}