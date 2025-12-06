class Solution {
    public int countPartitions(int[] nums, int k) {
        final int MOD = 1000000007;
        int n = nums.length;
        long[] dp = new long[n + 1];
        dp[0] = 1;

        Deque<Integer> maxq = new LinkedList<>();
        Deque<Integer> minq = new LinkedList<>();
        int left = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            while (!maxq.isEmpty() && nums[maxq.peekLast()] <= nums[i]) {
                maxq.pollLast();
            }

            while (!minq.isEmpty() && nums[minq.peekLast()] >= nums[i]) {
                minq.pollLast();
            }
            maxq.offer(i);
            minq.offer(i);

            while (nums[maxq.peekFirst()] - nums[minq.peekFirst()] > k) {
                if (maxq.peekFirst() == left)
                    maxq.pollFirst();
                if (minq.peekFirst() == left)
                    minq.pollFirst();
                total = (total - dp[left] + MOD) % MOD;
                left++;
            }

            total = (total + dp[i]) % MOD;
            dp[i + 1] = total;
        }

        return (int) dp[n];
    }
}