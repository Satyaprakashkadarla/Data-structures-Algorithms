
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Find the closest ancestor with the same character.
        dfs(0, tree, s, last, newParent);

        // Build the modified tree.
        List<Integer>[] modified = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }

        // Calculate subtree sizes.
        int[] answer = new int[n];
        calculate(0, modified, answer);

        return answer;
    }

    private void dfs(int node, List<Integer>[] tree, String s,
                     int[] last, int[] newParent) {

        int c = s.charAt(node) - 'a';
        int previous = last[c];

        if (node != 0 && previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : tree[node]) {
            dfs(child, tree, s, last, newParent);
        }

        // Restore ancestor state while backtracking.
        last[c] = previous;
    }

    private int calculate(int node, List<Integer>[] tree, int[] answer) {
        int size = 1;

        for (int child : tree[node]) {
            size += calculate(child, tree, answer);
        }

        answer[node] = size;
        return size;
    }
}
