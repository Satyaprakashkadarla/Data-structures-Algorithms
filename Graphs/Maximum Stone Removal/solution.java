class Solution {
    public int maxRemove(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int row = stones[i][0];
            int col = stones[i][1];
            rowMap.computeIfAbsent(row, k -> new ArrayList<>()).add(i);
            colMap.computeIfAbsent(col, k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> indices : rowMap.values()) {
            for (int i = 0; i < indices.size(); i++) {
                for (int j = i + 1; j < indices.size(); j++) {
                    int u = indices.get(i);
                    int v = indices.get(j);
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }
            }
        }
        for (List<Integer> indices : colMap.values()) {
            for (int i = 0; i < indices.size(); i++) {
                for (int j = i + 1; j < indices.size(); j++) {
                    int u = indices.get(i);
                    int v = indices.get(j);
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }
            }
        }
        boolean[] visited = new boolean[n];
        int totalRemovable = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int componentSize = dfs(i, graph, visited);
                totalRemovable += (componentSize - 1);
            }
        }
        return totalRemovable;
    }
    private int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, graph, visited);
            }
        }
        return count;
    }
}