import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public void inorder(Node root, List<Integer> ans) {
        if (root == null) return;
        inorder(root.left, ans);
        ans.add(root.data);
        inorder(root.right, ans);
    }

    public List<Integer> merge(Node root1, Node root2) {
        List<Integer> ans = new ArrayList<>();
        inorder(root1, ans);
        inorder(root2, ans);
        Collections.sort(ans);
        return ans;
    }
}
