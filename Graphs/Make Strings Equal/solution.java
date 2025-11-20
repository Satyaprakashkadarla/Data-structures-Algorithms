class Solution {
    public int minCost(String s, String t, char[][] transform, int[] cost) {
        int n = s.length();
        
        // Create adjacency matrix for 26 letters
        int[][] dist = new int[26][26];
        
        // Initialize with large values (infinity)
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Transforming to itself costs 0
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Add given transformations
        for (int i = 0; i < transform.length; i++) {
            int from = transform[i][0] - 'a';
            int to = transform[i][1] - 'a';
            dist[from][to] = Math.min(dist[from][to], cost[i]);
        }
        
        // Floyd-Warshall algorithm to find minimum cost between all pairs
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 0; j < 26; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // Calculate total cost
        int totalCost = 0;
        
        for (int i = 0; i < n; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            if (sChar == tChar) {
                continue; // No cost if characters already match
            }
            
            int sIdx = sChar - 'a';
            int tIdx = tChar - 'a';
            
            // Find minimum cost to make both characters equal
            int minCostForPosition = Integer.MAX_VALUE;
            
            // Option 1: Transform s[i] to t[i]
            if (dist[sIdx][tIdx] != Integer.MAX_VALUE) {
                minCostForPosition = Math.min(minCostForPosition, dist[sIdx][tIdx]);
            }
            
            // Option 2: Transform t[i] to s[i]
            if (dist[tIdx][sIdx] != Integer.MAX_VALUE) {
                minCostForPosition = Math.min(minCostForPosition, dist[tIdx][sIdx]);
            }
            
            // Option 3: Transform both to a common character
            for (int k = 0; k < 26; k++) {
                if (dist[sIdx][k] != Integer.MAX_VALUE && dist[tIdx][k] != Integer.MAX_VALUE) {
                    minCostForPosition = Math.min(minCostForPosition, dist[sIdx][k] + dist[tIdx][k]);
                }
            }
            
            if (minCostForPosition == Integer.MAX_VALUE) {
                return -1; // No way to make these characters equal
            }
            
            totalCost += minCostForPosition;
        }
        
        return totalCost;
    }
}