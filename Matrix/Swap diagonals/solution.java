class Solution {
    public void swapDiagonal(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            // Swap major and minor diagonal elements for row i
            int temp = mat[i][i];
            mat[i][i] = mat[i][n - 1 - i];
            mat[i][n - 1 - i] = temp;
        }
    }
}