import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {
        int n = mat.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        // Initialize result matrix with 0s
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                res.get(i).add(0); // placeholder
            }
        }
        
        // Fill with transpose values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res.get(i).set(j, mat[j][i]);
            }
        }
        
        return res;
    }
}