import java.util.*;

class Solution {
    public int minConnect(int V, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        boolean[] visited = new boolean[V];
        

        List<int[]> components = new ArrayList<>(); // [nodeCount, edgeCount]
        
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                int[] nodeEdgeCount = new int[2]; // [nodes, edges]
                dfs(i, adj, visited, nodeEdgeCount);
                components.add(nodeEdgeCount);
            }
        }
        
        int numComponents = components.size();
        
        // If there's only one component, no operations needed
        if (numComponents == 1) {
            return 0;
        }
        
        int totalExtraEdges = 0;
        for (int[] comp : components) {
            int nodes = comp[0];
            int edgesInComp = comp[1] / 2; 

            int extraEdges = edgesInComp - (nodes - 1);
            totalExtraEdges += Math.max(0, extraEdges);
        }
        int edgesNeeded = numComponents - 1;
        
        if (totalExtraEdges >= edgesNeeded) {
            return edgesNeeded;
        }
        
        return -1;
    }
    
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] nodeEdgeCount) {
        visited[node] = true;
        nodeEdgeCount[0]++; // increment node count
        
        for (int neighbor : adj.get(node)) {
            nodeEdgeCount[1]++; // increment edge count (will be double counted)
            
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, nodeEdgeCount);
            }
        }
    }
}