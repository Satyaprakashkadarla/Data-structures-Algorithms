class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        // constants
        int n = grid.length;
        int m = grid[0].length;
        int mod = 1_000_000_007;
		
		// directions -> { right, bottom }
        int[][] directions = new int[][]{{0,1}, {1,0}};
		
		//dp[i][j][l] represents the total number of paths from ith row and jth index where the sum of elements divided by k is l.
        long[][][] dp = new long[n][m][k];
        
        // base case
        dp[n-1][m-1][grid[n-1][m-1] % k]++;
        
        // recurrence relation
        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (i == n-1 && j == m-1) {
                    continue;
                }        
                
                long[] curr = dp[i][j];
				// check right and bottom
                for (int[] dir : directions) {
                    int x = dir[0] + i, y = dir[1] + j;
                    if (x >= n || y >= m) { // check within grid
                        continue;
                    }
                    
                    long[] next = dp[x][y];
                    for (int l = 0; l < k; l++) {
                        int currRemainder = (l + grid[i][j]) % k;
                        curr[currRemainder] += next[l];
                        curr[currRemainder] %= mod;
                    }
                }

            }
        }
        
        return (int) (dp[0][0][0] % mod);
    }
}