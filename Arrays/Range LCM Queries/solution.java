import java.util.*;

class Solution {
    static class SegmentTree {
        long[] tree;
        int n;
        
        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            build(arr, 1, 0, n - 1);
        }
        
        private long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }
        
        private long lcm(long a, long b) {
            return a / gcd(a, b) * b;
        }
        
        private void build(int[] arr, int node, int l, int r) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            build(arr, node * 2, l, mid);
            build(arr, node * 2 + 1, mid + 1, r);
            tree[node] = lcm(tree[node * 2], tree[node * 2 + 1]);
        }
        
        void update(int idx, int val, int node, int l, int r) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) update(idx, val, node * 2, l, mid);
            else update(idx, val, node * 2 + 1, mid + 1, r);
            tree[node] = lcm(tree[node * 2], tree[node * 2 + 1]);
        }
        
        long query(int ql, int qr, int node, int l, int r) {
            if (ql > r || qr < l) return 1;
            if (ql <= l && r <= qr) return tree[node];
            int mid = (l + r) / 2;
            long left = query(ql, qr, node * 2, l, mid);
            long right = query(ql, qr, node * 2 + 1, mid + 1, r);
            return lcm(left, right);
        }
    }
    
    public ArrayList<Long> RangeLCMQuery(int[] arr, int[][] queries) {
        SegmentTree seg = new SegmentTree(arr);
        ArrayList<Long> result = new ArrayList<>();
        
        for (int[] q : queries) {
            if (q[0] == 1) { // update
                seg.update(q[1], q[2], 1, 0, arr.length - 1);
            } else { // query
                long lcm = seg.query(q[1], q[2], 1, 0, arr.length - 1);
                result.add(lcm);
            }
        }
        return result;
    }
}