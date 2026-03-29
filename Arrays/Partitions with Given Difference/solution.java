class Solution {
    public int countPartitions(int[] arr, int diff) {
        int total = 0;
        for (int num : arr) total += num;
        
        if ((total + diff) % 2 != 0 || total + diff < 0) return 0;
        int target = (total + diff) / 2;
        
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int num : arr) {
            for (int s = target; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        return dp[target];
    }
}