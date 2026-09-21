class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];

        // prev[r] = number of subarrays ending at previous index
        // whose product % k == r
        long[] prev = new long[k];

        for (int num : nums) {
            long[] cur = new long[k];
            int a = num % k;

            // Start a new subarray with just nums[i]
            cur[a]++;

            // Extend every subarray ending at i - 1
            for (int r = 0; r < k; r++) {
                if (prev[r] != 0) {
                    int nr = (r * a) % k;
                    cur[nr] += prev[r];
                }
            }

            // Add all subarrays ending at current index
            for (int r = 0; r < k; r++) {
                ans[r] += cur[r];
            }

            prev = cur;
        }

        return ans;
    }
}
