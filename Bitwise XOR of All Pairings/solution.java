class Solution {
    // Method to calculate the XOR of all elements after performing XOR as if each element of nums1 is paired with all elements of nums2 and vice-versa
    public int xorAllNums(int[] nums1, int[] nums2) {
        int result = 0; // Initialize result to zero

        // If the length of nums2 is odd, XOR result with all elements in nums1
        // Because if nums2 has an odd number of elements, each element in nums1 will be represented an odd number of times when taking the XOR with all elements of nums2 and vice versa.
        if (nums2.length % 2 == 1) {
            for (int value : nums1) {
                result ^= value;
            }
        }

        // Likewise, if the length of nums1 is odd, XOR result with all elements in nums2
        if (nums1.length % 2 == 1) {
            for (int value : nums2) {
                result ^= value;
            }
        }

        return result; // Return the accumulated XOR result
    }
}
