import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        BitSet[] dp = new BitSet[n];

        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(1024);
        }

        dp[0].set(grid[0][0]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                BitSet cur = new BitSet(1024);
                int x = grid[i][j];

                if (i > 0) {
                    for (int v = dp[j].nextSetBit(0);
                         v >= 0;
                         v = dp[j].nextSetBit(v + 1)) {
                        cur.set(v ^ x);
                    }
                }

                if (j > 0) {
                    for (int v = dp[j - 1].nextSetBit(0);
                         v >= 0;
                         v = dp[j - 1].nextSetBit(v + 1)) {
                        cur.set(v ^ x);
                    }
                }

                dp[j] = cur;
            }
        }

        return dp[n - 1].nextSetBit(0);
    }
}