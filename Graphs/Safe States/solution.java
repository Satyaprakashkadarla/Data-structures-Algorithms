class Solution {
    public ArrayList<Integer> safeNodes(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> revGraph = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        
        int[] outdegree = new int[V];
        
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            revGraph.get(v).add(u);
            outdegree[u]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (outdegree[i] == 0) {
                queue.add(i);
            }
        }
        
        boolean[] safe = new boolean[V];
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safe[node] = true;
            
            for (int prev : revGraph.get(node)) {
                outdegree[prev]--;
                if (outdegree[prev] == 0) {
                    queue.add(prev);
                }
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safe[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}