class Solution {
    public int celebrity(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                // Check if i knows j (should be false) and if j knows i (should be true)
                if (mat[i][j] == 1 || mat[j][i] == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}