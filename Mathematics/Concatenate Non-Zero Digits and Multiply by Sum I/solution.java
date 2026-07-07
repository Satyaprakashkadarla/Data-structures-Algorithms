class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;

        String s = Integer.toString(n);
        long x = 0;
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                x = x * 10 + d;
                sum += d;
            }
        }

        return x * sum;
    }
}