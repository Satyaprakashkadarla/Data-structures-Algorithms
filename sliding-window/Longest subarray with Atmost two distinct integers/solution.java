import java.util.*;

class Solution {
    public int totalElements(int[] arr) {
        int left = 0, maxLen = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int right = 0; right < arr.length; right++) {
            freq.put(arr[right], freq.getOrDefault(arr[right], 0) + 1);
            
            while (freq.size() > 2) {
                int leftVal = arr[left];
                freq.put(leftVal, freq.get(leftVal) - 1);
                if (freq.get(leftVal) == 0) {
                    freq.remove(leftVal);
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}