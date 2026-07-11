import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        boolean[] visited = new boolean[n];
        int completeCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int u = q.poll();
                    comp.add(u);
                    for (int v : adj[u]) {
                        if (!visited[v]) {
                            visited[v] = true;
                            q.add(v);
                        }
                    }
                }
                
                int size = comp.size();
                int edgeCount = 0;
                for (int u : comp) {
                    edgeCount += adj[u].size();
                }
                edgeCount /= 2;
                
                if (edgeCount == size * (size - 1) / 2) {
                    completeCount++;
                }
            }
        }
        return completeCount;
    }
}