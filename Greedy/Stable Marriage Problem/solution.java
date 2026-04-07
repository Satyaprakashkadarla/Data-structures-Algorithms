class Solution {
    public int[] stableMarriage(int[][] men, int[][] women) {
        int n = men.length;
        int[] wife = new int[n];
        int[] husband = new int[n];
        int[] nextProposal = new int[n];
        
        // Precompute woman's preference rank
        int[][] rank = new int[n][n];
        for (int w = 0; w < n; w++) {
            for (int pos = 0; pos < n; pos++) {
                rank[w][women[w][pos]] = pos;
            }
        }
        
        for (int i = 0; i < n; i++) {
            wife[i] = -1;
            husband[i] = -1;
        }
        
        // Gale-Shapley
        boolean[] freeMen = new boolean[n];
        for (int i = 0; i < n; i++) freeMen[i] = true;
        int freeCount = n;
        
        while (freeCount > 0) {
            int m;
            for (m = 0; m < n; m++) {
                if (freeMen[m]) break;
            }
            
            int w = men[m][nextProposal[m]];
            nextProposal[m]++;
            
            if (husband[w] == -1) {
                // w is free
                husband[w] = m;
                wife[m] = w;
                freeMen[m] = false;
                freeCount--;
            } else {
                int h = husband[w];
                if (rank[w][m] < rank[w][h]) {
                    // w prefers m over current husband
                    husband[w] = m;
                    wife[m] = w;
                    freeMen[m] = false;
                    wife[h] = -1;
                    freeMen[h] = true;
                }
                // else m remains free
            }
        }
        
        return wife;
    }
}