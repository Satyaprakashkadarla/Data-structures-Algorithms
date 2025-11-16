class Solution {
    public int LCIS(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        
        int[] dp = new int[n]; // dp[j] = length of LCIS ending with b[j]
        
        for (int i = 0; i < m; i++) {
            int current = 0; // best LCIS length that can be extended by a[i]
            
            for (int j = 0; j < n; j++) {
                if (a[i] == b[j]) {
                    dp[j] = Math.max(dp[j], current + 1);
                }
                
                if (a[i] > b[j]) {
                    current = Math.max(current, dp[j]);
                }
            }
        }
        
        int result = 0;
        for (int val : dp) {
            result = Math.max(result, val);
        }
        return result;
    }
}