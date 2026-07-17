class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        // cntDiv[d] = count of numbers divisible by d
        int[] cntDiv = new int[max + 1];
        for (int d = 1; d <= max; d++) {
            for (int m = d; m <= max; m += d) {
                cntDiv[d] += freq[m];
            }
        }

        // exactPairs[d] = number of pairs whose gcd is exactly d
        long[] exactPairs = new long[max + 1];
        for (int d = max; d >= 1; d--) {
            long c = cntDiv[d];
            long pairs = c * (c - 1) / 2;

            for (int m = d + d; m <= max; m += d) {
                pairs -= exactPairs[m];
            }
            exactPairs[d] = pairs;
        }

        // Prefix counts of sorted gcdPairs
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exactPairs[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // convert to 1-based order

            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (prefix[mid] >= target)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            ans[i] = lo;
        }

        return ans;
    }
}