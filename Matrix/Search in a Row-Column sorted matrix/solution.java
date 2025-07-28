class Solution {
    // This method returns true if the element x is found in the matrix 'mat', otherwise false
    public static boolean matSearch(int mat[][], int x) {
        
        // Initialize row pointer 'i' at the first row (0th index)
        // Initialize column pointer 'j' at the last column (last index)
        int i = 0, j = mat[0].length - 1;
        
        // Iterate while the row index is within bounds and the column index is non-negative
        while(i < mat.length && j >= 0){
            
            // If the current element is equal to x, return true
            if(mat[i][j] == x)
                return true;
            
            // If the current element is greater than x, move left (decrease column index)
            if(mat[i][j] > x){
                j--;  // Move left to a smaller element in the same row
            }
            else
                // If the current element is smaller than x, move down (increase row index)
                i++;  // Move down to a larger element in the same column
        }
        
        // If the loop finishes without finding 'x', return false
        return false;
    }
}