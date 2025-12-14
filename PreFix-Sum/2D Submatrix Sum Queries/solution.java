import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {
        int N = mat.length;
        if (N == 0) {
            return new ArrayList<>();
        }
        int M = mat[0].length;

        long[][] P = new long[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                P[i][j] = mat[i-1][j-1] + P[i-1][j] + P[i][j-1] - P[i-1][j-1];
            }
        }

        ArrayList<Integer> results = new ArrayList<>();
        
        for (int[] query : queries) {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            
            
            long sum = P[r2 + 1][c2 + 1] 
                     - P[r1][c2 + 1] 
                     - P[r2 + 1][c1] 
                     + P[r1][c1];

            results.add((int) sum);
        }

        return results;
    }
}