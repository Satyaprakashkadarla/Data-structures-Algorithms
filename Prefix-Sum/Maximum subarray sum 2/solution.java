import java.util.*;

public class Solution {
    public long maxSubarrSum(int[] arr, int a, int b) {
        int n = arr.length;
        long[] prefix = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        Deque<Integer> dq = new ArrayDeque<>();
        long ans = Long.MIN_VALUE;

        for (int r = a; r <= n; r++) {
            int l = r - a;

            while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[l]) {
                dq.pollLast();
            }
            dq.addLast(l);

            while (!dq.isEmpty() && dq.peekFirst() < r - b) {
                dq.pollFirst();
            }

            ans = Math.max(ans, prefix[r] - prefix[dq.peekFirst()]);
        }

        return ans;
    }
}
