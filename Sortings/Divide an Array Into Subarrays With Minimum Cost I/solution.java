class Solution {
    public int minimumCost(int[] nums) {
        
        //contigouos so First element must always be included
        int ans = nums[0];
        int sec = nums[1];
        int third = nums[2];
          int in2=1;//track 3rd indx so it cant be reuse
        // Traverse to find minimum possible values
        for (int i = 2; i < nums.length; i++) {
             if(nums[i]<sec){
                third=sec;
                sec=nums[i];
              in2=i;
             }
             if(nums[i]<third && i!=in2){
                third=nums[i];
                
             }
        }
        ans += sec + third;

        return ans;
    }
}