import java.util.*;

class Solution {
    public int equalSumSpan(int[] a1, int[] a2) {
        int n = a1.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int diff = 0;
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            diff += (a1[i] - a2[i]);
            if (map.containsKey(diff)) {
                maxLen = Math.max(maxLen, i - map.get(diff));
            } else {
                map.put(diff, i);
            }
        }
        return maxLen;
    }
}