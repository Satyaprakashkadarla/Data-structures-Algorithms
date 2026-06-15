class Solution {
    public int minimumCost(int[] cost, int w) {
        int n = cost.length;
        int[] dp = new int[w + 1];
        for (int i = 1; i <= w; i++) dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (cost[i] == -1) continue;
            int wt = i + 1;
            for (int j = wt; j <= w; j++) {
                if (dp[j - wt] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - wt] + cost[i]);
                }
            }
        }
        
        return dp[w] == Integer.MAX_VALUE ? -1 : dp[w];
    }
}