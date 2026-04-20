class Solution {
    public int derangeCount(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        
        int d1 = 0, d2 = 1;
        for (int i = 3; i <= n; i++) {
            int d3 = (i - 1) * (d1 + d2);
            d1 = d2;
            d2 = d3;
        }
        return d2;
    }
}