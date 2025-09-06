class Solution {
    public long minOperations(int[][] queries) {
        long ans = 0;
        long[] p = new long[16];
        p[0] = 1;
        for (int i = 1; i < 16; i++) p[i] = p[i - 1] * 4;
        for (int[] q : queries) {
            long l = q[0], r = q[1], sum = 0;
            for (int t = 1; t <= 15; t++) {
                long L = Math.max(l, p[t - 1]);
                long R = Math.min(r, p[t] - 1);
                if (L <= R) sum += t * (R - L + 1);
            }ans += (sum + 1) / 2;
        }return ans;
    }
}