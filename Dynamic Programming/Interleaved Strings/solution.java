class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int k = s3.length();
        
        // If lengths don't match, it's impossible
        if (m + n != k) {
            return false;
        }
        
        // DP table: dp[i][j] means whether s3[0..i+j-1] is interleaving of s1[0..i-1] and s2[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty strings
        dp[0][0] = true;
        
        // Fill first column (s2 is empty, only use s1)
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        // Fill first row (s1 is empty, only use s2)
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        // Fill the rest of the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Check if current character of s3 matches s1
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }
                
                // Check if current character of s3 matches s2
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        
        return dp[m][n];
    }
}