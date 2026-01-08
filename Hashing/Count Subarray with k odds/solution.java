import java.util.*;

class Solution {
    public int countSubarrays(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        int prefix = 0;
        int count = 0;
        
        for (int num : arr) {
            if (num % 2 == 1) {
                prefix++;
            }
            count += map.getOrDefault(prefix - k, 0);
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        
        return count;
    }
}