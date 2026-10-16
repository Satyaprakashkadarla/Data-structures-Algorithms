class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] sumSq;
    int[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new int[4 * n];
        sumSq = new int[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Add 1 to distinct counts of subarrays
            // starting at positions [last[x] + 1, i].
            update(1, 0, n - 1, last[x] + 1, i);

            // Sum of squared distinct counts for subarrays ending at i.
            ans = (ans + sumSq[1]) % MOD;

            last[x] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, int val) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sumSq[node] = (int) ((sumSq[node]
                + 2L * val * oldSum
                + len * val * val) % MOD);

        sum[node] = (int) ((oldSum + len * val) % MOD);
        lazy[node] += val;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);
        lazy[node] = 0;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        if (ql <= mid) {
            update(node * 2, l, mid, ql, qr);
        }

        if (qr > mid) {
            update(node * 2 + 1, mid + 1, r, ql, qr);
        }

        sum[node] = (int) ((sum[node * 2] + (long) sum[node * 2 + 1]) % MOD);
        sumSq[node] = (int) ((sumSq[node * 2] + (long) sumSq[node * 2 + 1]) % MOD);
    }
}