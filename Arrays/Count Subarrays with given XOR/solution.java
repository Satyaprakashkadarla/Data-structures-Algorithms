import java.util.*;

class Solution {
    public long subarrayXor(int arr[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefix = 0;
        long count = 0;
        
        for (int num : arr) {
            prefix ^= num;
            count += map.getOrDefault(prefix ^ k, 0);
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        
        return count;
    }
}