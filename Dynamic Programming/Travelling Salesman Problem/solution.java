class Solution {
    public int tsp(int[][] cost) {
        int n = cost.length;
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1 << 0][0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) continue;
                if (dp[mask][i] == INF) continue;
                for (int j = 0; j < n; j++) {
                    if ((mask & (1 << j)) != 0) continue;
                    int newMask = mask | (1 << j);
                    int newCost = dp[mask][i] + cost[i][j];
                    
                    if (newCost < dp[newMask][j]) {
                        dp[newMask][j] = newCost;
                    }
                }
            }
        }
        int fullMask = (1 << n) - 1;
        int minCost = INF;
        for (int i = 0; i < n; i++) {
            if (dp[fullMask][i] != INF) {
                minCost = Math.min(minCost, dp[fullMask][i] + cost[i][0]);
            }
        }
        return minCost;
    }
}