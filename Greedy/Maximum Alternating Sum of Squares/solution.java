import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            squares[i] = x * x;
        }

        Arrays.sort(squares);

        int n = squares.length;
        long score = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }

        return score;
    }
}