class Solution {
    public int minSquares(int n) {
        int[] dp = new int[n + 1];
        
        // Initialize with worst case: all 1's
        for (int i = 0; i <= n; i++) {
            dp[i] = i; // 1+1+1... i times
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
}