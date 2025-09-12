import java.util.Arrays;

class Solution {
    public int getMinDiff(int[] arr, int k) {
        int n = arr.length;
        if (n == 1) return 0;

        Arrays.sort(arr);
        
        int minDiff = arr[n - 1] - arr[0]; 
        int smallest = arr[0] + k;
        int largest = arr[n - 1] - k;

        for (int i = 0; i < n - 1; i++) {
            int minElem = Math.min(smallest, arr[i + 1] - k);
            int maxElem = Math.max(largest, arr[i] + k);

            if (minElem < 0) continue; 
            minDiff = Math.min(minDiff, maxElem - minElem);
        }

        return minDiff;
    }
}
