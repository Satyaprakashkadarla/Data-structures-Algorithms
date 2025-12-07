class Solution {
    int distinctSubseq(String str) {
        int MOD = 1000000007;
        int n = str.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        for (int i = 1; i <= n; i++) {
            char ch = str.charAt(i - 1);
            dp[i] = (2 * dp[i - 1]) % MOD;
            
            int idx = ch - 'a';
            if (last[idx] != -1) {
                dp[i] = (dp[i] - dp[last[idx]] + MOD) % MOD;
            }
            last[idx] = i - 1;
        }
        
        return (int) dp[n];
    }
}