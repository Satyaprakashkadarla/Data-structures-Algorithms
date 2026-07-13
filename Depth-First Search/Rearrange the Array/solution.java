class Solution {
    int minOperations(int[] b) {
        int n = b.length;
        boolean[] visited = new boolean[n];
        long lcm = 1;
        int MOD = 1000000007;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int j = i;
                int len = 0;
                while (!visited[j]) {
                    visited[j] = true;
                    j = b[j] - 1; // convert to 0-based
                    len++;
                }
                lcm = lcm(lcm, len);
            }
        }
        return (int)(lcm % MOD);
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}