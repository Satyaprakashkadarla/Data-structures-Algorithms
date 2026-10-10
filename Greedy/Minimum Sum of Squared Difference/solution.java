class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long[] freq = new long[100001];
        long operations = (long) k1 + k2;
        long maxDiff = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }

        for (int d = (int) maxDiff; d > 0 && operations > 0; d--) {
            long count = freq[d];
            long reduce = Math.min(count, operations);

            freq[d] -= reduce;
            freq[d - 1] += reduce;
            operations -= reduce;
        }

        // If operations remain, all differences are already zero.
        long result = 0;

        for (int d = 1; d <= maxDiff; d++) {
            result += freq[d] * d * d;
        }

        return result;
    }
}