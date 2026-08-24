class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Total sum = prefix[n - 1]
        int prefix = 0;
        for (int x : stones) {
            prefix += x;
        }

        // dp = best score difference from the current state
        int dp = prefix;

        // Consider prefix sums from n-2 down to 1.
        // A move must remove at least 2 stones.
        prefix -= stones[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix - dp);
            prefix -= stones[i];
        }

        return dp;
    }
}
