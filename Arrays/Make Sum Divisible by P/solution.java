class Solution {
    public int minSubarray(int[] nums, int p) {
        long r = 0;
        for (int i : nums) r += i;
        r %= p;
        if (r == 0) return 0;

        int n = nums.length;
        Map<Long, Integer> di = new HashMap(n+1);
        di.put(0l, -1);

        long[] ps = new long[n+1];
        int mi = n;
        for (int i = 0; i < n; i++) {
            long rem = (ps[i] + nums[i]) % p;
            ps[i+1] = rem;
            long d = rem - r;
            if (d < 0) d+= p;
            if (di.containsKey(d)) {
                mi = Math.min(mi, i - di.get(d));
                if (mi == 1) return 1;
            }

            di.put(rem, i);
        }
        return mi == n ? -1 : mi;
    }
}