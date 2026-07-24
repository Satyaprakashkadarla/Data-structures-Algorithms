class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        boolean[] ans = new boolean[MAX];

        // Single element values are always obtainable
        for (int x : nums) {
            ans[x] = true;
        }

        // DP for XOR of exactly 3 distinct indices
        for (int x : nums) {
            for (int cnt = 2; cnt >= 0; cnt--) {
                for (int xr = 0; xr < MAX; xr++) {
                    if (dp[cnt][xr]) {
                        dp[cnt + 1][xr ^ x] = true;
                    }
                }
            }
        }

        for (int xr = 0; xr < MAX; xr++) {
            if (dp[3][xr]) ans[xr] = true;
        }

        int res = 0;
        for (boolean b : ans) {
            if (b) res++;
        }
        return res;
    }
}
