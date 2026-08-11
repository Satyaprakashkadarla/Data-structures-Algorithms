class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find sum of the longest sequential prefix
        int i = 1;
        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }

        // Mark all numbers present in nums
        boolean[] present = new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        // Find the smallest missing integer >= sum
        while (sum <= 100 && present[sum]) {
            sum++;
        }

        return sum;
    }
}