class Solution {
    public int maxSubarrayXOR(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return 0; // Edge case: array smaller than window size
        int currentXor = 0;
        
        // 1. Calculate XOR for the first window of size k
        for (int i = 0; i < k; i++) {
            currentXor ^= arr[i];
        }

        int maxXor = currentXor;

        // 2. Slide the window across the array
        for (int i = k; i < n; i++) {
            currentXor = currentXor ^ arr[i - k] ^ arr[i];
            
            // Update maxXor if the new window's XOR is higher
            if (currentXor > maxXor) {
                maxXor = currentXor;
            }
        }

        return maxXor;
    }
}