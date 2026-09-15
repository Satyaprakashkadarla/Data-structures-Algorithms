class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        // Palindrome DP
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;

                if (s.charAt(l) == s.charAt(r) &&
                    (len <= 2 || pal[l + 1][r - 1])) {
                    pal[l][r] = true;
                }
            }
        }

        // dp[i] = answer for first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int l = 0; l < i; l++) {
                if (i - l >= k && pal[l][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[l] + 1);
                }
            }
        }

        return dp[n];
    }
}
