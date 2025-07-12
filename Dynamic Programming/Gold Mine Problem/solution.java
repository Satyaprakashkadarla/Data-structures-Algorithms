class Solution {
    public int maxGold(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Traverse from second last column to the first column
        for (int y = m - 2; y >= 0; y--) {
            for (int x = 0; x < n; x++) {
                int maxPrev = 0;

                // right-up
                if (x - 1 >= 0 && y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x - 1][y + 1]);
                }

                // right
                if (y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x][y + 1]);
                }

                // right-down
                if (x + 1 < n && y + 1 < m) {
                    maxPrev = Math.max(maxPrev, mat[x + 1][y + 1]);
                }

                mat[x][y] += maxPrev;
            }
        }

        // Find the max in the first column
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, mat[i][0]);
        }

        return result;
    }
}