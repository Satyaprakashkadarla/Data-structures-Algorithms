class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            long cur = len[i];

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = cur + 1;
            } else if (ch == '*') {
                len[i + 1] = Math.max(0L, cur - 1);
            } else if (ch == '#') {
                len[i + 1] = cur * 2;
            } else { // '%'
                len[i + 1] = cur;
            }
        }

        if (k < 0 || k >= len[n]) {
            return '.';
        }

        for (int i = n; i >= 1; i--) {
            char ch = s.charAt(i - 1);
            long prevLen = len[i - 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == len[i] - 1) {
                    return ch;
                }
                // Otherwise the character came from the previous state.
            } else if (ch == '#') {
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else if (ch == '%') {
                k = prevLen - 1 - k;
            } else { // '*'
                // Deletion removes the last character of the previous state.
                // Any surviving index maps directly to the same index.
            }
        }

        return '.';
    }
}