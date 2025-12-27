class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int low = mat[0][0];
        int high = mat[n - 1][n - 1];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = countLessEqual(mat, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    
    private int countLessEqual(int[][] mat, int target) {
        int n = mat.length;
        int count = 0;
        int row = 0, col = n - 1;
        
        while (row < n && col >= 0) {
            if (mat[row][col] <= target) {
                count += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return count;
    }
}