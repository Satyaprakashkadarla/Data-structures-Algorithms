class Solution {
        public int arrayPairSum(int[] nums) {
            //sort the array
            Arrays.sort(nums);
            int sum = 0;
            for(int i = 0; i < nums.length; i += 2) {
                sum += nums[i];
            }
            return sum;
        }
    }