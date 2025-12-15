class Solution {
    public int cntWays(int[] arr) {
        int n = arr.length;
        int evenSum = 0, oddSum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) evenSum += arr[i];
            else oddSum += arr[i];
        }
        
        int evenLeft = 0, oddLeft = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int delta = (i % 2 == 0) ? -arr[i] : arr[i];
            int totalDiff = evenSum - oddSum + delta;
            if (totalDiff % 2 == 0) {
                int target = totalDiff / 2;
                if (evenLeft - oddLeft == target) {
                    count++;
                }
            }
            
            // Update left sums for next iteration
            if (i % 2 == 0) {
                evenLeft += arr[i];
            } else {
                oddLeft += arr[i];
            }
        }
        
        return count;
    }
}