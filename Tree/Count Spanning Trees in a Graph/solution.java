import java.util.*;

class Solution {
    public int countSpanTree(int n, int[][] edges) {
        if (n == 1) return 1;
        
        int[][] laplacian = new int[n][n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            laplacian[u][u]++;
            laplacian[v][v]++;
            laplacian[u][v]--;
            laplacian[v][u]--;
        }
        
        // Remove last row and column
        int size = n - 1;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = laplacian[i][j];
            }
        }
        
        return determinant(matrix, size);
    }
    
    private int determinant(int[][] matrix, int n) {
        if (n == 1) return matrix[0][0];
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        
        int det = 0;
        for (int col = 0; col < n; col++) {
            int sign = (col % 2 == 0) ? 1 : -1;
            int[][] minor = new int[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int colIdx = 0;
                for (int j = 0; j < n; j++) {
                    if (j == col) continue;
                    minor[i - 1][colIdx++] = matrix[i][j];
                }
            }
            det += sign * matrix[0][col] * determinant(minor, n - 1);
        }
        return det;
    }
}