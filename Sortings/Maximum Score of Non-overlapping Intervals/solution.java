class Solution {
    public int[] maximumWeight(List<List<Integer>> in) {
        int n = in.size();
        long[][] a = new long[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = in.get(i).get(0);
            a[i][1] = in.get(i).get(1);
            a[i][2] = in.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (u, v) -> Long.compare(u[1], v[1]));

        long[] r = new long[n];
        for (int i = 0; i < n; i++) r[i] = a[i][1];

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++)
            for (int k = 0; k <= 4; k++)
                dp[i][k] = new State(0, new int[0]);

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= 4; k++)
                dp[i][k] = dp[i - 1][k];

            int p = lb(r, a[i - 1][0]);

            for (int k = 1; k <= 4; k++) {
                State s = dp[p][k - 1];
                State t = new State(
                    s.w + a[i - 1][2],
                    add(s.id, (int)a[i - 1][3])
                );

                if (better(t, dp[i][k]))
                    dp[i][k] = t;
            }
        }

        return dp[n][4].id;
    }

    static int lb(long[] a, long x) {
        int l = 0, h = a.length;
        while (l < h) {
            int m = (l + h) >>> 1;
            if (a[m] < x) l = m + 1;
            else h = m;
        }
        return l;
    }

    static int[] add(int[] a, int x) {
        int[] b = Arrays.copyOf(a, a.length + 1);
        int i = b.length - 1;
        while (i > 0 && b[i - 1] > x) {
            b[i] = b[i - 1];
            i--;
        }
        b[i] = x;
        return b;
    }

    static boolean better(State a, State b) {
        if (a.w != b.w) return a.w > b.w;
        for (int i = 0; i < a.id.length; i++) {
            if (i == b.id.length) return false;
            if (a.id[i] != b.id[i]) return a.id[i] < b.id[i];
        }
        return true;
    }

    static class State {
        long w;
        int[] id;

        State(long w, int[] id) {
            this.w = w;
            this.id = id;
        }
    }
}
