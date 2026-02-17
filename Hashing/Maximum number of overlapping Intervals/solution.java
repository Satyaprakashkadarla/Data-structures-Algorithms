import java.util.*;

class Solution {
    public static int overlapInt(int[][] arr) {
        int n = arr.length;
        
        // Create event list: +1 for start, -1 for end+1
        int[][] events = new int[2 * n][2];
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            events[idx][0] = arr[i][0];
            events[idx][1] = 1;      // start event
            idx++;
            
            events[idx][0] = arr[i][1] + 1;
            events[idx][1] = -1;     // end event
            idx++;
        }
        
        // Sort events by time
        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0]) 
                return a[1] - b[1];
            return a[0] - b[0];
        });
        
        int maxOverlap = 0;
        int current = 0;
        
        for (int i = 0; i < 2 * n; i++) {
            current += events[i][1];
            maxOverlap = Math.max(maxOverlap, current);
        }
        
        return maxOverlap;
    }
}
