class Solution {
    public void dfs(int u, int parent, List<List<Integer>> adj, int[] tin, int[] low,
                    boolean[] vis, boolean[] isArticulation, int[] timer) {
        vis[u] = true;
        tin[u] = low[u] = timer[0]++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!vis[v]) {
                dfs(v, u, adj, tin, low, vis, isArticulation, timer);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= tin[u] && parent != -1) {
                    isArticulation[u] = true;
                }

                children++;
            } else {
                low[u] = Math.min(low[u], tin[v]);
            }
        }

        if (parent == -1 && children > 1) {
            isArticulation[u] = true;
        }
    }

    public List<Integer> articulationPoints(int V, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] tin = new int[V], low = new int[V];
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        boolean[] vis = new boolean[V], isArticulation = new boolean[V];
        int[] timer = {0};
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, -1, adj, tin, low, vis, isArticulation, timer);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }
}