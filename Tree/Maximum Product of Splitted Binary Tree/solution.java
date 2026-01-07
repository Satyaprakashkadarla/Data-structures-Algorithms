/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    long T=0,R=0;
    public int maxProduct(TreeNode root) {
        T=subSumDFS(root);
        subSumDFS(root);
        return (int) (R % (int)(1e9+7));
    }
    private int subSumDFS(TreeNode root){
        if(root==null) return 0;
        int sub=subSumDFS(root.left)+root.val+subSumDFS(root.right);
        R=Math.max(R,sub*(T-sub));
        return sub;
    }
}