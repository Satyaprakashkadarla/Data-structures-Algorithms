import java.util.Arrays;

class Solution {
    public int minCutCost(int n, int[] cuts) {
        int m = cuts.length;
        
        // Create new array with endpoints
        int[] sortedCuts = new int[m + 2];
        sortedCuts[0] = 0;
        sortedCuts[m + 1] = n;
        System.arraycopy(cuts, 0, sortedCuts, 1, m);
        Arrays.sort(sortedCuts);
        
        // DP table: dp[i][j] = min cost to cut between sortedCuts[i] and sortedCuts[j]
        int size = m + 2;
        int[][] dp = new int[size][size];
        
        // Initialize with large values
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        // Base case: no cuts between adjacent points
        for (int i = 0; i < size - 1; i++) {
            dp[i][i + 1] = 0;
        }
        
        // Fill the DP table for different lengths
        for (int len = 2; len < size; len++) {
            for (int i = 0; i + len < size; i++) {
                int j = i + len;
                
                // Try all possible first cuts between i and j
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + (sortedCuts[j] - sortedCuts[i]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][size - 1];
    }
}