import java.util.*;

class Solution {
    public int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {

        // graph[node] = {neighbor, cost}
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Existing direction: no reversal
            graph[u].add(new int[]{v, 0});

            // Reverse this edge: cost 1
            graph[v].add(new int[]{u, 1});
        }

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        Deque<Integer> deque = new ArrayDeque<>();

        dist[src] = 0;
        deque.offerFirst(src);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int cost = edge[1];

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;

                    // Cost 0 -> process earlier
                    if (cost == 0) {
                        deque.offerFirst(v);
                    } else {
                        // Cost 1 -> process later
                        deque.offerLast(v);
                    }
                }
            }
        }

        return dist[dst] == INF ? -1 : dist[dst];
    }
}