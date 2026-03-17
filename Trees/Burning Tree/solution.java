class Solution {
    public static int minTime(Node root, int target) {
        // Step 1: Map each node to its parent and locate the target node
        Queue<Node>     que    = new LinkedList<>();
        Map<Node, Node> parent = new HashMap<>();
        Node            tar    = null;

        que.offer(root);
        parent.put(root, null);

        while (!que.isEmpty()) {
            Node curr = que.poll();

            // Locate the target node
            if (curr.data == target) {
                tar = curr;
            }

            // Map left child to parent
            if (curr.left != null) {
                que.offer(curr.left);
                parent.put(curr.left, curr);
            }

            // Map right child to parent
            if (curr.right != null) {
                que.offer(curr.right);
                parent.put(curr.right, curr);
            }
        }

        // Step 2: Simulate burning using BFS from target node
        Map<Node, Boolean> visited = new HashMap<>();
        int t = -1;     // Time counter
        que.offer(tar); // Start BFS from target node

        while (!que.isEmpty()) {
            int n = que.size(); // Nodes burning at current second

            while (n-- > 0) {
                Node curr = que.poll();
                visited.put(curr, true); // Mark current node as burned

                // Spread to left child
                if (curr.left != null && !visited.containsKey(curr.left)) {
                    que.offer(curr.left);
                }

                // Spread to right child
                if (curr.right != null && !visited.containsKey(curr.right)) {
                    que.offer(curr.right);
                }

                // Spread to parent
                if (parent.get(curr) != null && !visited.containsKey(parent.get(curr))) {
                    que.offer(parent.get(curr));
                }
            }

            t++; // Increment time after processing current level
        }

        return t;
    }
}