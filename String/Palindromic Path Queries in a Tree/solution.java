
import java.util.*;

class Solution {
    private List<Integer>[] tree;
    private int[] parent, depth, size, heavy, head, pos;
    private int[] bit;
    private char[] chars;
    private int timer;

    public List<Boolean> palindromePath(int n, int[][] edges, String s,
                                         String[] queries) {
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);

        // Iterative traversal to establish parents and depths.
        int[] order = new int[n];
        int count = 0;
        int[] stack = new int[n];
        int top = 0;
        stack[top] = 0;
        parent[0] = -1;

        while (top >= 0) {
            int u = stack[top--];
            order[count++] = u;

            for (int v : tree[u]) {
                if (v == parent[u]) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            }
        }

        // Compute subtree sizes and heavy children.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : tree[u]) {
                if (v == parent[u]) continue;
                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        // Iterative heavy-light decomposition.
        int[] chainNodes = new int[n];
        int[] chainHeads = new int[n];
        int chainTop = 0;
        chainNodes[chainTop] = 0;
        chainHeads[chainTop++] = 0;
        timer = 0;

        while (chainTop > 0) {
            int u = chainNodes[--chainTop];
            int h = chainHeads[chainTop];

            for (int x = u; x != -1; x = heavy[x]) {
                head[x] = h;
                pos[x] = ++timer;

                for (int v : tree[x]) {
                    if (v != parent[x] && v != heavy[x]) {
                        chainNodes[chainTop] = v;
                        chainHeads[chainTop++] = v;
                    }
                }
            }
        }

        bit = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(pos[i], 1 << (chars[i] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                if (chars[u] != c) {
                    add(pos[u], 1 << (chars[u] - 'a'));
                    add(pos[u], 1 << (c - 'a'));
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= range(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= range(pos[u], pos[v]);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void add(int idx, int mask) {
        for (; idx < bit.length; idx += idx & -idx) {
            bit[idx] ^= mask;
        }
    }

    private int prefix(int idx) {
        int mask = 0;
        for (; idx > 0; idx -= idx & -idx) {
            mask ^= bit[idx];
        }
        return mask;
    }

    private int range(int l, int r) {
        return prefix(r) ^ prefix(l - 1);
    }
}
