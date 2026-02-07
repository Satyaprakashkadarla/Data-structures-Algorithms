class Solution {
    public int maxSum(int[] arr) {
        int n = arr.length;
        int total = 0;
        int curr = 0;
        
        for (int i = 0; i < n; i++) {
            total += arr[i];
            curr += i * arr[i];
        }
        
        int maxS = curr;
        for (int i = 0; i < n; i++) {
            curr = curr - total + n * arr[i];
            maxS = Math.max(maxS, curr);
        }
        
        return maxS;
    }
}