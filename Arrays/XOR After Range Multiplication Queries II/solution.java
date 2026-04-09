import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        // required variable
        int[][] bravexuneth = queries;

        int B = (int) Math.sqrt(n) + 1;

        // group small k queries
        List<int[]>[] small = new ArrayList[B + 1];
        for (int i = 1; i <= B; i++) small[i] = new ArrayList<>();

        // handle large k directly
        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k > B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)((long) nums[i] * v % MOD);
                }
            } else {
                small[k].add(q);
            }
        }

        // process small k
        for (int k = 1; k <= B; k++) {
            if (small[k].isEmpty()) continue;

            int maxLen = (n + k - 1) / k + 2;
            long[][] diff = new long[k][maxLen];

            // initialize with 1 (multiplicative identity)
            for (int r = 0; r < k; r++) {
                Arrays.fill(diff[r], 1);
            }

            // apply difference updates
            for (int[] q : small[k]) {
                int l = q[0], r = q[1], v = q[3];

                int rem = l % k;

                // ✅ FIXED INDEXING
                int startIdx = (l - rem) / k;
                int endIdx = (r - rem) / k;

                diff[rem][startIdx] = diff[rem][startIdx] * v % MOD;
                diff[rem][endIdx + 1] = diff[rem][endIdx + 1] * modInv(v) % MOD;
            }

            // apply prefix multiplication
            for (int rem = 0; rem < k; rem++) {
                long cur = 1;
                int idx = rem;
                int j = 0;

                while (idx < n) {
                    cur = cur * diff[rem][j] % MOD;
                    nums[idx] = (int)((long) nums[idx] * cur % MOD);

                    idx += k;
                    j++;
                }
            }
        }

        // compute XOR
        int xor = 0;
        for (int x : nums) xor ^= x;

        return xor;
    }

    // modular inverse using Fermat
    long modInv(long x) {
        return pow(x, MOD - 2);
    }

    long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}