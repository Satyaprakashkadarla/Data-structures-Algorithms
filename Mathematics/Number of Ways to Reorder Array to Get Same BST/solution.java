
class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations C(n, k) modulo MOD
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1]
                            + comb[i - 1][j]) % MOD;
            }
        }

        long ways = countWays(nums);
        return (int) ((ways - 1 + MOD) % MOD);
    }

    private long countWays(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return 1;
        }

        int root = nums[0];

        int[] left = new int[n];
        int[] right = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }

        // Copy only the populated portions
        int[] leftSub = new int[l];
        int[] rightSub = new int[r];

        System.arraycopy(left, 0, leftSub, 0, l);
        System.arraycopy(right, 0, rightSub, 0, r);

        long leftWays = countWays(leftSub);
        long rightWays = countWays(rightSub);

        // Interleave left and right subtree elements
        long interleavings = comb[l + r][l];

        return (((interleavings * leftWays) % MOD) * rightWays) % MOD;
    }
}