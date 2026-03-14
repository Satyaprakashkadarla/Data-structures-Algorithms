import java.util.*;

class Solution {
    public boolean isEularCircuitExist(int v, ArrayList<ArrayList<Integer>> adj) {
        int[] degree = new int[v];
        for (int i = 0; i < v; i++) {
            degree[i] = adj.get(i).size();
        }
        
        // Check if all degrees are even
        for (int d : degree) {
            if (d % 2 != 0) return false;
        }
        
        // Check connectivity for non-zero degree vertices
        boolean[] visited = new boolean[v];
        int start = -1;
        for (int i = 0; i < v; i++) {
            if (degree[i] > 0) {
                start = i;
                break;
            }
        }
        if (start == -1) return true; // no edges, trivial circuit
        
        // BFS/DFS from start
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w : adj.get(u)) {
                if (!visited[w]) {
                    visited[w] = true;
                    q.add(w);
                }
            }
        }
        
        // Check all non-zero degree vertices visited
        for (int i = 0; i < v; i++) {
            if (degree[i] > 0 && !visited[i]) return false;
        }
        
        return true;
    }
}