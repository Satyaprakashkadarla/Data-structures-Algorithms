class Solution {
    public int countPairs(int[] nums, int k) {
        int length = nums.length; 
        int pairCount = 0;       
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    pairCount++; 
                }
            }
        }
        return pairCount;
    }
}
      