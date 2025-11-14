class Solution {
    public static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        
        // Check if it's possible to merge all piles into one
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        
        // Prefix sum array for quick range sum calculation
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }
        
        // DP table: dp[i][j] = min cost to merge stones[i..j]
        int[][] dp = new int[n][n];
        
        // Initialize with large values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // Single pile costs 0 to merge
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        // Iterate over different lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Try all possible splits where we can merge
                for (int mid = i; mid < j; mid += k - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                
                // If we can merge the entire segment into one pile
                if ((len - 1) % (k - 1) == 0) {
                    dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
        }
        
        return dp[0][n - 1];
    }
}