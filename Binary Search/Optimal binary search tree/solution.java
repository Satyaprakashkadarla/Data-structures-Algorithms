class Solution {
    public int minCost(int keys[], int freq[]) {
        int n = keys.length;
        if (n == 0) return 0;
        
        // Prefix sum of frequencies
        int[] prefixSum = new int[n];
        prefixSum[0] = freq[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + freq[i];
        }
        
        // Helper function to get sum of freq from i to j in O(1)
        java.util.function.BiFunction<Integer, Integer, Integer> sumFreq = (i, j) -> {
            if (i > j) return 0;
            return prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]);
        };
        
        // dp[i][j] = min cost for keys[i..j]
        int[][] dp = new int[n][n];
        
        // Length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = freq[i];
        }
        
        // Length from 2 to n
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                int freqSum = sumFreq.apply(i, j);
                
                // Try each key as root
                for (int r = i; r <= j; r++) {
                    int leftCost = (r > i) ? dp[i][r - 1] : 0;
                    int rightCost = (r < j) ? dp[r + 1][j] : 0;
                    int total = leftCost + rightCost + freqSum;
                    if (total < dp[i][j]) {
                        dp[i][j] = total;
                    }
                }
            }
        }
        
        return dp[0][n - 1];
    }
}