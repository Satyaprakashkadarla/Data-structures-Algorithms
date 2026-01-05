class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return 0; // edge case
        
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }
}