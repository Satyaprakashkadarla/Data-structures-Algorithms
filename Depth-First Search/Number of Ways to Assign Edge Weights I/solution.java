class Solution {
    private static final long MOD = 1_000_000_007L;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        java.util.List<Integer>[] graph = new java.util.ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new java.util.ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // BFS to find maximum depth from root (node 1)
        int[] depth = new int[n + 1];
        java.util.Arrays.fill(depth, -1);

        java.util.ArrayDeque<Integer> q = new java.util.ArrayDeque<>();
        q.offer(1);
        depth[1] = 0;

        int maxDepth = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : graph[u]) {
                if (depth[v] == -1) {
                    depth[v] = depth[u] + 1;
                    maxDepth = Math.max(maxDepth, depth[v]);
                    q.offer(v);
                }
            }
        }

        // Number of assignments with odd total cost on a path of length maxDepth:
        // 2^(maxDepth - 1)
        return (int) modPow(2, maxDepth - 1);
    }

    private long modPow(long base, int exp) {
        long res = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return res;
    }
}