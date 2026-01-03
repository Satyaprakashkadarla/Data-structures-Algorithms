class Solution {
    public Node flatten(Node root) {
        if (root == null || root.next == null) return root;
        
        // Flatten the next list
        root.next = flatten(root.next);
        
        // Merge current vertical list with flattened next list
        root = merge(root, root.next);
        
        return root;
    }
    
    private Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        
        Node result;
        if (a.data <= b.data) {
            result = a;
            result.bottom = merge(a.bottom, b);
        } else {
            result = b;
            result.bottom = merge(a, b.bottom);
        }
        result.next = null; // Important: next pointer should be null in merged list
        return result;
    }
}