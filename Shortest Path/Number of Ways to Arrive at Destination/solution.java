import java.util.*;
class Solution {
    public int countPaths(int V, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], time = edge[2];
            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }
        // Dijkstra's algorithm to find shortest distances
        long[] dist = new long[V];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        // Priority queue: [node, distance]
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[0];
            long distance = curr[1];
            if (distance > dist[node]) continue;
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                long newDist = distance + neighbor[1];
                
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new long[]{nextNode, newDist});
                }
            }
        }
        // Now count the number of shortest paths using DP
        long[] ways = new long[V];
        ways[0] = 1;
        // Sort nodes by their distance from source
        Integer[] nodes = new Integer[V];
        for (int i = 0; i < V; i++) nodes[i] = i;
        Arrays.sort(nodes, (a, b) -> Long.compare(dist[a], dist[b]));
        for (int node : nodes) {
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                long edgeWeight = neighbor[1];
                
                // If this edge is part of a shortest path
                if (dist[nextNode] == dist[node] + edgeWeight) {
                    ways[nextNode] += ways[node];
                }
            }
        }
        return (int) ways[V - 1];
    }
}