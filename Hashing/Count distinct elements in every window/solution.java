import java.util.*;

class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        
        // First window
        for (int i = 0; i < k; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        result.add(freq.size());
        
        // Slide window
        for (int i = k; i < arr.length; i++) {
            // Remove arr[i-k]
            int out = arr[i - k];
            freq.put(out, freq.get(out) - 1);
            if (freq.get(out) == 0) {
                freq.remove(out);
            }
            
            // Add arr[i]
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            
            result.add(freq.size());
        }
        
        return result;
    }
}