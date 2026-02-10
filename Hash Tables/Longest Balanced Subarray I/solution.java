class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int longest = 0;

        for(int l = 0; l < n - 1; l++){
            Set<Integer> pool = new HashSet<>();
            int oddCount = 0;
            int evenCount = 0;
            
            for(int r = l; r < n; r++){
                int num = nums[r];
                
                // Only count if it's a new distinct number
                if(!pool.contains(num)){
                    pool.add(num);
                    if(num % 2 == 1){
                        oddCount++;
                    } else {
                        evenCount++;
                    }
                }

                // Check if current window is balanced
                if(oddCount == evenCount){
                    longest = Math.max(longest, r - l + 1);
                }
            }

            // Early termination: can't find longer subarray
            if(longest >= n - l){
                return longest;
            }
        }

        return longest;
    }
}