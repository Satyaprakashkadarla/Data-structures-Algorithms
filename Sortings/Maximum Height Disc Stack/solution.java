import java.util.*;

class Solution {
    public int maxStackHeight(int[] r, int[] h) {
        int n = r.length;

        int[][] discs = new int[n][2];
        for (int i = 0; i < n; i++) {
            discs[i][0] = r[i];
            discs[i][1] = h[i];
        }

        // Sort by radius
        Arrays.sort(discs, (a, b) -> Integer.compare(a[0], b[0]));

        // h[i] <= 1000, so Fenwick tree size can be 1001
        int[] bit = new int[1002];
        int ans = 0;

        for (int i = 0; i < n; ) {
            int j = i;

            // Find all discs having the same radius
            while (j < n && discs[j][0] == discs[i][0]) {
                j++;
            }

            // Store updates temporarily so equal radii cannot stack
            int[] dp = new int[j - i];

            for (int k = i; k < j; k++) {
                int height = discs[k][1];

                // Maximum stack height with previous height < current height
                dp[k - i] = height + query(bit, height - 1);
                ans = Math.max(ans, dp[k - i]);
            }

            // Apply updates only after processing this radius group
            for (int k = i; k < j; k++) {
                update(bit, discs[k][1], dp[k - i]);
            }

            i = j;
        }

        return ans;
    }

    // Maximum value for heights <= index
    private int query(int[] bit, int index) {
        int max = 0;

        while (index > 0) {
            max = Math.max(max, bit[index]);
            index -= index & -index;
        }

        return max;
    }

    // Update position with maximum value
    private void update(int[] bit, int index, int value) {
        while (index < bit.length) {
            bit[index] = Math.max(bit[index], value);
            index += index & -index;
        }
    }
}
