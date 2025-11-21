import java.util.*;

class Solution {
    public int shortestPath(int V, int a, int b, int[][] edges) {
        // Build adjacency list for straight edges
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w1 = edge[2];
            graph.get(u).add(new int[]{v, w1});
            graph.get(v).add(new int[]{u, w1});
        }
        
        // Case 1: No curved edges (only straight edges)
        int[] distFromA = dijkstra(V, a, graph);
        int[] distFromB = dijkstra(V, b, graph);
        
        int result = Integer.MAX_VALUE;
        
        // If there's a path using only straight edges
        if (distFromA[b] != Integer.MAX_VALUE) {
            result = Math.min(result, distFromA[b]);
        }
        
        // Case 2: Exactly one curved edge
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w2 = edge[3];
            
            // Use curved edge from u to v
            if (distFromA[u] != Integer.MAX_VALUE && distFromB[v] != Integer.MAX_VALUE) {
                result = Math.min(result, distFromA[u] + w2 + distFromB[v]);
            }
            
            // Use curved edge from v to u (undirected graph)
            if (distFromA[v] != Integer.MAX_VALUE && distFromB[u] != Integer.MAX_VALUE) {
                result = Math.min(result, distFromA[v] + w2 + distFromB[u]);
            }
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private int[] dijkstra(int V, int source, List<List<int[]>> graph) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.offer(new int[]{source, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0], distance = current[1];
            
            if (distance > dist[u]) continue;
            
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                int newDist = dist[u] + weight;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{v, newDist});
                }
            }
        }
        
        return dist;
    }
}