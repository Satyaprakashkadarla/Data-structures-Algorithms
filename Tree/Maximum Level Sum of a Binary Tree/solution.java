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
    private int totallevel;
    private int sumatlevels[] = new int[10000];

    public int maxLevelSum(TreeNode root) {
        int ans =1,maxsum = Integer.MIN_VALUE;

        maxlevelsumhelper(root,0);

        for(int i=0;i<=totallevel;i++){
            int sum = sumatlevels[i];

            if(sum>maxsum){
                maxsum = sum;
                ans = i+1;
            }
        }

        System.gc();

        return ans;
    }

    private void maxlevelsumhelper(TreeNode t,int l){
        if(t == null)
            return;

        sumatlevels[l]+=t.val;
        
        totallevel = Math.max(totallevel,l);

        maxlevelsumhelper(t.right,l+1);
        maxlevelsumhelper(t.left,l+1);
    }
}