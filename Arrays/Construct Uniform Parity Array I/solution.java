class Solution {
    public boolean uniformArray(int[] nums1) {
        // If there is at least one odd number,
        // every element can be made odd.
        for (int num : nums1) {
            if ((num & 1) == 1) {
                return true;
            }
        }

        // All numbers are even, so keep them unchanged.
        return true;
    }
}