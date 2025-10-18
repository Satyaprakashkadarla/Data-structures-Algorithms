class Solution {
    private int count = 0;
    private int result = 0;

    public int findMedian(Node root) {
        int nodes = countNodes(root);
        int mid = (nodes + 1) / 2;
        inorder(root, mid);
        return result;
    }

    private void inorder(Node node, int mid) {
        if (node == null) return;

        inorder(node.left, mid);

        count++;
        if (count == mid) {
            result = node.data;
            return;
        }

        inorder(node.right, mid);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}