class Solution {
    public int maxSumIS(int arr[]) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        
        // Initialize dp array with individual elements
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }
        
        // Fill dp array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }
        
        // Find maximum value in dp array
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, dp[i]);
        }
        
        return maxSum;
    }
}