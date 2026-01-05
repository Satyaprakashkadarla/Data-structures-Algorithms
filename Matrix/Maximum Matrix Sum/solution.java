class Solution {
    public long maxMatrixSum(int[][] nums) {
        int n = nums.length;
        long sum = 0;
        int negCount = 0;
        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] < 0) negCount++;
                int val = Math.abs(nums[i][j]);
                sum += val;
                minVal = Math.min(minVal, val);
            }
        }

        if (negCount % 2 == 0) return sum;
        return sum - 2L * minVal;
    }
}