class Solution {
    private void flipWindow(int[] nums, int pos) {
        nums[pos] ^= 1;       // Flip the bit at position `pos`
        nums[pos + 1] ^= 1;   // Flip the bit at position `pos + 1`
        nums[pos + 2] ^= 1;   // Flip the bit at position `pos + 2`
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int operations = 0;

        for (int i = 0; i < n - 2; ++i) {
            if (nums[i] == 1) {
                continue; // Skip if the current bit is already 1
            }
            flipWindow(nums, i); // Flip the window starting at position `i`
            operations++;        // Increment the operation count
        }

        // Check if the last three bits are all 1
        if (nums[n - 2] == 0 || nums[n - 1] == 0) {
            return -1; // If not, return -1
        }

        return operations; // Return the total number of operations
    }
}
