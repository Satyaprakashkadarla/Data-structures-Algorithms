class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007L;

        long[] dp = new long[k + 1];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = Math.min(k, i); j >= 1; j--) {
                // Either:
                // 1. Add point i to an existing segment, or
                // 2. Start a new segment whose left endpoint is i.
                //
                // Number of choices for the second interpretation
                // is represented by the accumulated dp[j-1].
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        // This simple recurrence alone doesn't account for all endpoint
        // configurations, so use the closed-form equivalent below.
        return (int) count(n, k, MOD);
    }

    private long count(int n, int k, long MOD) {
        // Answer = C(n + k - 1, 2k)
        //
        // Each segment needs two endpoints, and adjacent segments
        // are allowed to share an endpoint.
        long ans = 1;

        int r = 2 * k;
        int N = n + k - 1;

        // Compute C(N, r) modulo MOD.
        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        fact[0] = 1;
        for (int i = 1; i <= N; i++)
            fact[i] = fact[i - 1] * i % MOD;

        invFact[N] = pow(fact[N], MOD - 2, MOD);
        for (int i = N; i > 0; i--)
            invFact[i - 1] = invFact[i] * i % MOD;

        ans = fact[N] * invFact[r] % MOD;
        ans = ans * invFact[N - r] % MOD;

        return ans;
    }

    private long pow(long a, long e, long mod) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) != 0)
                res = res * a % mod;
            a = a * a % mod;
            e >>= 1;
        }
        return res;
    }
}
