class Solution {
    // Helper DFS function to detect dead ends in BST
    private boolean dfs(Node root, int min, int max) {
        if (root == null) {
            return false; // base case: empty subtree cannot have dead end
        }

        // if it's a leaf node
        if (root.left == null && root.right == null) {
            // check if no number can be inserted at or below this leaf
            if (root.data - min == 1 && max - root.data == 1) {
                return true;  // dead end found
            } else {
                return false; // not a dead end
            }
        }

        // recursively check in left and right subtrees
        return dfs(root.left, min, root.data) ||
               dfs(root.right, root.data, max);
    }
    
    public boolean isDeadEnd(Node root) {
        // Start with min = 0 and max = Integer.MAX_VALUE for root
        return dfs(root, 0, Integer.MAX_VALUE);
    }
}