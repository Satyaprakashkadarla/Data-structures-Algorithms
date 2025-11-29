class Solution {
    public static int countSetBits(int n) {
        int count = 0;
        
        for (int i = 0; (1 << i) <= n; i++) {
            int cycle = 1 << (i + 1);
            count += (n / cycle) * (1 << i);
            count += Math.max(0, (n % cycle) - (1 << i) + 1);
        }
        
        return count;
    }
}