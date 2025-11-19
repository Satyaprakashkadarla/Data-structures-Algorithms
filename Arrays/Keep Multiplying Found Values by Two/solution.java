class Solution {
    public int findFinalValue(final int[] nums, int original) {
        final boolean[] dict = new boolean[1001];

        for(final int num : nums)
            dict[num] = true;

        while(original <= 1000 && dict[original])
            original *= 2;

        return original;
    }
}