import java.util.*;

class Solution {
    public int countSubset(int[] arr, int k) {
        int n = arr.length;
        int mid = n / 2;
        
        // Left half
        Map<Long, Integer> leftSums = new HashMap<>();
        for (int mask = 0; mask < (1 << mid); mask++) {
            long sum = 0;
            for (int i = 0; i < mid; i++) {
                if ((mask >> i & 1) == 1) {
                    sum += arr[i];
                }
            }
            leftSums.put(sum, leftSums.getOrDefault(sum, 0) + 1);
        }
        
        // Right half
        int count = 0;
        int rightSize = n - mid;
        for (int mask = 0; mask < (1 << rightSize); mask++) {
            long sum = 0;
            for (int i = 0; i < rightSize; i++) {
                if ((mask >> i & 1) == 1) {
                    sum += arr[mid + i];
                }
            }
            count += leftSums.getOrDefault(k - sum, 0);
        }
        
        return count;
    }
}