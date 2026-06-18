class Solution {
    public int findCoverage(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] rowPrefix = new int[n][m];
        int[][] colPrefix = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowPrefix[i][j] = mat[i][j];
                if (j > 0) rowPrefix[i][j] += rowPrefix[i][j - 1];
            }
        }
        
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colPrefix[i][j] = mat[i][j];
                if (i > 0) colPrefix[i][j] += colPrefix[i - 1][j];
            }
        }
        
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    int count = 0;
                    if (j > 0 && rowPrefix[i][j - 1] > 0) count++;
                    if (j < m - 1 && rowPrefix[i][m - 1] - rowPrefix[i][j] > 0) count++;
                    if (i > 0 && colPrefix[i - 1][j] > 0) count++;
                    if (i < n - 1 && colPrefix[n - 1][j] - colPrefix[i][j] > 0) count++;
                    total += count;
                }
            }
        }
        return total;
    }
}