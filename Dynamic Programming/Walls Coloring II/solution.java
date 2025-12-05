class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        
        if (k == 0) return -1; // no colors available
        if (k == 1 && n > 1) return -1;
        
        int[] prev = new int[k];
        
        // Initialize for first wall
        for (int c = 0; c < k; c++) {
            prev[c] = costs[0][c];
        }
        
        for (int i = 1; i < n; i++) {
            // Find min1 and min2 in prev
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            for (int c = 0; c < k; c++) {
                if (prev[c] < min1) {
                    min2 = min1;
                    min1 = prev[c];
                } else if (prev[c] < min2) {
                    min2 = prev[c];
                }
            }
            
            int[] curr = new int[k];
            for (int c = 0; c < k; c++) {
                // If prev[c] is the minimum, we must use second minimum
                if (prev[c] == min1) {
                    curr[c] = costs[i][c] + min2;
                } else {
                    curr[c] = costs[i][c] + min1;
                }
            }
            prev = curr;
        }
        
        int ans = Integer.MAX_VALUE;
        for (int c = 0; c < k; c++) {
            ans = Math.min(ans, prev[c]);
        }
        return ans;
    }
}