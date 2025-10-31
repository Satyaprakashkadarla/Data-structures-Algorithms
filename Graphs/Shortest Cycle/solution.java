import java.util.*;

class Solution {
    public int shortCycle(int V, int[][] edges) {
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
        
        int minCycle = Integer.MAX_VALUE;
        
        // Try each vertex as starting point
        for (int i = 0; i < V; i++) {
            int cycleLength = bfs(i, V, adj);
            if (cycleLength != -1) {
                minCycle = Math.min(minCycle, cycleLength);
            }
        }
        
        return minCycle == Integer.MAX_VALUE ? -1 : minCycle;
    }
    
    private int bfs(int start, int V, List<List<Integer>> adj) {
        int[] parent = new int[V];
        int[] distance = new int[V];
        Arrays.fill(parent, -1);
        Arrays.fill(distance, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distance[start] = 0;
        parent[start] = -1;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adj.get(current)) {
                // If neighbor is not visited
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[current] + 1;
                    parent[neighbor] = current;
                    queue.offer(neighbor);
                } 
                // If neighbor is visited but not the direct parent, we found a cycle
                else if (parent[current] != neighbor) {
                    // Cycle length = distance from current to start + distance from neighbor to start + 1
                    returna distance[current] + distance[neighbor] + 1;
                }
            }
        }
        
        return -1;
    }
}