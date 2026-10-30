
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count]: minimum cost to reach node
        // with count consecutive occurrences of its label
        int[][] dist = new int[n][k + 1];

        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        // Start at node 0, with one occurrence of its label
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long cost = cur[0];
            int u = (int) cur[1];
            int count = (int) cur[2];

            if (cost != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                int nextCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + w;

                if (newCost < dist[v][nextCount]) {
                    dist[v][nextCount] = (int) newCost;
                    pq.offer(new long[]{
                        newCost, v, nextCount
                    });
                }
            }
        }

        return -1;
    }
}
