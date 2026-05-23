class Solution {
    public void toSumTree(Node root) {
        transform(root);
    }
    
    private int transform(Node node) {
        if (node == null) return 0;
        
        int leftSum = transform(node.left);
        int rightSum = transform(node.right);
        
        int original = node.data;
        node.data = leftSum + rightSum;
        
        return original + leftSum + rightSum;
    }
}