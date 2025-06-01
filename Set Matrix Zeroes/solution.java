class Solution {
    
    // Function to set the matrix zeroes as per the problem's requirement
    public void setMatrixZeroes(int[][] mat) {
        
        // Get the number of rows (r) and columns (c) in the matrix
        int r = mat.length, c = mat[0].length;
        
        // This flag will track if the first column needs to be set to zeroes
        boolean isFirstColZero = false;
        
        // Step 1: Traverse the matrix to mark the rows and columns to be zeroed.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                
                // If we encounter a zero in the matrix
                if (mat[i][j] == 0) {
                    
                    // If the zero is in the first column, mark the flag.
                    if (j == 0) {
                        isFirstColZero = true;
                    } 
                    // Otherwise, mark the first cell of the respective row and column as zero.
                    else {
                        mat[i][0] = 0; // Mark the first cell of the row
                        mat[0][j] = 0; // Mark the first cell of the column
                    }
                }
            }
        }
        
        // Step 2: Traverse the matrix again to set the elements to zero based on the marks in the first row and column.
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                
                // If the first cell of the row or column is zero, set the current cell to zero.
                if (mat[i][0] == 0 || mat[0][j] == 0) {
                    mat[i][j] = 0;
                }
            }
        }
        
        // Step 3: Handle the first row separately (because it was used to mark other rows).
        if (mat[0][0] == 0) {
            for (int i = 0; i < c; i++) {
                mat[0][i] = 0; // Set all elements in the first row to zero if mat[0][0] is zero.
            }
        }
        
        // Step 4: Handle the first column separately (because it was used to mark other columns).
        if (isFirstColZero) {
            for (int i = 0; i < r; i++) {
                mat[i][0] = 0; // Set all elements in the first column to zero if flag is true.
            }
        }
    }
}
