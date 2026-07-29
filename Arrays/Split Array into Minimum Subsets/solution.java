import java.util.*;

class Solution {
    int minSubsets(int arr[]) {
        Arrays.sort(arr);
        int groups = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                groups++;
            }
        }
        return groups;
    }
}