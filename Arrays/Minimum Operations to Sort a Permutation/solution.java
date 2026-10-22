class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zeroIdx = -1;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // Case 1: Increasing to the right (step = 1)
        if (isValid(nums, zeroIdx, 1)) {
            ans = Math.min(ans, zeroIdx);             // Direct rotations
            ans = Math.min(ans, n - zeroIdx + 2);     // Reverse, rotate, reverse
        }
        
        // Case 2: Increasing to the left (step = -1)
        if (isValid(nums, zeroIdx, -1)) {
            ans = Math.min(ans, zeroIdx + 2);         // Rotate, reverse
            ans = Math.min(ans, n - zeroIdx);         // Reverse, rotate
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    // Helper method to check if elements follow step (+1 or -1) cyclically
    private boolean isValid(int[] nums, int zeroIdx, int step) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int prev = (zeroIdx + (i - 1) * step) % n;
            int curr = (zeroIdx + i * step) % n;
            
            // Normalize negative modulo indices in Java
            if (prev < 0) prev += n;
            if (curr < 0) curr += n;
            
            if (nums[prev] > nums[curr]) {
                return false;
            }
        }
        return true;
    }
}