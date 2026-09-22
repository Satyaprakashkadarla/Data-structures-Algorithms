class Solution {
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n, k;
    Node[] tree;

    private Node merge(Node a, Node b) {
        Node res = new Node(k);

        // Product of the complete merged segment.
        res.prod = (a.prod * b.prod) % k;

        // Prefixes lying completely inside a.
        for (int r = 0; r < k; r++) {
            res.cnt[r] = a.cnt[r];
        }

        // Prefixes that take all of a and then a prefix of b.
        for (int r = 0; r < k; r++) {
            int newRem = (a.prod * r) % k;
            res.cnt[newRem] += b.cnt[r];
        }

        return res;
    }

    private Node makeLeaf(int value) {
        Node res = new Node(k);

        int rem = value % k;
        res.prod = rem;

        // The only non-empty prefix is the element itself.
        res.cnt[rem] = 1;

        return res;
    }

    private void build(int idx, int l, int r, int[] nums) {
        if (l == r) {
            tree[idx] = makeLeaf(nums[l]);
            return;
        }

        int mid = (l + r) >>> 1;

        build(idx << 1, l, mid, nums);
        build(idx << 1 | 1, mid + 1, r, nums);

        tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
    }

    private void update(int idx, int l, int r, int pos, int value) {
        if (l == r) {
            tree[idx] = makeLeaf(value);
            return;
        }

        int mid = (l + r) >>> 1;

        if (pos <= mid) {
            update(idx << 1, l, mid, pos, value);
        } else {
            update(idx << 1 | 1, mid + 1, r, pos, value);
        }

        tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(idx << 1, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(idx << 1 | 1, mid + 1, r, ql, qr);
        }

        Node left = query(idx << 1, l, mid, ql, qr);
        Node right = query(idx << 1 | 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int index = queries[qi][0];
            int value = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            // Persistent point update.
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Every possible remaining array is a non-empty prefix
            // of nums[start..n-1].
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[qi] = res.cnt[x];
        }

        return ans;
    }
}
