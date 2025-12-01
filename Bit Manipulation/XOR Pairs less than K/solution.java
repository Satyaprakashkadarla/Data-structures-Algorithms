class Solution {

    // 0-1 Trie node
    static class Node {
        Node[] child = new Node[2];
        int cnt; 
    }
    private static final int MAX_BITS = 16; 
    public int cntPairs(int[] arr, int k) {
        Node root = new Node();
        int ans = 0;

        for (int x : arr) {
            ans += countLess(root, x, k);
            insert(root, x);
        }
        return ans;
    }
    private void insert(Node root, int x) {
        Node cur = root;
        for (int bit = MAX_BITS - 1; bit >= 0; bit--) {
            int b = (x >> bit) & 1;
            if (cur.child[b] == null) {
                cur.child[b] = new Node();
            }
            cur = cur.child[b];
            cur.cnt++; 
        }
    }
    private int countLess(Node root, int x, int k) {
        Node cur = root;
        int res = 0;
        for (int bit = MAX_BITS - 1; bit >= 0 && cur != null; bit--) {
            int xb = (x >> bit) & 1;   
            int kb = (k >> bit) & 1;   
            if (kb == 1) {
                int desiredBit = xb; 
                if (cur.child[desiredBit] != null) {
                    res += cur.child[desiredBit].cnt;
                }
                int otherBit = xb ^ 1;
                cur = cur.child[otherBit];
            } else {
                
                cur = cur.child[desiredBit];
            }
        }
        return res;
    }
}
