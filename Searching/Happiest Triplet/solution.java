import java.util.*;

class Solution {
    public int[] smallestDiff(int a[], int b[], int c[]) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        int n = a.length;
        
        int i = 0, j = 0, k = 0;
        int bestDiff = Integer.MAX_VALUE;
        int bestSum = Integer.MAX_VALUE;
        int[] bestTriplet = new int[3];
        
        while (i < n && j < n && k < n) {
            int x = a[i], y = b[j], z = c[k];
            int minVal = Math.min(x, Math.min(y, z));
            int maxVal = Math.max(x, Math.max(y, z));
            int diff = maxVal - minVal;
            int sum = x + y + z;
            
            if (diff < bestDiff || (diff == bestDiff && sum < bestSum)) {
                bestDiff = diff;
                bestSum = sum;
                bestTriplet[0] = x;
                bestTriplet[1] = y;
                bestTriplet[2] = z;
            }
            
            // Move pointer of smallest value
            if (minVal == x) i++;
            else if (minVal == y) j++;
            else k++;
        }
        
        // Sort in decreasing order
        Arrays.sort(bestTriplet);
        return new int[]{bestTriplet[2], bestTriplet[1], bestTriplet[0]};
    }
}