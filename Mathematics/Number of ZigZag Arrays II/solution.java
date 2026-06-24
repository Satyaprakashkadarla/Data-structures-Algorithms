class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        int sz = 2 * m;
        long[][] T = new long[sz][sz];

        // State:
        // 0..m-1     -> U[v] (last comparison was up)
        // m..2m-1    -> D[v] (last comparison was down)
        //
        // U[v] = sum D[u] for u < v
        // D[v] = sum U[u] for u > v
        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                T[v][m + u] = 1;          // U[v] <- D[u]
            }
            for (int u = v + 1; u < m; u++) {
                T[m + v][u] = 1;          // D[v] <- U[u]
            }
        }

        long[] state = new long[sz];

        // Length 2 initialization
        for (int v = 0; v < m; v++) {
            state[v] = v;                 // U[v] = count of smaller values
            state[m + v] = m - 1 - v;     // D[v] = count of larger values
        }

        long[][] P = matrixPower(T, n - 2);
        long[] result = multiply(P, state);

        long ans = 0;
        for (long x : result) {
            ans += x;
            ans %= MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] base, long exp) {
        int n = base.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        long[][] cur = base;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, cur);
            }
            cur = multiply(cur, cur);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long aik = A[i][k];
                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0 || v[j] == 0) continue;
                sum = (sum + A[i][j] * v[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }
}