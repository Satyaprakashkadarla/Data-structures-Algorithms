import java.util.*;

class Solution {
    public int longestCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }
        
        int[] state = new int[V]; // 0 unvisited, 1 visiting, 2 visited
        int[] dist = new int[V];
        int maxCycle = -1;
        
        for (int i = 0; i < V; i++) {
            if (state[i] == 0) {
                maxCycle = Math.max(maxCycle, dfs(i, adj, state, dist, 0));
            }
        }
        return maxCycle;
    }
    
    private int dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] state, int[] dist, int time) {
        state[node] = 1;
        dist[node] = time;
        int maxCycle = -1;
        
        for (int next : adj.get(node)) {
            if (state[next] == 0) {
                maxCycle = Math.max(maxCycle, dfs(next, adj, state, dist, time + 1));
            } else if (state[next] == 1) {
                maxCycle = Math.max(maxCycle, time - dist[next] + 1);
            }
        }
        
        state[node] = 2;
        return maxCycle;
    }
}