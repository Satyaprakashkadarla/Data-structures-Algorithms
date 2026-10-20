class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        long totalSum = 0;

        // Iterate through all divisors k of n
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                if (isValid(nums, k)) {
                    totalSum += k;
                }
                int k2 = n / k;
                if (k2 != k) {
                    if (isValid(nums, k2)) {
                        totalSum += k2;
                    }
                }
            }
        }
        return (int) totalSum;
    }

    private boolean isValid(int[] nums, int k) {
        int n = nums.length;
        int numBlocks = n / k;
        
        Integer prevMax = null;

        for (int b = 0; b < numBlocks; b++) {
            int start = b * k;
            int end = start + k - 1;

            // 1. Check if the current block can be cyclically sorted
            int drops = 0;
            int minIdx = start;
            for (int i = start; i <= end; i++) {
                if (nums[i] > nums[start + (i - start + 1) % k]) {
                    drops++;
                }
            }
            if (drops > 1) {
                return false;
            }

            // Find the minimum element of this block when cyclically sorted.
            // The minimum element is nums[minIdx] where nums[minIdx] <= nums[prev].
            // Or more simply, find the rotation start:
            int rotOffset = 0;
            for (int i = start; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    rotOffset = (i + 1) - start;
                    break;
                }
            }

            int currMin = nums[start + rotOffset];
            int currMax = nums[start + (rotOffset - 1 + k) % k];

            // 2. Check if the blocks are globally non-decreasing
            if (prevMax != null && prevMax > currMin) {
                return false;
            }

            prevMax = currMax;
        }

        return true;
    }
}