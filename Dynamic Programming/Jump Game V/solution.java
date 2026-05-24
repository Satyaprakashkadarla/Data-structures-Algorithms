class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        
        int ans = 1;
        
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(arr, d, i, dp));
        }
        
        return ans;
    }
    
    private int dfs(int[] arr, int d, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }
        
        int n = arr.length;
        int max = 1;
        
        // Move right
        for (int j = i + 1; j <= Math.min(i + d, n - 1); j++) {
            if (arr[j] >= arr[i]) {
                break;
            }
            
            max = Math.max(max, 1 + dfs(arr, d, j, dp));
        }
        
        // Move left
        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {
            if (arr[j] >= arr[i]) {
                break;
            }
            
            max = Math.max(max, 1 + dfs(arr, d, j, dp));
        }
        
        dp[i] = max;
        return max;
    }
}