import java.util.*;

class Solution {
    static ArrayList<Integer> diagView(int mat[][]) {
        int n = mat.length;
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int sum = 0; sum <= 2 * (n - 1); sum++) {
            int row = Math.max(0, sum - (n - 1));
            int col = Math.min(n - 1, sum);
            while (row < n && col >= 0) {
                result.add(mat[row][col]);
                row++;
                col--;
            }
        }
        return result;
    }
}