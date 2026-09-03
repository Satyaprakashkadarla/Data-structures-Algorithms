class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false, hasEven = false;

        for (int x : nums1) {
            min = Math.min(min, x);

            if ((x & 1) == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }

        // Already uniform parity
        if (!hasOdd || !hasEven) {
            return true;
        }

        // If minimum is odd, it can remain unchanged.
        // Every even number can subtract this odd minimum
        // (provided it is larger, which it is since min is the minimum).
        if ((min & 1) == 1) {
            return true;
        }

        // Minimum is even. To make everything even, leave the
        // minimum unchanged and subtract it from odd numbers.
        // But odd - even remains odd, so this cannot work.
        // To make everything odd, the minimum even itself must
        // become odd, which is impossible because nothing is smaller.
        return false;
    }
}
