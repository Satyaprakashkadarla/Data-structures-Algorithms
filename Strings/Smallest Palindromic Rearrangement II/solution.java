class Solution {
    static final long LIMIT = 1000000L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1) mid = (char) ('a' + i);
        }

        long total = countWays(half, halfLen);
        if (total < k) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long cnt = countWays(half, halfLen - pos - 1);

                if (cnt >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= cnt;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if (mid != 0) ans.append(mid);
        ans.append(left.reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt, int total) {
        long res = 1;
        int rem = total;

        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (c == 0) continue;

            for (int j = 1; j <= c; j++) {
                res = res * (rem - c + j) / j;
                if (res >= LIMIT) {
                    res = LIMIT;
                }
            }
            rem -= c;
        }

        return Math.min(res, LIMIT);
    }
}