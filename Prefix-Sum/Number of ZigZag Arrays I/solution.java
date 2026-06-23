class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 2];
        long[] down = new long[m + 2];

        // Length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // previous value is smaller
            down[v] = m - v;    // previous value is larger
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m + 2];
            long[] newDown = new long[m + 2];

            long[] prefDown = new long[m + 2];
            for (int v = 1; v <= m; v++) {
                prefDown[v] = (prefDown[v - 1] + down[v]) % MOD;
            }

            for (int v = 1; v <= m; v++) {
                newUp[v] = prefDown[v - 1];
            }

            long[] suffUp = new long[m + 3];
            for (int v = m; v >= 1; v--) {
                suffUp[v] = (suffUp[v + 1] + up[v]) % MOD;
            }

            for (int v = 1; v <= m; v++) {
                newDown[v] = suffUp[v + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}