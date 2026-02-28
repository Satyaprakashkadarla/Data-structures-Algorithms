import java.util.*;

class Solution {
    public static ArrayList<Integer> findClosestPair(int arr1[], int arr2[], int x) {
        int n = arr1.length, m = arr2.length;
        int i = 0, j = m - 1;
        int bestDiff = Integer.MAX_VALUE;
        int bestI = 0, bestJ = m - 1;
        
        while (i < n && j >= 0) {
            int sum = arr1[i] + arr2[j];
            int diff = Math.abs(sum - x);
            if (diff < bestDiff) {
                bestDiff = diff;
                bestI = i;
                bestJ = j;
            }
            if (sum < x) {
                i++;
            } else {
                j--;
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr1[bestI]);
        result.add(arr2[bestJ]);
        return result;
    }
}