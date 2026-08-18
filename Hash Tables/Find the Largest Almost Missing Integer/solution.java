import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int totalSubarrays = n - k + 1;
        
        // Map to store count of subarrays each number appears in
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // For each subarray of size k
        for (int i = 0; i <= n - k; i++) {
            // Use a set to avoid counting duplicates within the same subarray
            Set<Integer> seen = new HashSet<>();
            
            for (int j = i; j < i + k; j++) {
                if (!seen.contains(nums[j])) {
                    seen.add(nums[j]);
                    countMap.put(nums[j], countMap.getOrDefault(nums[j], 0) + 1);
                }
            }
        }
        
        // Find the largest number that appears in exactly one subarray
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }
        
        return result;
    }
}