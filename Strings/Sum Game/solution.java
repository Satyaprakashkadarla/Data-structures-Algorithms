class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int diff = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);

            if (i < half) {
                if (c == '?') {
                    leftQ++;
                } else {
                    diff += c - '0';
                }
            } else {
                if (c == '?') {
                    rightQ++;
                } else {
                    diff -= c - '0';
                }
            }
        }

        int qDiff = leftQ - rightQ;

        // Alice wins if Bob cannot force the final sums to be equal.
        return diff * 2 != -9 * qDiff;
    }
}