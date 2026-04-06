import java.util.*;

class Solution {

    static class Node {
        int freq;
        int idx;
        Node left, right;

        Node(int f, int i) {
            freq = f;
            idx = i;
            left = right = null;
        }
    }

    public ArrayList<String> huffmanCodes(String s, int[] f) {

        int n = f.length;

        // Custom Comparator (same as C++ cmp)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq)
                return a.idx - b.idx;   // smaller index first
            return a.freq - b.freq;     // smaller frequency first
        });

        // Insert nodes
        for (int i = 0; i < n; i++) {
            pq.add(new Node(f[i], i));
        }

        // Edge case: only one character
        if (pq.size() == 1) {
            ArrayList<String> single = new ArrayList<>();
            single.add("0");
            return single;
        }

        // Build Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            int newIdx = Math.min(left.idx, right.idx);

            Node newNode = new Node(left.freq + right.freq, newIdx);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        Node root = pq.poll();

        ArrayList<String> ans = new ArrayList<>();
        preorder(root, "", ans);

        return ans;
    }

    void preorder(Node root, String code, ArrayList<String> ans) {
        if (root == null) return;

        // Leaf node
        if (root.left == null && root.right == null) {
            if (code.length() == 0) code = "0"; // single node case
            ans.add(code);
            return;
        }

        preorder(root.left, code + "0", ans);
        preorder(root.right, code + "1", ans);
    }
}