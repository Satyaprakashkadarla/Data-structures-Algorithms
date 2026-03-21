class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Iterate through the first half of the submatrix rows to flip
        for (int i = 0; i < k / 2; i++) {
            int row1 = x + i;
            int row2 = x + k - 1 - i;
            
            // Swap elements in the specific columns [y, y + k - 1]
            for (int j = 0; j < k; j++) {
                int col = y + j;
                int temp = grid[row1][col];
                grid[row1][col] = grid[row2][col];
                grid[row2][col] = temp;
            }
        }
        return grid;
    }
}
