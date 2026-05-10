import java.util.*;

class Solution {
    public int maxProfit(int x, int y, int[] a, int[] b) {
        int n = a.length;
        int sumB = 0;
        Integer[] gain = new Integer[n];
        for (int i = 0; i < n; i++) {
            sumB += b[i];
            gain[i] = a[i] - b[i];
        }
        
        Arrays.sort(gain, Collections.reverseOrder());
        
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + gain[i - 1];
        }
        
        long maxProfit = 0;
        int minA = Math.max(0, n - y);
        int maxA = Math.min(x, n);
        
        for (int aCount = minA; aCount <= maxA; aCount++) {
            maxProfit = Math.max(maxProfit, sumB + prefix[aCount]);
        }
        
        return (int) maxProfit;
    }
}