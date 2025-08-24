import java.util.*;

class Solution {
    public boolean canMake(int[] arr, int day, int k, int m) {
        int count = 0, bouquets = 0;
        for (int bloomDay : arr) {
            if (bloomDay <= day) {
                count++;
                if (count == k) {
                    bouquets++;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }
        return bouquets >= m;
    }

    public int minDaysBloom(int[] arr, int k, int m) {
        long need = 1L * m * k;
        if (need > arr.length) return -1;

        int low = Arrays.stream(arr).min().getAsInt();
        int high = Arrays.stream(arr).max().getAsInt();        
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMake(arr, mid, k, m)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
