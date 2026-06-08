class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] res = new int[n];
        int idx = 0;

        // Elements smaller than pivot
        for (int num : nums) {
            if (num < pivot) {
                res[idx++] = num;
            }
        }

        // Elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                res[idx++] = num;
            }
        }

        // Elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                res[idx++] = num;
            }
        }

        return res;
    }
}