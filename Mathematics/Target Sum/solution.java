class Solution {
    public int totalWays(int[] arr, int target) {
        int total = 0;
        for (int num : arr) total += num;
        
        if ((target + total) % 2 != 0 || target + total < 0) return 0;
        int P = (target + total) / 2;
        
        int[] dp = new int[P + 1];
        dp[0] = 1;
        
        for (int num : arr) {
            for (int s = P; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        return dp[P];
    }
}