class Solution {
    int findWays(int n) {
        if (n % 2 == 1) return 0;
        int p = n / 2;
        long catalan = 1;
        // Compute C(2p, p) / (p+1)
        for (int i = 0; i < p; i++) {
            catalan = catalan * (2L * p - i) / (i + 1);
        }
        catalan = catalan / (p + 1);
        return (int) catalan;
    }
}