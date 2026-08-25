import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Store all numbers in a set for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Check multiples of k starting from k
        int multiple = k;
        while (set.contains(multiple)) {
            multiple += k;
        }
        
        return multiple;
    }
}