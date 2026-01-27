class Solution {
    public int minCost(final int n, final int[][] edges) {
        final List<int[]>[] adj = new List[n];

        for(final int[] edge : edges) {
            if(adj[edge[0]] == null)
                adj[edge[0]] = new ArrayList<>();

            if(adj[edge[1]] == null)
                adj[edge[1]] = new ArrayList<>();

            adj[edge[0]].add(new int[] { edge[1], edge[2] });
            adj[edge[1]].add(new int[] { edge[0], 2 * edge[2] });
        }


        final Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        final int[] costs = new int[n];

        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[0] = 0;
        queue.offer(new int[] { 0, 0 });

        while(!queue.isEmpty()) {
            final int[] data = queue.poll();
            final int node = data[0], total = data[1];

            if(total > costs[node])
                continue;

            if(node == n - 1)
                return total;

            final List<int[]> list = adj[node];

            if(list != null) {
                for(final int[] next : list) {
                    if(total + next[1] >= costs[next[0]])
                        continue;

                    costs[next[0]] = total + next[1];

                    queue.offer(new int[] { next[0], costs[next[0]] });
                }
            }
        }

        return -1;
    }
}