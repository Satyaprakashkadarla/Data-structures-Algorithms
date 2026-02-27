class Solution {
    public int countSquare(int[][] mat, int x) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] prefix = new int[n + 1][m + 1];
        
        // Build prefix sums
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        
        int count = 0;
        // Try all squares
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int maxK = Math.min(n - i, m - j);
                for (int k = 1; k <= maxK; k++) {
                    int sum = prefix[i + k][j + k] - prefix[i][j + k] - prefix[i + k][j] + prefix[i][j];
                    if (sum == x) count++;
                }
            }
        }
        return count;
    }
}