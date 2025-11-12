class Solution {
    public boolean wildCard(String txt, String pat) {
        int m = txt.length();
        int n = pat.length();
        
        boolean[] dp = new boolean[n + 1];
        boolean[] prev = new boolean[n + 1];
        
        // Base case: empty pattern with empty text
        prev[0] = true;
        
        // For empty text, pattern must be all '*'
        for (int j = 1; j <= n; j++) {
            if (pat.charAt(j - 1) == '*') {
                prev[j] = prev[j - 1];
            } else {
                prev[j] = false;
            }
        }
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            dp[0] = false; // empty pattern doesn't match non-empty text
            for (int j = 1; j <= n; j++) {
                char pChar = pat.charAt(j - 1);
                char tChar = txt.charAt(i - 1);
                
                if (pChar == tChar || pChar == '?') {
                    dp[j] = prev[j - 1];
                } else if (pChar == '*') {
                    dp[j] = dp[j - 1] || prev[j];
                } else {
                    dp[j] = false;
                }
            }
            // Swap arrays
            boolean[] temp = prev;
            prev = dp;
            dp = temp;
        }
        
        return prev[n];
    }
}