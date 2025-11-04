class Solution {
    public int minCost(int[] height) {
        int n = height.length;
        if (n == 1) return 0;
        
        int prev2 = 0; // dp[i-2]
        int prev1 = Math.abs(height[1] - height[0]); // dp[i-1]
        
        for (int i = 2; i < n; i++) {
            int curr = Math.min(
                prev1 + Math.abs(height[i] - height[i-1]),
                prev2 + Math.abs(height[i] - height[i-2])
            );
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}