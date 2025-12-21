import java.util.*;

class Solution {
    public ArrayList<Integer> countXInRange(int[] arr, int[][] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int x = q[2];
            
            // Find lower bound of x in [l, r]
            int leftIdx = lowerBound(arr, l, r, x);
            if (leftIdx == -1 || arr[leftIdx] != x) {
                result.add(0);
            } else {
                // Find upper bound of x in [leftIdx, r]
                int rightIdx = upperBound(arr, leftIdx, r, x);
                result.add(rightIdx - leftIdx);
            }
        }
        
        return result;
    }
    
    // Returns first index in [l, r] where arr[i] >= x, or -1 if none
    private int lowerBound(int[] arr, int l, int r, int x) {
        int low = l, high = r;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    
    // Returns first index in [l, r] where arr[i] > x, or r+1 if none
    private int upperBound(int[] arr, int l, int r, int x) {
        int low = l, high = r;
        int ans = r + 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}