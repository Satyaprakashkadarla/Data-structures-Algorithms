class Solution {
    public int circularSubarraySum(int arr[]) {
        int s = 0, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE, curr = 0, currNeg = 0;

        for (int i : arr) {
            // Compute total sum of the array
            s += i;

            // Compute maximum subarray sum using Kadane's algorithm
            curr = Math.max(curr + i, i);
            maxSum = Math.max(curr, maxSum);

            // Compute minimum subarray sum using Kadane's algorithm
            currNeg = Math.min(currNeg + i, i);
            minSum = Math.min(minSum, currNeg);
        }

        // If all elements are negative, the minimum subarray sum will equal the total sum
        if (s == minSum) {
            return maxSum;
        }

        // Otherwise, return the maximum of the regular subarray sum or the circular subarray sum
        return Math.max(maxSum, s - minSum);
    }
}
