import java.util.*;

class Solution {
    public int diameter(int V, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // Step 1: BFS from node 0 to find farthest node
        int[] firstBFS = bfs(0, adj, V);
        int farthestNode1 = firstBFS[0];
        
        // Step 2: BFS from farthestNode1 to find farthest node and distance
        int[] secondBFS = bfs(farthestNode1, adj, V);
        int diameter = secondBFS[1];
        
        return diameter;
    }
    
    private int[] bfs(int start, List<List<Integer>> adj, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        dist[start] = 0;
        queue.offer(start);
        
        int farthestNode = start;
        int maxDist = 0;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            for (int v : adj.get(u)) {
                if (dist[v] == -1) { // not visited
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                    if (dist[v] > maxDist) {
                        maxDist = dist[v];
                        farthestNode = v;
                    }
                }
            }
        }
        
        return new int[]{farthestNode, maxDist};
    }
}