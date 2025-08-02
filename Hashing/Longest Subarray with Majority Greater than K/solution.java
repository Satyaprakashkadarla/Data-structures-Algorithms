class Solution {
    public int longestSubarray(int[] arr, int k) {
      HashMap<Integer, Integer> map = new HashMap<>();
         int n = arr.length;
        int ans=0;
         for(int i=0; i<n; i++){
             if(arr[i]>k)
             arr[i]=1;
             else 
              arr[i]=-1;
         }
        int preSum=0;
        for(int i=0; i<n; i++){
            preSum+=arr[i];
            if(preSum>0){
                ans=Math.max(ans, i+1);
            }
             if(map.containsKey(preSum-1))
            ans=Math.max(ans, i-map.get(preSum-1));
             
             if(!map.containsKey(preSum))
          map.put(preSum, i);
        }
        return ans;
    }
}