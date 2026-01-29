class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 1. Initialize the Graph (26x26 Adjacency Matrix)
        int[][] dis = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            dis[i][i] = 0; // Cost to convert char to itself is 0
        }
        
        // 2. Populate Initial Costs (Direct edges)
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            // Keep the minimum cost if multiple direct edges exist
            dis[u][v] = Math.min(dis[u][v], cost[i]);
        }

        // 3. Floyd-Warshall Algorithm (All-Pairs Shortest Path)
        // Outer loop 'k' MUST be the intermediate node
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dis[i][k] == Integer.MAX_VALUE) continue; // Optimization
                
                for (int j = 0; j < 26; j++) {
                    if (dis[k][j] == Integer.MAX_VALUE) continue; // Optimization
                    
                    // Relaxation: Is going through 'k' cheaper?
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        // 4. Calculate Final Cost
        long totalCost = 0L;
        for (int i = 0; i < source.length(); i++) {
            int c1 = source.charAt(i) - 'a';
            int c2 = target.charAt(i) - 'a';
            
            // If there is no path between characters, return -1
            if (dis[c1][c2] == Integer.MAX_VALUE) {
                return -1L;
            }
            totalCost += (long) dis[c1][c2];
        }

        return totalCost;
    }
}