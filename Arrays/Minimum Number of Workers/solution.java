import java.util.*;

class Solution {
    public int minMen(int arr[]) {
        int n = arr.length;
        List<int[]> intervals = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (arr[i] != -1) {
                int start = Math.max(0, i - arr[i]);
                int end = Math.min(n - 1, i + arr[i]);
                intervals.add(new int[]{start, end});
            }
        }
        
        // Sort by start, then by end desc
        intervals.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });
        
        int count = 0;
        int covered = 0;
        int i = 0;
        
        while (covered < n) {
            int maxEnd = -1;
            while (i < intervals.size() && intervals.get(i)[0] <= covered) {
                maxEnd = Math.max(maxEnd, intervals.get(i)[1]);
                i++;
            }
            if (maxEnd < covered) {
                return -1; // cannot cover
            }
            count++;
            covered = maxEnd + 1;
        }
        
        return count;
    }
}