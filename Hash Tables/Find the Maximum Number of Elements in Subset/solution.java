import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        for (long x : freq.keySet()) {

            // Special handling for 1
            if (x == 1) {
                int c = freq.get(1L);
                ans = Math.max(ans, c % 2 == 0 ? c - 1 : c);
                continue;
            }

            long cur = x;
            int len = 0;

            while (true) {
                Integer cnt = freq.get(cur);
                if (cnt == null) break;

                if (cnt >= 2) {
                    len += 2;

                    // Prevent overflow
                    if (cur > 1000000000L) break;
                    long nxt = cur * cur;
                    if (!freq.containsKey(nxt)) {
                        len--;   // use one as the peak
                        break;
                    }
                    cur = nxt;
                } else {
                    len++;      // use as peak
                    break;
                }
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}