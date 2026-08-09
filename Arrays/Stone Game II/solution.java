class Solution {
    private int[][] dp;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffix = new int[n + 1];
        dp = new int[n][n + 1];

        // Suffix sums
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1);
    }

    private int solve(int i, int m) {
        if (i >= n) {
            return 0;
        }

        // Can take all remaining piles.
        if (i + 2 * m >= n) {
            return suffix[i];
        }

        if (dp[i][m] != 0) {
            return dp[i][m];
        }

        int best = 0;

        for (int x = 1; x <= 2 * m && i + x <= n; x++) {
            int nextM = Math.max(m, x);

            // Current player's stones =
            // total remaining - maximum opponent can get.
            int current = suffix[i] - solve(i + x, nextM);

            best = Math.max(best, current);
        }

        return dp[i][m] = best;
    }
}